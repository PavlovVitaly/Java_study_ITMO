package com.company.tools.loader;

import com.company.log.logger.ListenerOfDownloader;
import com.company.tools.timer.Timer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Semaphore;

/**
 * Created by владелец on 27.04.2017.
 * <div>Класс загрузчиков файла</div>
 */
public class Downloader extends Thread  {
    private URL loadedUrl;
    private File downloadedPath;
    private ListenerOfDownloader listener;
    private HttpURLConnection connection;
    private Timer timer;
    private Semaphore semaphore;

    /**
     * <div>Конструктор инициализирует загрузчик и запускает загрузку файла в параллельном потоке.</div>
     * @param reference ссылка (http или https) на скачиваемый файл.
     * @param downloadedPath директория в которую производится загрузка.
     * @param listener наблюдатель.
     * @param semaphore семафор, для разрешения возникающих коллизий.
     */
    public Downloader(String reference, String downloadedPath, ListenerOfDownloader listener, Semaphore semaphore)throws Exception{
        setListener(listener);
        loadedUrl = new URL(reference);
        this.downloadedPath = new File(downloadedPath);
        this.downloadedPath.createNewFile();
        connection = (HttpURLConnection) loadedUrl.openConnection();
        timer = new Timer();
        this.semaphore = semaphore;
        this.start();
    }

    /**
     * <div>Метод задания наблюдателя.</div>
     * @param listener наблюдатель.
     */
    public void setListener(ListenerOfDownloader listener){
        this.listener = listener;
    }

    /**
     * <div>Метод загружает файл и сообщает наблюдателю о том, загрузился ли файл или нет с доп. информацией
     * (см. ListenerOfDownloader методы onError и onDownload).</div>
     * TODO метод нарушает принцып единой ответственности, т.к. метод и загружает и сообщает наблюдателю
     * TODO информацию о загрузке, необходимо разделить ответственности.
     */
    private void downloadFile(){
        long readBytes = 0;
        BufferedInputStream input = null;
        FileOutputStream output = null;

        try {
            try {
                connection.connect();
            } catch (IOException e) {
                listener.onError(loadedUrl.getRef(), downloadedPath.getPath(), e.getMessage());
            }
            input = new BufferedInputStream(connection.getInputStream());
            output = new FileOutputStream(downloadedPath);

            timer.start();
            readBytes = directInputToOutput(input, output);
        } catch (IOException e) {
            listener.onError(loadedUrl.getRef(), downloadedPath.getPath(), e.getMessage());
        } catch(Exception e){
            listener.onError(loadedUrl.getRef(), downloadedPath.getPath(), e.getMessage());
        }
        finally {
            closeStreamsAndConnection(connection, input, output);
        }
        timer.stop();
        listener.onDownload(loadedUrl.getPath(), downloadedPath.getName(), readBytes, timer.getTimeMilliseconds());
    }

    /**
     * <div>Метод перенаправляет input stream в output stream.</div>
     * @param inputStream входной поток.
     * @param outputStream выходной поток.
     */
    private long directInputToOutput(BufferedInputStream inputStream, FileOutputStream outputStream) throws Exception{
        byte[] buffer = new byte[1024];
        int intRead = 0;
        long readBytes = 0;

        while((intRead = inputStream.read(buffer))!= -1) {
            outputStream.write(buffer, 0, intRead);
            readBytes += intRead;
        }
        return readBytes;
    }

    /**
     * <div>Метод закрывает входной и выходной потоки, а также закрывает соедтнение.</div>
     * @param curHttpConnection текущее http соединение.
     * @param inputStream входной поток.
     * @param outputStream выходной поток.
     */
    private void closeStreamsAndConnection(HttpURLConnection curHttpConnection, BufferedInputStream inputStream, FileOutputStream outputStream){
        try {
            outputStream.flush();
            inputStream.close();
        } catch (IOException e) {
            listener.onError(loadedUrl.getRef(), downloadedPath.getPath(), e.getMessage());
        }
        curHttpConnection.disconnect();
    }

    /**
     * <div>Метод запускает загрузку при разрешающем сигнале семафора, а также сообщает наблюдателю
     * о окончании загрузки.</div>
     * TODO метод нарушает принцып единой ответственности, т.к. метод и запускает загрузку
     * TODO и сообщает наблюдателю о окончании загрузки.
     */
    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listener.addDownload();
        try {
            downloadFile();
        } catch (Exception e) {
            //e.printStackTrace();
        }finally {
            listener.releaseDownload();
            semaphore.release();
        }
    }
}

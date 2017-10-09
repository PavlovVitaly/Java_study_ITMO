package com.company.tools.loader;

import com.company.downloadedFiles.parametersOfFiles.FailedDownload;
import com.company.downloadedFiles.parametersOfFiles.InfoAboutDownloadedFile;
import com.company.log.logger.DownloadLogger;
import com.company.log.logger.ListenerOfDownloader;
import com.company.output.console.OutputWriter;
import com.company.tools.timer.Timer;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by владелец on 27.04.2017.
 * <div>Класс создающий потоки с загрузкой файлов.</div>
 */
public class DownloaderFiles {
    private DownloadLogger logger;
    private AtomicInteger cntActiveDownloads;
    private Semaphore semaphore;
    private OutputWriter printer;

    /**
     * <div>Конструктор.</div>
     * @note Инициирует загрузку файлов.
     * @param refAndName ассоциативный массив с сылками и именами загружаемых файлов.
     * @param path директория в которую производится загрузка.
     * @param numberOfDownloads максимальное колличество потоков загрузки.
     * @param logger логгер для хранения информации о загрузках.
     * @param printer объект вывода результатов.
     */
    public DownloaderFiles(Map<String, String> refAndName,
                           String path,
                           int numberOfDownloads,
                           DownloadLogger logger,
                           OutputWriter printer){

        this.logger = logger;
        this.printer = printer;
        cntActiveDownloads = new AtomicInteger(0);
        semaphore = new Semaphore(numberOfDownloads, true);
        startDownload(refAndName, path, numberOfDownloads);
    }

    /**
     * <div>Метод, запускающий потоки загрузок.</div>
     * @param refAndName ассоциативный массив с сылками и именами загружаемых файлов.
     * @param path директория в которую производится загрузка.
     * @param numberOfDownloads максимальное колличество потоков загрузки.
     * TODO вынести создание листенера
     */
    private void startDownload(Map<String, String> refAndName, String path, int numberOfDownloads){
        Timer timer = new Timer();
        Set<Map.Entry<String, String>> entries = refAndName.entrySet();
        timer.start();

        for(Map.Entry<String, String> item: entries){
            ListenerOfDownloader listener = new ListenerOfDownloader() {

                private AtomicInteger curNumOfFiles = new AtomicInteger(0);

                public void addDownload(){
                    printer.showStartedDownload(item.getValue());
                }

                public void releaseDownload(){
                    cntActiveDownloads.incrementAndGet();
                }

                @Override
                public void onError(String reference, String path, String reason) {
                    logger.addFailedDownload(new FailedDownload(reference, path, reason));
                    printer.showFailedDownload(path, reason);
                    curNumOfFiles.incrementAndGet();
                }

                @Override
                public void onDownload(String downloadedReference, String path, long sizeOfFileInByte, long timeDownload) {
                    logger.addSuccessDownload(new InfoAboutDownloadedFile(downloadedReference, path, sizeOfFileInByte, timeDownload));
                    printer.showSuccessDownload(path, sizeOfFileInByte, timeDownload);
                    curNumOfFiles.incrementAndGet();
                    if(curNumOfFiles.get() >= refAndName.size()){
                        timer.stop();

                        logger.setTotalTimeDownloadingInMilliseconds(timer.getTimeMilliseconds());
                        printer.showStatisticsDownloads(logger);
                    }


                }
            };

            try {
                new Downloader(item.getKey(), path + "\\" + item.getValue(), listener, semaphore);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int numberOfFiles = refAndName.size();
        while((semaphore.availablePermits() != numberOfDownloads) ||
                (cntActiveDownloads.get() < numberOfDownloads));

       timer.stop();

        logger.setTotalTimeDownloadingInMilliseconds(timer.getTimeMilliseconds());
        printer.showStatisticsDownloads(logger);
    }
}

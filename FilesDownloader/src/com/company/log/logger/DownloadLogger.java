package com.company.log.logger;

import com.company.downloadedFiles.parametersOfFiles.FailedDownload;
import com.company.downloadedFiles.parametersOfFiles.InfoAboutDownloadedFile;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by владелец on 27.04.2017.
 * Класс логгера загрузок.
 */
public class DownloadLogger {
    private LinkedList<InfoAboutDownloadedFile> infoAboutDownloadedFiles;
    private long totalTimeDownloadingInMilliseconds;
    private List<FailedDownload> failedDownloads;
    private AtomicInteger totalNumFiles;

    public DownloadLogger(){
        infoAboutDownloadedFiles = new LinkedList<>();
        totalTimeDownloadingInMilliseconds = 0;
        failedDownloads = new LinkedList<>();
        totalNumFiles = new AtomicInteger(0);
    }

    public LinkedList<InfoAboutDownloadedFile> getInfoAboutDownloadedFiles() {
        return infoAboutDownloadedFiles;
    }

    /**
     * <div>Getter времени выполнения сеанса загрузки файлов.</div>
     * @return totalTimeDownloadingInMilliseconds время выполнения сеанса загрузки файлов.
     */
    public long getTotalTimeDownloadingInMilliseconds() {
        return totalTimeDownloadingInMilliseconds;
    }

    /**
     * <div>Метод записывает в логгер временя выполнения сеанса загрузки файлов.</div>
     * @param  totalTimeDownloadingInMilliseconds время выполнения сеанса загрузки файлов.
     */
    public void setTotalTimeDownloadingInMilliseconds(long totalTimeDownloadingInMilliseconds) {
        this.totalTimeDownloadingInMilliseconds = totalTimeDownloadingInMilliseconds;
    }

    /**
     * <div>Getter количества файлов в сеансе загрузки.</div>
     * @return totalNumFiles количество файлов в сеансе загрузки.
     */
    public int getTotalNumFiles() {
        return totalNumFiles.get();
    }

    /**
     * <div>Метод добавляет в логгер запись с информацией об успешной загрузке.</div>
     * @param  info информация об успешной загрузке.
     */
    public synchronized void addSuccessDownload(InfoAboutDownloadedFile info){
        infoAboutDownloadedFiles.add(info);
        totalNumFiles.incrementAndGet();
    }

    /**
     * <div>Метод добавляет в логгер запись с информацией о неудачной загрузке.</div>
     * @param  failedDownload информация о неудачной загрузке.
     */
    public synchronized void addFailedDownload(FailedDownload failedDownload){
        failedDownloads.add(failedDownload);
        totalNumFiles.incrementAndGet();
    }

    /**
     * <div>Getter процента успешных загрузок в сеансе.</div>
     * @return количество файлов в сеансе загрузки.
     */
    public int getPercentOfSuccessfulDownloads(){
        return (int)((infoAboutDownloadedFiles.size() * 100) / totalNumFiles.get());
    }

    /**
     * <div>Getter количества успешных загрузок в сеансе.</div>
     * @return спешных загрузок в сеансе.
     */
    public int getNumberDownloadedFiles(){
        return infoAboutDownloadedFiles.size();
    }

    /**
     * <div>Getter размера успешно загруженных файлов (в байтах) в сеансе.</div>
     * @return размер успешно загруженных файлов (в байтах) в сеансе.
     * TODO используется для вычисления скорости загрузки в сеансе,
     * TODO что не является корректным, т.к. неудачные загрузки могут быть выполнены на половину и т.д.
     */
    public long getTotalSizeDownloadedFilesInBytes(){
        long result = 0;
        for(InfoAboutDownloadedFile item: infoAboutDownloadedFiles)
            result += item.getSizeOfFileInByte();
        return result;
    }
}

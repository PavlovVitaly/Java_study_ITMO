package com.company.output.console;

import com.company.log.logger.DownloadLogger;

/**
 * Created by владелец on 16.05.2017.
 * Класс для вывода данных в консоль.
 * TODO singleton
 */
public class ConsoleWriter implements OutputWriter {
    @Override
    public synchronized void showStartedDownload(String name) {
        System.out.println(OutputStatusDownloading.showStartedDownload(name));
    }

    @Override
    public synchronized void showSuccessDownload(String name, long sizeOfFile, long timeOfExecution) {
        System.out.println(OutputStatusDownloading.showSuccessDownload(name, sizeOfFile, timeOfExecution));
    }

    @Override
    public synchronized void showFailedDownload(String name, String reason) {
        System.out.println(OutputStatusDownloading.showFailedDownload(name, reason));
    }

    @Override
    public synchronized void showStatisticsDownloads(DownloadLogger logger) {
        System.out.println(OutputStatusDownloading.showStatisticsDownloads(logger));
    }
}

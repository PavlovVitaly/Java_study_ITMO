package com.company.output.console;

import com.company.log.logger.DownloadLogger;
import com.company.downloadedFiles.parametersOfFiles.SizeOfFile;
import com.company.downloadedFiles.parametersOfFiles.TimeOfExecution;

/**
 * Created by владелец on 02.05.2017.
 * <div>Класс формирования сообщений загрузчика.</div>
 */
public class OutputStatusDownloading {
    /**
     * <div>Метод формирует сообщение о начале загрузки файла.</div>
     * @param name имя загружаемого файла.
     * @return сформированное сообщение.
     */
    public static String showStartedDownload(String name){
        return "Downloading file: " + name;
    }

    /**
     * <div>Метод формирует сообщение о успешной загрузке файла.</div>
     * @param name имя загружаемого файла.
     * @param sizeOfFile размер загруженного файла.
     * @param timeOfExecution время загрузки файла.
     * @return сформированное сообщение.
     */
    public static String showSuccessDownload(String name, long sizeOfFile, long timeOfExecution){
        SizeOfFile size = new SizeOfFile(sizeOfFile);
        TimeOfExecution time = new TimeOfExecution(timeOfExecution);
        return "The file " + name + " is downloaded: " + size.toString() + " in " + time.toString();
    }

    /**
     * <div>Метод формирует сообщение о неудачной загрузке файла.</div>
     * @param name имя загружаемого файла.
     * @param reasonOfFailure причина провала загрузки файла.
     * @return сформированное сообщение.
     */
    public static String showFailedDownload(String name, String reasonOfFailure){
        return "Filed downloading file " + name + ".\nReason of failure: " + reasonOfFailure;
    }

    /**
     * <div>Метод формирует сообщение содержащее статистику загрузки файлов.</div>
     * @param logger логгер.
     * @return сформированное сообщение, содержащие процент успешных загрузок,
     * количество загруженных файлов, суммарный размер файлов, общее время загрузки и
     * среднюю скорость загрузки.
     */
    public static String showStatisticsDownloads(DownloadLogger logger){
        SizeOfFile size = new SizeOfFile(logger.getTotalSizeDownloadedFilesInBytes());
        double timeInSeconds = logger.getTotalTimeDownloadingInMilliseconds() / 1000.0;
        double speedOfDownloads = logger.getTotalSizeDownloadedFilesInBytes() / timeInSeconds;
        SizeOfFile speed = new SizeOfFile((long)speedOfDownloads);
        TimeOfExecution time = new TimeOfExecution(logger.getTotalTimeDownloadingInMilliseconds());
        return "Downloaded: " + logger.getPercentOfSuccessfulDownloads() + "%\n" +
                "Downloaded: " + logger.getNumberDownloadedFiles() + " files, " + size.toString() + "\n" +
                "Time of downloading: " + time.toString() + "\n" +
                "Average speed of downloading: " + String.format("%.2f", speed.getSize()) + size.getUnitPrefix() + "/s";
    }
}

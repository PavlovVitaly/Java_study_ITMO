package com.company.output.console;

import com.company.log.logger.DownloadLogger;

/**
 * Created by владелец on 02.05.2017.
 * <div>Интерфейс вывода данных о загрузке</div>
 */
public interface OutputWriter {
    /**
     * <div>Метод выводит сообщение о начале загрузке с именем загружаемого файла.</div>
     * @param name имя загружаемого файла.
     */
    public void showStartedDownload(String name);

    /**
     * <div>Метод выводит сообщение о успешной загрузке с именем загружаемого файла,
     * его размером и временем загрузки.</div>
     * @param name имя загружаемого файла.
     * @param sizeOfFile размер загружаемого файла.
     * @param timeOfExecution время загрузки файла.
     */
    public void showSuccessDownload(String name, long sizeOfFile, long timeOfExecution);

    /**
     * <div>Метод выводит сообщение о неудачной загрузке с именем загружаемого файла
     * и причиной провала загрузки.</div>
     * @param name имя загружаемого файла.
     * @param reason причина провала загрузки.
     */
    public void showFailedDownload(String name, String reason);

    /**
     * <div>Метод выводит статистику проведенного сеанса загрузки файлов.</div>
     * @param logger объект, ведущий лог загрузок.
     */
    public void showStatisticsDownloads(DownloadLogger logger);
}

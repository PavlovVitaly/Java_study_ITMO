package com.company.log.logger;

/**
 * Created by владелец on 27.04.2017.
 * Интерфейс для получения данных от потоков ведущих загрузку.
 */
public interface ListenerOfDownloader {

    /**
     * <div>Метод сообщающий об ошибке загрузки.</div>
     * @param reference ссылка (http или https) на скачиваемый файл.
     * @param path директория в которую производится загрузка.
     * @param reason причина провала загрузки.
     */
    public void onError(String reference, String path, String reason);

    /**
     * <div>Метод сообщающий об удачной загрузке.</div>
     * @param downloadedReference ссылка (http или https) на скачиваемый файл.
     * @param path директория в которую производится загрузка.
     * @param sizeOfFileInByte размер загруженного файла.
     * @param timeDownload время загрузки файла.
     */
    public void onDownload(String downloadedReference, String path, long sizeOfFileInByte, long timeDownload);

    /**
     * <div>Метод сообщающий о начальной загрузке.</div>
     */
    public void addDownload();

    /**
     * <div>Метод сообщающий об окончании загрузке.</div>
     */
    public void releaseDownload();
}

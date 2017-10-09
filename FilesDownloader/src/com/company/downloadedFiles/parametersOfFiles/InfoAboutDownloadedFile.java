package com.company.downloadedFiles.parametersOfFiles;

/**
 * Created by владелец on 27.04.2017.
 * Класс для хранения информации о загруженных файлах.
 */
public class InfoAboutDownloadedFile {
    private String downloadedReference;
    private String path;
    private long sizeOfFileInByte;
    private long timeDownloadMillisecond;

    /**
     * <div>Конструктор инициализирует объект информации о загруженных файлах.</div>
     * @param downloadedReference ссылка (http или https) на скачиваемый файл.
     * @param path директория в которую производится загрузка.
     * @param sizeOfFileInByte размер файла в байтах.
     * @param timeDownload время загрузки файла.
     */
    public InfoAboutDownloadedFile(String downloadedReference, String path, long sizeOfFileInByte, long timeDownload) {
        this.downloadedReference = downloadedReference;
        this.path = path;
        this.sizeOfFileInByte = sizeOfFileInByte;
        this.timeDownloadMillisecond = timeDownload;
    }

    /**
     * <div>Getter ссылки (http или https) на скачиваемый файл.</div>
     * @return ссылка (http или https) на скачиваемый файл.
     */
    public String getDownloadedReference() {
        return downloadedReference;
    }

    /**
     * <div>Getter директории в которую производится загрузка.</div>
     * @return директория в которую производится загрузка.
     */
    public String getPath() {
        return path;
    }

    /**
     * <div>Getter размера загруженного файла в байтах.</div>
     * @return размер загруженного файла в байтах.
     */
    public long getSizeOfFileInByte() {
        return sizeOfFileInByte;
    }

    /**
     * <div>Getter времени загрузки файла.</div>
     * @return время загрузки файла.
     */
    public long getTimeDownloadMillisecond() {
        return timeDownloadMillisecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoAboutDownloadedFile that = (InfoAboutDownloadedFile) o;

        if (sizeOfFileInByte != that.sizeOfFileInByte) return false;
        if (timeDownloadMillisecond != that.timeDownloadMillisecond) return false;
        if (!downloadedReference.equals(that.downloadedReference)) return false;
        return path.equals(that.path);
    }

    @Override
    public int hashCode() {
        int result = downloadedReference.hashCode();
        result = 31 * result + path.hashCode();
        result = 31 * result + (int) (sizeOfFileInByte ^ (sizeOfFileInByte >>> 32));
        result = 31 * result + (int) (timeDownloadMillisecond ^ (timeDownloadMillisecond >>> 32));
        return result;
    }
}

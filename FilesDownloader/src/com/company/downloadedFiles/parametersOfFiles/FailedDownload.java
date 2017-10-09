package com.company.downloadedFiles.parametersOfFiles;

/**
 * Created by владелец on 02.05.2017.
 * <div>Класс с информацией о неудачной загрузке</div>
 */
public class FailedDownload {
    private String reference;
    private String path;
    private String reasonOfFailure;

    /**
     * <div>Конструктор инициализирует объект информацией о неудачной загрузке.</div>
     * @param reference ссылка (http или https) на скачиваемый файл.
     * @param path директория в которую производится загрузка.
     * @param reasonOfFailure причина провала загрузки.
     */
    public FailedDownload(String reference, String path, String reasonOfFailure){
        setInfoOfFailure(reference, path, reasonOfFailure);
    }

    /**
     * <div>Метод присваивает объекту информацию о неудачной загрузке.</div>
     * @param reference ссылка (http или https) на скачиваемый файл.
     * @param path директория в которую производится загрузка.
     * @param reasonOfFailure причина провала загрузки.
     */
    public void setInfoOfFailure(String reference, String path, String reasonOfFailure){
        this.reference = reference;
        this.path = path;
        this.reasonOfFailure = reasonOfFailure;
    }

    /**
     * <div>Getter ссылки (http или https) на скачиваемый файл.</div>
     * @return ссылка (http или https) на скачиваемый файл.
     */
    public String getReference() {
        return reference;
    }

    /**
     * <div>Getter директории в которую производится загрузка.</div>
     * @return директория в которую производится загрузка.
     */
    public String getPath() {
        return path;
    }

    /**
     * <div>Getter причины провала загрузки.</div>
     * @return причина провала загрузки.
     */
    public String getReasonOfFailure() {
        return reasonOfFailure;
    }
}

package com.company.downloadedFiles.parametersOfFiles;

/**
 * Created by владелец on 03.05.2017.
 * <div>Класс для хранения и конвертирования размера скаченного файла в максимально возможную
 * единицу измерения информации.</div>
 */
public class SizeOfFile {
    private static final long thousand = 1000;
    private double size;
    private UnitPrefixOfBytes unitPrefix;

    /**
     * <div>Конструктор инициализирует объект информацей об объеме файла.</div>
     * @param bytes размер файла в байтах.
     */
    public SizeOfFile(long bytes){
        convertSizeToMaxUnitPrefix(bytes);
    }

    /**
     * <div>Метод конвертирует размер файла из байт в максимально возможную единицу измерения
     * (полученное значение размера не может быть меньше нуля).</div>
     * @param bytes размер файла в байтах.
     */
    public void convertSizeToMaxUnitPrefix(long bytes){
        double size = bytes;
        for (int i = 0; i <= 4; i++){
            if (size >= 1){
                this.size = size;
                size /= thousand;
            }else {
                unitPrefix = UnitPrefixOfBytes.setPrefix(i - 1);
                break;
            }
        }
    }

    /**
     * <div>Getter конвертированного размера файла.</div>
     * @return конвертированный размер файла.
     */
    public double getSize() {
        return size;
    }

    /**
     * <div>Getter полученной единицы измерения информации после конвертировании.</div>
     * @return единицу измерения информации после конвертировании.
     */
    public UnitPrefixOfBytes getUnitPrefix() {
        return unitPrefix;
    }

    @Override
    public String toString() {
        return String.format("%.2f", size) + " " + unitPrefix;
    }
}

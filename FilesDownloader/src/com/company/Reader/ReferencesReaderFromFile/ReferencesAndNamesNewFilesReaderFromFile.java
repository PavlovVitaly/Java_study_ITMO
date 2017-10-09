package com.company.Reader.ReferencesReaderFromFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by владелец on 26.04.2017.
 * <div>Класс для парсинга и хранения данных из файла с ссылками для загрузки.</div>
 * TODO нарушение принцыпа единой ответственности.
 */
public class ReferencesAndNamesNewFilesReaderFromFile {
    private Map<String, String> referencesAndNamesNewFiles;

    /**
     * <div>Конструктор инициализирует объект информацией из файла с ссылками для загрузки.</div>
     * @param inputFile полное имя файла со ссылками.
     */
    public ReferencesAndNamesNewFilesReaderFromFile(File inputFile) throws Exception{
        referencesAndNamesNewFiles = new HashMap<>();
        setReferencesAndNamesNewFiles(inputFile);
    }

    /**
     * <div>Методод парсит входной файл и записывает в ассоциативный массив ссылки и соответствующие названия файлов .</div>
     * @param inputFile полное имя файла со ссылками.
     */
    public void setReferencesAndNamesNewFiles(File inputFile) throws Exception{
        File file = inputFile;
        StringBuffer str = new StringBuffer();
        String[] dividedStr;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        do{
            str.append(reader.readLine());
            if (!str.toString().equals("null")){
                dividedStr = str.toString().split(" ");
                referencesAndNamesNewFiles.put(dividedStr[0], dividedStr[1]);
                str.delete(0, str.length());
            }
        }while(!str.toString().equals("null"));
    }

    /**
     * <div>Getter ассоициативного массива с сылками и именами файлов.</div>
     * @return ассоициативный массив с сылками и именами файлов.
     */
    public Map<String, String>  getReferencesAndNamesNewFiles(){
        return referencesAndNamesNewFiles;
    }
}

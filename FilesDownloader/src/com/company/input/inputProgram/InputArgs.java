package com.company.input.inputProgram;

import com.company.input.exception.NotExistingDirectory;
import com.company.input.exception.NotExistingFile;
import com.company.input.exception.NotValidSizeArgString;

import java.io.File;

/**
 * Created by владелец on 02.05.2017.
 * Класс для хранения аргументов программы.
 */
public class InputArgs {
    private int numberOfThreads;
    private File outputFolder;
    private File fileWithReferences;
    private static final int sizeOfInputArgs = 3;

    /**
     * <div>Конструктор инициализирует объект информации об аргументах программы.</div>
     * @param args строка аргументов программы.
     */
    public InputArgs(String[] args)throws Exception {
        setInputArgs(args);
    }

    /**
     * <div>Метод задает объекту информацию об аргументах программы.</div>
     * @param args строка аргументов программы.
     * @exception NotExistingDirectory директория с файлом, содержащем ссылки для загрузки, не существует.
     * @exception NotExistingFile файл, содержащий ссылки для загрузки, не существует.
     * @exception NotValidSizeArgString невалидная строка с аргументами программы.
     */
    public void setInputArgs(String[] args)throws Exception {
        if (args.length == sizeOfInputArgs) {
            this.numberOfThreads = Integer.parseInt(args[0]);
            this.outputFolder = new File(args[1]);
            this.fileWithReferences = new File(args[2]);

            if(!this.outputFolder.exists())
                throw new NotExistingDirectory();
            if (!this.fileWithReferences.exists())
                throw new NotExistingFile();
        }else
            throw new NotValidSizeArgString();
    }

    /**
     * <div>Getter количества потоков загрузки.</div>
     * @return количества потоков загрузки.
     */
    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    /**
     * <div>Getter директории в которую будет производиться загрузка.</div>
     * @return директория в которую будет производиться загрузка.
     */
    public File getOutputFolder() {
        return outputFolder;
    }

    /**
     * <div>Getter файла с сылками.</div>
     * @return файл с сылками.
     */
    public File getFileWithReferences() {
        return fileWithReferences;
    }
}
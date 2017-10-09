package com.company;

import com.company.Reader.ReferencesReaderFromFile.ReferencesAndNamesNewFilesReaderFromFile;
import com.company.input.inputProgram.InputArgs;
import com.company.log.logger.DownloadLogger;
import com.company.output.console.ConsoleWriter;
import com.company.output.console.OutputWriter;
import com.company.tools.loader.DownloaderFiles;

public class Main {

    public static void main(String[] args) {
        InputArgs inputArgs = null;
        try {
            inputArgs = new InputArgs(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ReferencesAndNamesNewFilesReaderFromFile reader = null;
        try {
            reader = new ReferencesAndNamesNewFilesReaderFromFile(inputArgs.getFileWithReferences());
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadLogger logger = new DownloadLogger();
        OutputWriter printer = new ConsoleWriter();

        DownloaderFiles downloaderFiles = new DownloaderFiles(reader.getReferencesAndNamesNewFiles(), inputArgs.getOutputFolder().getPath(), inputArgs.getNumberOfThreads(), logger, printer);
    }
}
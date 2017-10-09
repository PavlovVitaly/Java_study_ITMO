package packageOfTests.IntegrationTests;

import com.company.log.logger.DownloadLogger;
import com.company.tools.loader.DownloaderFiles;
import com.company.input.inputProgram.InputArgs;
import com.company.Reader.ReferencesReaderFromFile.ReferencesAndNamesNewFilesReaderFromFile;
import com.company.output.console.ConsoleWriter;
import com.company.output.console.OutputWriter;
import junit.framework.TestCase;

import java.io.File;

/**
 * Created by владелец on 04.05.2017.
 */
public class TestDownloaderFiles extends TestCase {

    private final String outputPath = ".\\Output";
    private final String inputPath = ".\\Input";

    private String numberThreads = "2";

    public void testSuccessfulDownloadFile(){
        System.out.println("\nStart");
        String[] args = new String[]{numberThreads, outputPath, inputPath + "\\test.txt"};
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

        String[] files = new String[]{"\\bb.jpg", "\\cc.jpg", "\\dd.png", "\\qq.jpg", "\\zz.jpg"};
        for(int i = 0; i < files.length; i++) {
            File file = new File(inputPath + files[i]);
            if(file.exists())
                file.delete();
        }

        DownloaderFiles downloaderFiles = new DownloaderFiles(reader.getReferencesAndNamesNewFiles(), inputArgs.getOutputFolder().getPath(), inputArgs.getNumberOfThreads(), logger, printer);
    }

    public void testFailureDownloadFile(){
        System.out.println("\nStart");
        String[] args = new String[]{numberThreads, outputPath, inputPath + "\\FailureTest.txt"};
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

        String[] files = new String[]{"\\bb.jpg", "\\cc.jpg", "\\dd.png", "\\qq.jpg", "\\zz.jpg", "\\ww.jpg", "\\tt.png", "\\yy.jpg", "\\uu.jpg"};
        for(int i = 0; i < files.length; i++) {
            File file = new File(inputPath + files[i]);
            if(file.exists())
                file.delete();
        }

        DownloaderFiles downloaderFiles = new DownloaderFiles(reader.getReferencesAndNamesNewFiles(), inputArgs.getOutputFolder().getPath(), inputArgs.getNumberOfThreads(), logger, printer);
    }
}

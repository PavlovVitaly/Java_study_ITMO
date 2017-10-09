package packageOfTests.IntegrationTests;

import com.company.tools.loader.Downloader;
import com.company.log.logger.ListenerOfDownloader;
import junit.framework.TestCase;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by user on 30.04.2017.
 */
public class TestDownloader extends TestCase {
    public final String outputPath = ".\\Output";
    public final String inputPath = ".\\Input";

    private String addressDownloadingFile = "https://www.motto.net.ua/old_site/img/unbelievable/1189283425_4672616374616C20417274203331.jpg";
    private String wrongAddressDownloadingFile = "https://www.motto.net.ua/old_site/img/unbelievable/1189283425_467261.jpg";
    protected String nameOfFile = "The Path Of Totality.jpg";
    private long sizeOfFile = 354448;
    private boolean downloadIsDone;
    private List<String> errors;

    public void testSuccessCreateDownloadedFile(){
        File downloadedFile = new File(outputPath + "\\" + nameOfFile);
        downloadFile(downloadedFile, addressDownloadingFile);
        assertTrue(downloadedFile.exists());
    }

    public void testFullDownloadOfFile(){
        File downloadedFile = new File(outputPath + "\\" +nameOfFile);
        downloadFile(downloadedFile, addressDownloadingFile);
        assertEquals(sizeOfFile, downloadedFile.length());
    }

    public void testNumberDownloadingErrorConnection(){
        File downloadedFile = new File(outputPath + "\\" +nameOfFile);
        errors = new LinkedList<>();
        downloadFile(downloadedFile, wrongAddressDownloadingFile);
        assertEquals(1, errors.size());
    }

    public void testDownloadingErrorConnection(){
        String expectedErrorMessage = "https://www.motto.net.ua/old_site/img/unbelievable/1189283425_467261.jpg";
        File downloadedFile = new File(outputPath + "\\" +nameOfFile);
        errors = new LinkedList<>();
        downloadFile(downloadedFile, wrongAddressDownloadingFile);
        assertEquals(expectedErrorMessage, errors.get(0));
    }

    private void downloadFile(File loadedFile, String address){
        File downloadedFile = loadedFile;
        if (downloadedFile.exists())
            downloadedFile.delete();
        downloadIsDone = false;

        ListenerOfDownloader listener = new ListenerOfDownloader() {
            public void addDownload(){}
            public void releaseDownload(){}
            @Override
            public void onError(String reference, String path,  String reason) {
                errors.add(reason);
            }
            @Override
            public void onDownload(String downloadedReference, String path, long sizeOfFileInByte, long timeDownload) {
                downloadIsDone = true;
            }
        };
        Semaphore semaphore = new Semaphore(1);
        try {
            new Downloader(address, outputPath + "\\" +nameOfFile, listener, semaphore);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

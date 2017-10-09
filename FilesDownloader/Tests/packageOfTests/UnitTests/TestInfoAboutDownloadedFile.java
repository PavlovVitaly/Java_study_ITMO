package packageOfTests.UnitTests;

import com.company.downloadedFiles.parametersOfFiles.InfoAboutDownloadedFile;
import junit.framework.TestCase;

/**
 * Created by user on 03.05.2017.
 */
public class TestInfoAboutDownloadedFile extends TestCase {

    private final String downloadedReference = "TestReference";
    private final String path = "TestPath";
    private final long sizeOfFileInByte = 500;
    private final long timeDownloadMillisecond = 1000;
    InfoAboutDownloadedFile info = new InfoAboutDownloadedFile(downloadedReference, path, sizeOfFileInByte, timeDownloadMillisecond);

    public void testGetDownloadedReference(){
        assertEquals(downloadedReference, info.getDownloadedReference());
    }

    public void testGetPath(){
        assertEquals(path, info.getPath());
    }

    public void testGetSizeOfFileInByte(){
        assertEquals(sizeOfFileInByte, info.getSizeOfFileInByte());
    }

    public void testGetTimeDownloadMillisecond(){
        assertEquals(timeDownloadMillisecond, info.getTimeDownloadMillisecond());
    }
}

package packageOfTests.UnitTests;

import com.company.downloadedFiles.parametersOfFiles.SizeOfFile;
import junit.framework.TestCase;

/**
 * Created by владелец on 03.05.2017.
 */
public class TestSizeOfFile extends TestCase {

    public void testByteOutput(){
        final long bytes = 583;
        final String expectedResult = "583,00 B";
        SizeOfFile sizeOfFile = new SizeOfFile(bytes);
        assertEquals(expectedResult, sizeOfFile.toString());
    }

    public void testKByteOutput(){
        final long bytes = 1583;
        final String expectedResult = "1,58 kB";
        SizeOfFile sizeOfFile = new SizeOfFile(bytes);
        assertEquals(expectedResult, sizeOfFile.toString());
    }

    public void testMByteOutput(){
        final long bytes = 1583964;
        final String expectedResult = "1,58 MB";
        SizeOfFile sizeOfFile = new SizeOfFile(bytes);
        assertEquals(expectedResult, sizeOfFile.toString());
    }

    public void testGByteOutput(){
        final long bytes = 1583964327;
        final String expectedResult = "1,58 GB";
        SizeOfFile sizeOfFile = new SizeOfFile(bytes);
        assertEquals(expectedResult, sizeOfFile.toString());
    }

    public void testGetMByteOutput(){
        final long bytes = 1583964;
        final double expectedResult = 1.583964;
        SizeOfFile sizeOfFile = new SizeOfFile(bytes);
        assertEquals(expectedResult, sizeOfFile.getSize());
    }
}

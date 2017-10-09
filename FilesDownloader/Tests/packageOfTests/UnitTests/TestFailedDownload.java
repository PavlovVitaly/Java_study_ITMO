package packageOfTests.UnitTests;

import com.company.downloadedFiles.parametersOfFiles.FailedDownload;
import junit.framework.TestCase;

/**
 * Created by user on 03.05.2017.
 */
public class TestFailedDownload extends TestCase {
    private final String reference = "TestReference";
    private final String path = "TestPath";
    private final String reasonOfFailure = "TestError";
    private FailedDownload failedDownload = new FailedDownload(reference, path, reasonOfFailure);

    public void testGetReference(){
        assertEquals(reference, failedDownload.getReference());
    }

    public void testGetPath(){
        assertEquals(path, failedDownload.getPath());
    }

    public void testGetReasonOfFailure(){
        assertEquals(reasonOfFailure, failedDownload.getReasonOfFailure());
    }
}

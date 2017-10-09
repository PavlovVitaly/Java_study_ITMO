package packageOfTests.UnitTests;

import com.company.log.logger.DownloadLogger;
import com.company.downloadedFiles.parametersOfFiles.InfoAboutDownloadedFile;
import junit.framework.TestCase;

import java.util.LinkedList;

/**
 * Created by user on 03.05.2017.
 */
public class TestDownloadLogger extends TestCase {

    public void testGetInfoAboutDownloadedFiles(){
        LinkedList<InfoAboutDownloadedFile> infos = null;

        InfoAboutDownloadedFile[] expectedInfos = new InfoAboutDownloadedFile[5];
        expectedInfos[0] = new InfoAboutDownloadedFile("a", "b", 1, 10);
        expectedInfos[1] = new InfoAboutDownloadedFile("aa", "bb", 2, 20);
        expectedInfos[2] = new InfoAboutDownloadedFile("aaa", "bbb", 3, 30);
        expectedInfos[3] = new InfoAboutDownloadedFile("aaaa", "bbbb", 4, 40);
        expectedInfos[4] = new InfoAboutDownloadedFile("aaaaa", "bbbbb", 5, 50);

        DownloadLogger logger = new DownloadLogger();

        for(int i = 0; i < expectedInfos.length; i++){
            logger.addSuccessDownload(expectedInfos[i]);
        }

        infos = logger.getInfoAboutDownloadedFiles();

        int j = 0;
        for(InfoAboutDownloadedFile item: infos){
            assertEquals(expectedInfos[j++], item);
        }
    }

    public void testGetTotalNumFiles(){
        LinkedList<InfoAboutDownloadedFile> infos = null;

        InfoAboutDownloadedFile[] expectedInfos = new InfoAboutDownloadedFile[5];
        expectedInfos[0] = new InfoAboutDownloadedFile("a", "b", 1, 10);
        expectedInfos[1] = new InfoAboutDownloadedFile("aa", "bb", 2, 20);
        expectedInfos[2] = new InfoAboutDownloadedFile("aaa", "bbb", 3, 30);
        expectedInfos[3] = new InfoAboutDownloadedFile("aaaa", "bbbb", 4, 40);
        expectedInfos[4] = new InfoAboutDownloadedFile("aaaaa", "bbbbb", 5, 50);

        DownloadLogger logger = new DownloadLogger();

        for(int i = 0; i < expectedInfos.length; i++){
            logger.addSuccessDownload(expectedInfos[i]);
        }

        assertEquals(expectedInfos.length, logger.getTotalNumFiles());
    }
}

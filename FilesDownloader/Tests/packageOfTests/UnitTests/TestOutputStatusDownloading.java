package packageOfTests.UnitTests;

import com.company.downloadedFiles.parametersOfFiles.FailedDownload;
import com.company.downloadedFiles.parametersOfFiles.InfoAboutDownloadedFile;
import com.company.downloadedFiles.parametersOfFiles.SizeOfFile;
import com.company.log.logger.DownloadLogger;
import com.company.output.console.OutputStatusDownloading;
import junit.framework.TestCase;

/**
 * Created by user on 03.05.2017.
 */
public class TestOutputStatusDownloading extends TestCase {

    public void testShowStartedDownload(){
        final String name = "TestName";
        final String expectedResult = "Downloading file: " + name;
        assertEquals(expectedResult, OutputStatusDownloading.showStartedDownload(name));
    }

    public void testShowSuccessDownload(){
        final String name = "TestName";
        final long sizeOfFile = 1583964;
        final long timeOfExecution = 18191683;
        final String expectedResult = "The file " + name + " is downloaded: " + "1,58 MB" + " in " + "5 hours, 3 minutes, 11 seconds, 683 milliseconds";
        assertEquals(expectedResult, OutputStatusDownloading.showSuccessDownload(name, sizeOfFile, timeOfExecution));
    }

    public void testShowFailedDownload(){
        final String name = "TestName";
        final String reasonOfFailure = "TestError";
        final String expectedResult = "Filed downloading file " + name + ".\nReason of failure: " + reasonOfFailure;
        assertEquals(expectedResult, OutputStatusDownloading.showFailedDownload(name, reasonOfFailure));
    }

    public void testShowSuccessfulStatisticsDownloads(){
        final DownloadLogger logger = new DownloadLogger();
        final int numberOfFiles = 5;
        final int totalTimeDownloadingInMilliseconds = 191683;
        final int percent = 100;
        final long sizeInBytes = 191683*5;
        final SizeOfFile size = new SizeOfFile(sizeInBytes);

        final String expectedResult = "Downloaded: " + percent + "%\n" +
                "Downloaded: " + numberOfFiles +" files, " + size.toString() + "\n" +
                "Time of downloading: " + "3 minutes, 11 seconds, 683 milliseconds" + "\n" +
                "Average speed of downloading: " + "5,00kB" + "/s";

        for(int i = 0; i < numberOfFiles; i++)
            logger.addSuccessDownload(new InfoAboutDownloadedFile("a", "a", 191683, 500));
        logger.setTotalTimeDownloadingInMilliseconds(totalTimeDownloadingInMilliseconds);

        assertEquals(expectedResult, OutputStatusDownloading.showStatisticsDownloads(logger));
    }

    public void testShowStatisticsDownloads(){
        final DownloadLogger logger = new DownloadLogger();
        final int numberOfFiles = 5;
        final int totalTimeDownloadingInMilliseconds = 191683;
        final int percent = 50;
        final long sizeInBytes = 191683*5;
        final SizeOfFile size = new SizeOfFile(sizeInBytes);

        final String expectedResult = "Downloaded: " + percent + "%\n" +
                "Downloaded: " + numberOfFiles +" files, " + size.toString() + "\n" +
                "Time of downloading: " + "3 minutes, 11 seconds, 683 milliseconds" + "\n" +
                "Average speed of downloading: " + "5,00kB" + "/s";

        for(int i = 0; i < numberOfFiles; i++)
            logger.addSuccessDownload(new InfoAboutDownloadedFile("a", "a", 191683, 500));

        for(int i = 0; i < numberOfFiles; i++)
            logger.addFailedDownload(new FailedDownload("a", "a", "a"));

        logger.setTotalTimeDownloadingInMilliseconds(totalTimeDownloadingInMilliseconds);

        assertEquals(expectedResult, OutputStatusDownloading.showStatisticsDownloads(logger));
    }

    public void testShowStatisticsFailedDownloads(){
        final DownloadLogger logger = new DownloadLogger();
        final int numberOfFiles = 0;
        final int totalTimeDownloadingInMilliseconds = 191683;
        final int percent = 0;
        final double timeInSeconds = totalTimeDownloadingInMilliseconds/1000;
        final double speedOfDownloads = 0/timeInSeconds;

        final String expectedResult = "Downloaded: " + percent + "%\n" +
                "Downloaded: " + numberOfFiles +" files, " + "0,00 B" + "\n" +
                "Time of downloading: " + "3 minutes, 11 seconds, 683 milliseconds" + "\n" +
                "Average speed of downloading: " + String.format("%.2f", speedOfDownloads) + "B" + "/s";

        for(int i = 0; i < 5; i++)
            logger.addFailedDownload(new FailedDownload("a", "a", "a"));

        logger.setTotalTimeDownloadingInMilliseconds(totalTimeDownloadingInMilliseconds);

        String a = OutputStatusDownloading.showStatisticsDownloads(logger);

        assertEquals(expectedResult, OutputStatusDownloading.showStatisticsDownloads(logger));
    }
}

package packageOfTests.UnitTests;

import com.company.downloadedFiles.parametersOfFiles.TimeOfExecution;
import junit.framework.TestCase;

/**
 * Created by владелец on 03.05.2017.
 */
public class TestTimeOfExecution extends TestCase {

    public void testZeroMilliseconds(){
        final long test_ms = 0;
        final String expectedResult = "0 milliseconds";
        TimeOfExecution timeOfExecution = new TimeOfExecution(test_ms);
        assertEquals(expectedResult, timeOfExecution.toString());
    }

    public void testMilliseconds(){
        final long test_ms = 683;
        String expectedResult = "683 milliseconds";
        TimeOfExecution timeOfExecution = new TimeOfExecution(test_ms);
        assertEquals(expectedResult, timeOfExecution.toString());
    }

    public void testSeconds(){
        final long test_ms = 11683;
        final String expectedResult = "11 seconds, 683 milliseconds";
        TimeOfExecution timeOfExecution = new TimeOfExecution(test_ms);
        assertEquals(expectedResult, timeOfExecution.toString());
    }

    public void testMinutes(){
        final long test_ms = 191683;
        final String expectedResult = "3 minutes, 11 seconds, 683 milliseconds";
        TimeOfExecution timeOfExecution = new TimeOfExecution(test_ms);
        assertEquals(expectedResult, timeOfExecution.toString());
    }

    public void testHours(){
        final long test_ms = 18191683;
        final String expectedResult = "5 hours, 3 minutes, 11 seconds, 683 milliseconds";
        TimeOfExecution timeOfExecution = new TimeOfExecution(test_ms);
        assertEquals(expectedResult, timeOfExecution.toString());
    }

    public void testDays(){
        final long test_ms = 709391683;
        final String expectedResult = "8 days, 5 hours, 3 minutes, 11 seconds, 683 milliseconds";
        TimeOfExecution timeOfExecution = new TimeOfExecution(test_ms);
        assertEquals(expectedResult, timeOfExecution.toString());
    }
}

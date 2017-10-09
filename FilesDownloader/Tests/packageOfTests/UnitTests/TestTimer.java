package packageOfTests.UnitTests;

import com.company.tools.timer.Timer;
import junit.framework.TestCase;

/**
 * Created by user on 01.05.2017.
 */
public class TestTimer extends TestCase {

    public void testMeasurementTime(){
        long expectedTime = 1000;
        long delta = 20;
        Timer timer = new Timer();
        timer.start();
        try {
            Thread.sleep(expectedTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.stop();
        assertTrue((timer.getTimeMilliseconds() >= expectedTime) && (timer.getTimeMilliseconds() <= expectedTime + delta));
    }

    public void testGetTimeAfterStart(){
        long expectedTime = 0;
        Timer timer = new Timer();
        timer.start();
        assertEquals(expectedTime, timer.getTimeMilliseconds());
    }

    public void testDoubleStop(){
        long expectedTime = 1000;
        long delta = 20;
        Timer timer = new Timer();
        timer.start();
        try {
            Thread.sleep(expectedTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.stop();
        timer.stop();
        assertTrue((timer.getTimeMilliseconds() >= expectedTime) && (timer.getTimeMilliseconds() <= expectedTime + delta));
    }
}

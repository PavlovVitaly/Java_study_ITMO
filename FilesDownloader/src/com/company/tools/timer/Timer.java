package com.company.tools.timer;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by user on 01.05.2017.
 * <div>Класс таймера, засекает время выполнения.</div>
 * @note комментирование данного класса не преподносит дополнительной
 * информации и лишь перегружает код.
 */
public class Timer {
    private long timeMilliseconds;
    private Calendar calendar;

    public Timer(){
        timeMilliseconds = 0;
        calendar = null;
    }

    public long getTimeMilliseconds() {
        if (calendar == null)
            return timeMilliseconds;
        return 0;
    }

    public void start(){
        calendar = new GregorianCalendar();
        timeMilliseconds = calendar.getTimeInMillis();
    }

    public void stop(){
        if (calendar != null){
            calendar = new GregorianCalendar();
            timeMilliseconds = calendar.getTimeInMillis() - timeMilliseconds;
            calendar = null;
        }
    }
}

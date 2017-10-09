package com.company.downloadedFiles.parametersOfFiles;

/**
 * Created by владелец on 03.05.2017.
 * <div>Класс для хранения и пересчета времени скачивания файла.</div>
 */
public class TimeOfExecution {
    private static final long thousand = 1000;
    private static final long sixty = 60;
    private static final long hoursInDay = 24;
    private int day;
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;

    /**
     * <div>Конструктор инициализирует объект информацей об времени загрузки файла.</div>
     * @param ms время загрузки файла в миллисекундах.
     */
    public TimeOfExecution(long ms){
        formatTime(ms);
    }

    /**
     * <div>Метод конвертирует время загрузки файла из миллисекунд в максимально возможный формат.</div>
     * @param ms время загрузки файла в миллисекундах.
     */
    public void formatTime(long ms){
        long tempTime;

        tempTime = ms/thousand;
        if (tempTime >= 1) {
            this.milliseconds = (int)(ms % thousand);
        }else {
            this.milliseconds = (int)ms;
            return;
        }

        this.seconds = (int)tempTime;
        if (tempTime/sixty >= 1) {
            this.seconds = (int)(tempTime % sixty);
            tempTime = tempTime/sixty;
        }else {
            return;
        }

        this.minutes = (int)tempTime;
        if (tempTime/sixty >= 1) {
            this.minutes = (int)(tempTime % sixty);
            tempTime = tempTime/sixty;
        }else {
            return;
        }

        this.hours = (int)tempTime;
        if (tempTime/hoursInDay >= 1) {
            this.hours = (int)(tempTime % hoursInDay);
            tempTime = tempTime/hoursInDay;
        }else {
            return;
        }

        this.day = (int)tempTime;
    }

    @Override
    public String toString() {
        String result = "0 milliseconds";

        if (day != 0)
            result = day + " days, "
                    + hours + " hours, "
                    + minutes + " minutes, "
                    + seconds + " seconds, "
                    + milliseconds + " milliseconds";
        else if(hours != 0)
            result = hours + " hours, "
                    + minutes + " minutes, "
                    + seconds + " seconds, "
                    + milliseconds + " milliseconds";
        else if(minutes != 0)
            result = minutes + " minutes, "
                    + seconds + " seconds, "
                    + milliseconds + " milliseconds";
        else if(seconds != 0)
            result = seconds + " seconds, "
                    + milliseconds + " milliseconds";
        else if(milliseconds != 0)
            result = milliseconds + " milliseconds";
        return  result;
    }
}

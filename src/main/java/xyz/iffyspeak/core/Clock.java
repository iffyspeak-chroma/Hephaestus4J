package xyz.iffyspeak.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
    public static String getCurrentTime()
    {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        return format.format(date);
    }

    public static String getCurrentDate()
    {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd-yyyy");
        Date date = new Date();

        return format.format(date);
    }
}

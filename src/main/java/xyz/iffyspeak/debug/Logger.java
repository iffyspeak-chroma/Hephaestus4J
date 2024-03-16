package xyz.iffyspeak.debug;

import xyz.iffyspeak.core.Clock;

public class Logger {

    public static void Info(String msg)
    {
        System.out.println("[INFO] [" + Clock.getCurrentTime() + "] " + msg);
    }

    public static void Warn(String msg)
    {
        System.out.println("[WARN] [" + Clock.getCurrentTime() + "] " + msg);
    }

    public static void Error(String msg)
    {
        System.out.println("[ERROR] [" + Clock.getCurrentTime() + "] " + msg);
    }

}

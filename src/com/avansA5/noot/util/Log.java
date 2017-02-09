package com.avansA5.noot.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log
{
    public static void log(String msg)
    {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        String[] parts = caller.getClassName().split("\\.");
        String className = parts[parts.length - 1];

        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        String stringFormat = "%-40s%s%n";

        System.out.printf(stringFormat, "[" + dateFormat.format(d) + "] (" + className + "::" + caller.getMethodName() + ")", msg);
    }

    public static void error(String msg)
    {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        String[] parts = caller.getClassName().split("\\.");
        String className = parts[parts.length - 1];

        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        String stringFormat = "%-40s%s%n";

        System.err.printf(stringFormat, "[" + dateFormat.format(d) + "] (" + className + "::" + caller.getMethodName() + ")", msg);
    }
}

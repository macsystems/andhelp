package org.andhelp.io;

import android.util.Log;

/**
 * An central Logger which can be used to report log statement.
 * Please use #setTag to customize your log tag
 */
public class Logger
{
    private static volatile boolean allowLogging = true;

    private static String logTag = Logger.class.getSimpleName();


    private Logger()
    {
    }

    public static void setTag(final String loggingTag)
    {
        logTag = loggingTag;
    }

    /**
     * if logging is enabled it will return true
     *
     * @return
     */
    public static boolean isEnabled()
    {
        return allowLogging;
    }

    /**
     * Enable any further log statements.
     */
    public static void enableLogging()
    {
        allowLogging = true;
    }

    /**
     * Disabled any further log statements.
     */
    public static void disableLogging()
    {
        allowLogging = false;
    }

    public static void logE(final String message, final Throwable e)
    {
        if (!allowLogging)
        {
            return;
        }
        Log.e(logTag, message, e);
    }

    public static void logE(final String message)
    {
        if (!allowLogging)
        {
            return;
        }
        logE(message, null);
    }

    public static void logW(final String message, final Throwable e)
    {
        if (!allowLogging)
        {
            return;
        }


        Log.w(logTag, message, e);
    }

    public static void logW(final String message)
    {
        if (!allowLogging)
        {
            return;
        }
        logW(message, null);
    }

    public static void logD(final String message, final Throwable e)
    {
        if (!allowLogging)
        {
            return;
        }
        Log.d(logTag, message, e);
    }

    public static void logD(final String message)
    {
        if (!allowLogging)
        {
            return;
        }
        logD(message, null);
    }

    public static void logWTF(final String message, final Throwable e)
    {
        if (!allowLogging)
        {
            return;
        }
        Log.wtf(logTag, message, e);
    }

    public static void logWTF(final String message)
    {
        if (!allowLogging)
        {
            return;
        }
        logWTF(message, null);
    }
}

package org.andhelp.ui;

import android.os.Looper;

import org.andhelp.exception.IllegalThreadAccessExpection;

/**
 */
public final class UI
{

    private UI()
    {}

    /**
     * Throws an IllegalThreadAccessExpection when the calling thread is not the Main Thread
     * @throws IllegalThreadAccessExpection
     * @see org.andhelp.io.IO#throwIfMainThread()
     */
    public static void throwIfNotMainThread() throws IllegalThreadAccessExpection
    {
        if (Looper.getMainLooper().getThread() != Thread.currentThread())
        {
            throw new IllegalThreadAccessExpection("Only Main Thread allowed: " + Thread.currentThread().getName());
        }
    }
}

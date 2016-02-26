package org.andhelp.io;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;

import org.andhelp.exception.IllegalThreadAccessExpection;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Utility Class for IO related tasks.
 */
public final class IO {

    public static final String NETWORK_NOT_FOUND = "Network not found!";
    /**
     * Eight Kilobyte
     */
    public static final int KBYTE_8 = 1024 * 8;
    /**
     * Sixteen Kilobyte
     */
    public static final int KBYTE_16 = 1024 * 16;
    /**
     * Thirtytwo Kilobyte
     */
    public static final int KBYTE_32 = 1024 * 32;
    /**
     * Sixtyfour Kilobyte
     */
    public static final int KBYTE_64 = 1024 * 64;
    /**
     * One Hundred Twenty Eight Kilobyte
     */
    public static final int KBYTE_128 = 1024 * 128;

    public static final int DEFAULT_BUFFER_SIZE = 1024;
    public static final int DEFAULT_STRING_BUFFER_SIZE = 256;
    private static final String LOG_TAG = IO.class.getSimpleName();

    private IO() {
    }

    /**
     * Returns the Name of the Network Connection eg. WIFI, MOBILE.
     *
     * @param _context
     * @return the name of the network or @link NETWORK_NOT_FOUND
     */
    public static String getNetworkName(@Nonnull final Context _context) {
        final ConnectivityManager systemService = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);

        final StringBuilder builder = new StringBuilder(64);

        if (systemService.getActiveNetworkInfo() != null) {
            builder.append(systemService.getActiveNetworkInfo().getTypeName());
            builder.append(" [").append(systemService.getActiveNetworkInfo().getSubtypeName()).append("]");
        } else {
            builder.append(NETWORK_NOT_FOUND);
        }

        return builder.toString();

    }


    /**
     * Checks if a file exists in directory returned by {@link Context#getFilesDir()} with given name.
     *
     * @param filename
     * @param context
     * @return
     */
    public static boolean existFile(@Nonnull final String filename, @Nonnull final Context context) {
        final File root = context.getFilesDir();
        final File neededFile = new File(root, filename);
        return neededFile.exists();
    }


    /**
     * disconnects connection, parameter can be null.
     *
     * @param connection
     */
    public final static void disconnect(@Nullable final HttpURLConnection connection) {
        if (connection != null) {
            try {
                connection.disconnect();
            } catch (final Exception e) {
                Log.e(LOG_TAG, "Failed to close HttpURLConnection", e);
            }
        }
    }


    /**
     * closes {@link java.io.Closeable}, closeable can be null.
     *
     * @param closeable
     */
    public final static void close(@Nullable final Closeable closeable) {
        if (closeable != null) {

            try {
                closeable.close();
            } catch (final IOException e) {
                Log.e(LOG_TAG, "Failed to close stream", e);
            }
        }
    }


    /**
     * closes {@link java.util.Scanner}, scanner can be null.
     *
     * @param scanner
     */
    public final static void close(@Nullable final Scanner scanner) {
        if (scanner != null) {

            try {
                scanner.close();
            } catch (final IllegalStateException e) {
                Log.e(LOG_TAG, "Failed to close Scanner", e);
            }
        }
    }


    /**
     * Returns an <code>Uri</code> to an Resource in form: android.resource://de.packagename.appname//id
     *
     * @param context
     * @param resourceId
     * @return
     * @see android.net.Uri
     */
    public static Uri getResourceURI(@Nonnull final Context context, final int resourceId) {
        final StringBuilder builder = new StringBuilder(128);
        builder.append("android.resource://");
        builder.append(context.getPackageName());
        builder.append("//");
        builder.append(resourceId);

        return Uri.parse(builder.toString());
    }


    /**
     * closes database, parameter can be <code>null</code>.
     *
     * @param database
     */
    public final static void close(@Nullable final SQLiteDatabase database) {
        if (database != null) {
            database.close();
        }
    }

    /**
     * closes SQLiteHelper, parameter can be <code>null</code>.
     *
     * @param helper
     */
    public final static void close(@Nullable final SQLiteOpenHelper helper) {
        if (helper != null) {
            helper.close();
        }
    }


    /**
     * closes statement, parameter can be <code>null</code>.
     *
     * @param statement
     */
    public final static void close(@Nullable final SQLiteStatement statement) {
        if (statement != null) {
            statement.close();
        }
    }


    /**
     * Reads text from given <code>InputStream</code> into a <code>StringBuffer</code>. Default Char Encoding is used
     * see {@link java.io.InputStreamReader} for more details. *
     *
     * @param inputStream
     * @return
     * @throws IOException
     * @see {@link #asString(java.io.InputStream, int)}
     */
    public static <T> StringBuilder asString(final InputStream inputStream) throws IOException {
        return asString(inputStream, DEFAULT_BUFFER_SIZE);
    }


    /**
     * Reads text from given <code>InputStream</code> into a <code>StringBuffer</code>. Default Char Encoding is used
     * see {@link java.io.InputStreamReader} for more details.
     *
     * @param inputStream
     * @param bufferSize
     * @return
     * @throws IOException
     * @see {@link #asString(InputStream)}
     */
    public static StringBuilder asString(@Nonnull final InputStream inputStream, int bufferSize) throws IOException {
        if (inputStream == null) {
            throw new NullPointerException("InputStream");
        }
        if (bufferSize < 1) {
            bufferSize = DEFAULT_BUFFER_SIZE;
        }

        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream), bufferSize);

        try {
            String line;
            final StringBuilder builder = new StringBuilder(bufferSize);
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder;

        } finally {
            close(reader);
        }
    }


    /**
     * Reads a text file line by line.
     *
     * @param context
     * @param resourceId
     * @return
     * @throws android.content.res.Resources.NotFoundException
     * @throws IOException
     */
    public static List<String> readTextfile(@Nonnull final Context context, final int resourceId) throws Resources.NotFoundException, IOException {
        BufferedReader bufReader = null;
        InputStream inStream = null;
        InputStreamReader inReader = null;
        final List<String> list = new ArrayList<String>();
        try {
            inStream = context.getResources().openRawResource(resourceId);
            inReader = new InputStreamReader(inStream);
            bufReader = new BufferedReader(inReader);

            String line;
            while ((line = bufReader.readLine()) != null) {
                list.add(line);
            }
        } finally {
            close(inStream);
            close(inReader);
            close(bufReader);
        }
        return list;

    }


    /**
     * Closes Cursor.
     *
     * @param _cursor
     */
    public static void close(@Nullable final Cursor _cursor) {
        if (_cursor != null) {
            _cursor.close();
        }

    }


    /**
     * Closes AssetFileDescriptor.
     *
     * @param _assetFileDescriptor
     */
    public static void close(@Nullable final AssetFileDescriptor _assetFileDescriptor) {
        if (_assetFileDescriptor != null) {
            try {
                _assetFileDescriptor.close();
            } catch (final IOException e) {
                Log.e(LOG_TAG, "Failed to close AssetFileDescriptor", e);
            }
        }
    }


    /**
     * Returns a {@link Throwable} as a {@link String}, if {@link Throwable} is <code>null</code> the {@link String}
     * "null" will be returned.
     *
     * @param aThrowable
     * @return
     */
    public static String getStackTrace(@Nonnull final Throwable aThrowable) {
        if (aThrowable == null) {
            return "null";
        }
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }


    /**
     * Throws a Runtime Exception if you call this from main thread
     *
     * @throws org.andhelp.exception.IllegalThreadAccessExpection
     * @see org.andhelp.ui.UI#throwIfNotMainThread()
     */
    public static void throwIfMainThread() throws IllegalThreadAccessExpection {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalThreadAccessExpection("Access on Main Thread not allowed.");
        }

    }

    /**
     * Returns true when called from Main Thread
     *
     * @return
     */
    public static boolean isMainThread() {
        return Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper();

    }
}

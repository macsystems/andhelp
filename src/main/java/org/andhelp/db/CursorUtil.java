package org.andhelp.db;

import android.database.Cursor;
import android.database.DatabaseUtils;


public final class CursorUtil
{

    private CursorUtil()
    {
    }

    /**
     * Returns an String representing the Column data of the Cursor.</br>
     * Cursor can be null which results in an empty string returned
     *
     * @param cursor
     * @return
     */
    public static String logCursorColumnToString(final Cursor cursor)
    {
        if (cursor == null)
        {
            return "";
        }
        return DatabaseUtils.dumpCurrentRowToString(cursor);
    }


    public static String getString(final Cursor cursor, final String rowName)
    {
        return cursor.getString(cursor.getColumnIndexOrThrow(rowName));
    }

    public static long getLong(final Cursor cursor, final String rowName)
    {
        return cursor.getLong(cursor.getColumnIndexOrThrow(rowName));
    }


    public static float getFloat(final Cursor cursor, final String rowName)
    {
        return cursor.getFloat(cursor.getColumnIndexOrThrow(rowName));
    }


    public static double getDouble(final Cursor cursor, final String rowName)
    {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(rowName));
    }


    public static int getInt(final Cursor cursor, final String rowName)
    {
        return cursor.getInt(cursor.getColumnIndexOrThrow(rowName));
    }


    public static byte[] geBlob(final Cursor cursor, final String rowName)
    {
        return cursor.getBlob(cursor.getColumnIndexOrThrow(rowName));
    }
}

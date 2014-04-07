package org.andhelp.db;

import android.content.ContentValues;


/**
 * Maps an Column to an other column. Useful when you need to map virtual table names in an ContentProvider.
 */
public class ColumnMapper
{
    public static enum Type
    {
        BOOLEAN, BYTE, BYTEARRAY, DOUBLE, FLOAT, INTEGER, LONG, SHORT, STRING
    }


    private final String name;

    private final Type type;

    public ColumnMapper(final String columnName, final Type columnType)
    {
        if (columnName == null)
        {
            throw new IllegalArgumentException("Column name can't be null");
        }
        if (columnType == null)
        {
            throw new IllegalArgumentException("Type can't be null.");
        }
        name = columnName;
        type = columnType;
    }

    public void map(final String srcColumn, final ContentValues src, final ContentValues dst)
    {
        switch (type)
        {
            case BOOLEAN:
                dst.put(name, src.getAsBoolean(srcColumn));
                break;
            case BYTE:
                dst.put(name, src.getAsByte(srcColumn));
                break;
            case BYTEARRAY:
                dst.put(name, src.getAsByteArray(srcColumn));
                break;
            case DOUBLE:
                dst.put(name, src.getAsDouble(srcColumn));
                break;
            case FLOAT:
                dst.put(name, src.getAsFloat(srcColumn));
                break;
            case INTEGER:
                dst.put(name, src.getAsInteger(srcColumn));
                break;
            case LONG:
                dst.put(name, src.getAsLong(srcColumn));
                break;
            case SHORT:
                dst.put(name, src.getAsShort(srcColumn));
                break;
            case STRING:
                dst.put(name, src.getAsString(srcColumn));
                break;
            default:
                throw new IllegalArgumentException("Type not supported:" + type + " for column:" + srcColumn);
        }

    }
}

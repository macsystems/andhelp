package org.andhelp.db;

import android.content.ContentValues;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Maps an Column to an other column. Useful when you need to map virtual table names in an ContentProvider.
 */
public class ColumnMapper {

    public static final int BOOLEAN = 0;
    public static final int BYTE = 1;
    public static final int BYTEARRAY = 2;
    public static final int DOUBLE = 3;
    public static final int FLOAT = 4;
    public static final int INTEGER = 5;
    public static final int LONG = 6;
    public static final int SHORT = 7;
    public static final int STRING = 8;

    private final String name;
    private final int type;

    public ColumnMapper(@NonNull final String columnName, @Type final int columnType) {
        if (columnName == null) {
            throw new IllegalArgumentException("Column name can't be null");
        }
        name = columnName;
        type = columnType;
    }

    public void map(@NonNull final String srcColumn, @NonNull final ContentValues src, @NonNull final ContentValues dst) {
        switch (type) {
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

    @IntDef(flag = false, value = {BOOLEAN, BYTE, BYTEARRAY, DOUBLE, FLOAT, INTEGER, LONG, SHORT, STRING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }
}

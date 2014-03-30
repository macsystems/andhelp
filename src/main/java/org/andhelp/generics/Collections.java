package org.andhelp.generics;


import android.support.v4.util.ArrayMap;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Helper Class for Generic boilerplate code
 */
public final class Collections
{

    private Collections()
    {
    }


    public static <E> SparseArray<E> createSparseArray()
    {
        return new SparseArray<E>();
    }

    public static <E> SparseArray<E> createSparseArray(final int initialCapacity)
    {
        return new SparseArray<E>(initialCapacity);
    }


    public static <T> List<T> createList()
    {
        return new ArrayList<T>();
    }


    public static <T> List<T> createList(final int initialCapacity)
    {
        return new ArrayList<T>(initialCapacity);
    }


    public static <K, V> Map<K, V> createArrayMap()
    {
        return new ArrayMap<K, V>();
    }


    public static <K, V> Map<K, V> createArrayMap( final int initialCapacity)
    {
        return new ArrayMap<K, V>(initialCapacity);
    }


    public static <K, V> Map<K, V> createMap()
    {
        return new HashMap<K, V>();
    }


    public static <K, V> Map<K, V> createMap( final int initialCapacity)
    {
        return new HashMap<K, V>(initialCapacity);
    }

}

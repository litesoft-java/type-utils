package org.litesoft.utils;

/**
 * Small utility to force cast something when your really, REALLY sure that you know better!
 */
@SuppressWarnings("unchecked")
public class Cast {
    public static <T> T it( Object o ) {
        return (T)o;
    }
}

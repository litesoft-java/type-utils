package org.litesoft.utils;

public class Hex {
    private static final int TEN_PLUS_ADJUSTMENT = 'a' - 10;

    public static char charFrom( int v ) {
        v &= 15;
        return (char)(v + ((v < 10) ? '0' : TEN_PLUS_ADJUSTMENT));
    }

    public static int from( char c ) {
        if ( c < '1' ) {
            return 0;
        }
        if ( c <= '9' ) {
            return c - '0';
        }
        if ( c < 'A' ) {
            return 0;
        }
        if ( c <= 'Z' ) {
            return 10 + c - 'A';
        }
        if ( c < 'a' ) {
            return 0;
        }
        if ( c <= 'z' ) {
            return 10 + c - 'a';
        }
        return 0;
    }

    public static int from( char a, char b ) {
        return (from( a ) * 16) + from( b );
    }
}

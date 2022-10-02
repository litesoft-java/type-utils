package org.litesoft.utils;

import org.litesoft.annotations.Significant;

@SuppressWarnings("unused")
public class EnumCaseInsensitive {
    public interface additionalNameMatcher {
        boolean isMatch( String name1, String name2 );
    }

    public static <T extends Enum<T>> T internalParse( T[] values, String value ) {
        return internalParse( values, value, null, null );
    }

    public static <T extends Enum<T>> T internalParse( T[] values, String value, T defaultValue ) {
        return internalParse( values, value, defaultValue, null );
    }

    public static <T extends Enum<T>> T internalParse( T[] values, String value, additionalNameMatcher matcher ) {
        return internalParse( values, value, null, matcher );
    }

    public static <T extends Enum<T>> T internalParse( T[] values, String value, T defaultValue, additionalNameMatcher matcher ) {
        value = Significant.ConstrainTo.valueOrNull( value );
        if ( value != null ) {
            for ( T option : values ) {
                String name = option.name();
                if ( name.equalsIgnoreCase( value ) || ((matcher != null) && matcher.isMatch(name, value))) {
                    return option;
                }
            }
        }
        return defaultValue;
    }

    public static boolean firstCharacterEqualsIgnoreCase( String name1, String name2 ) {
        return extractFirstN( name1, 1 ).equalsIgnoreCase( extractFirstN( name2, 1 ) );
    }

    @SuppressWarnings("SameParameterValue")
    private static String extractFirstN( String str, int length) {
        if (str != null) {
            if (str.length() == length) {
                return str;
            }
            if (length < str.length()) {
                return str.substring( 0, length );
            }
        }
        return "";
    }
}

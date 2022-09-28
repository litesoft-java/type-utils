package org.litesoft.utils;

@SuppressWarnings("unused")
public class StringUtils {
    public static String toErrorMsg( Exception e ) {
        if ( e == null ) {
            return null;
        }
        String msg = normalizeToNull( e.getMessage() );
        return (msg != null) ? msg : e.getClass().getSimpleName();
    }

    public static String normalizeToNull( String value ) {
        if ( value != null ) {
            value = value.trim();
            if ( value.isEmpty() ) {
                value = null;
            }
        }
        return value;
    }

    public static String normalizeToEmpty( String value ) {
        return (value == null) ? "" : value.trim();
    }

    public static String options( String labelSingularButPluralWithAnS, Object[] validOptions ) {
        return options( labelSingularButPluralWithAnS, labelSingularButPluralWithAnS + "s", validOptions );
    }

    public static String options( String labelSingular, String labelPlural, Object[] validOptions ) {
        return switch ( count( validOptions ) ) {
            case 0 -> "no " + labelPlural;
            case 1 -> "the " + labelSingular + " is: " + options( validOptions );
            default -> "the " + labelPlural + " are: " + options( validOptions );
        };
    }

    public static String options( Object[] validOptions ) {
        return (0 == count( validOptions )) ? "" : optionsNotEmpty( validOptions );
    }

    private static int count( Object[] array ) {
        return (array == null) ? 0 : array.length;
    }

    private static String optionsNotEmpty( Object[] validOptions ) {
        StringBuilder sb = new StringBuilder();
        if ( validOptions.length > 1 ) {
            for ( int i = validOptions.length; --i > 0; ) {
                append( sb, validOptions[i] ).append( ", " );
            }
            sb.append( "or " );
        }
        return append( sb, validOptions[0] ).toString();
    }

    private static StringBuilder append( StringBuilder sb, Object value ) {
        if ( value == null ) {
            return sb.append( "null" );
        }
        boolean wasString = value instanceof String;
        String str = value.toString();
        if ( str == null ) {
            return sb.append( value.getClass().getSimpleName() );
        }
        String wrapper = wasString ? "'" : "";
        return sb.append( wrapper ).append( str ).append( wrapper );
    }
}

package org.litesoft.utils;

import org.litesoft.annotations.NotNull;
import org.litesoft.annotations.Significant;

/**
 * Class that provides the ability to substitute indexed (0-n) data into a format string (which may not be null or blank).
 * <p>
 * The indexed data reference is between ".|" and "|.", e.g a reference to the first indexed data would be ".|0|.".
 * <p>
 * Note: any indexed data reference that can not be applied to the <code>indexedFmtData</code> array will have the vertical bars replaced with question marks, e.g. ".|FRED|." will become ".?FRED?.".
 * Note: all unmatched indexed data reference starts ".|" and ends "|." are ignored!
 */
public class TemplatedMessage {
    public static final String INDEXED_DATA_REFERENCE_START = ".|";
    public static final String INDEXED_DATA_REFERENCE_END = "|.";
    public static final String ERROR_REPLACEMENT_INDEXED_DATA_REFERENCE_START = ".?";
    public static final String ERROR_REPLACEMENT_INDEXED_DATA_REFERENCE_END = "?.";
    private static final String[] EMPTY_ARRAY = new String[0];

    private String fmtString;
    private String[] indexedFmtData;
    private String toStringOutput;

    public TemplatedMessage( String fmtString, String... indexedFmtData ) {
        replaceFmtString( fmtString );
        replaceIndexedFmtData( indexedFmtData );
    }

    public String getFmtString() {
        return fmtString;
    }

    public String[] getIndexedFmtData() {
        return indexedFmtData;
    }

    public TemplatedMessage replaceFmtString( String fmtString ) {
        this.fmtString = Significant.AssertArgument.namedValue( "fmtString", fmtString );
        toStringOutput = null;
        return this;
    }

    public TemplatedMessage replaceIndexedFmtData( String... indexedFmtData ) {
        this.indexedFmtData = NotNull.ConstrainTo.valueOr( indexedFmtData, EMPTY_ARRAY );
        toStringOutput = null;
        return this;
    }

    public String toString() {
        if ( toStringOutput == null ) {
            toStringOutput = applyTemplating();
        }
        return toStringOutput;
    }

    protected String applyTemplating() {
        int endAt = fmtString.indexOf( INDEXED_DATA_REFERENCE_END );
        if ( endAt == -1 ) {
            return fmtString;
        }
        String toProcess = fmtString;
        StringBuilder sb = new StringBuilder();
        do {
            String before = toProcess.substring( 0, endAt );
            toProcess = toProcess.substring( endAt + INDEXED_DATA_REFERENCE_END.length() );
            int startAt = before.lastIndexOf( INDEXED_DATA_REFERENCE_START );
            if ( startAt == -1 ) { // no start -- pass it thru
                sb.append( before ).append( INDEXED_DATA_REFERENCE_END );
            } else {
                sb.append( before, 0, startAt );
                String reference = before.substring( startAt + INDEXED_DATA_REFERENCE_START.length() );
                int index = convertReference( reference );
                if ( (0 <= index) && (index < indexedFmtData.length) ) { // good reference
                    sb.append( indexedFmtData[index] ); // substitute
                } else { // bad reference
                    sb.append( ERROR_REPLACEMENT_INDEXED_DATA_REFERENCE_START )
                            .append( reference ) // wrap with Error indicators!
                            .append( ERROR_REPLACEMENT_INDEXED_DATA_REFERENCE_END );
                }
            }
            endAt = toProcess.indexOf( INDEXED_DATA_REFERENCE_END );
        } while ( endAt != -1 );
        return sb.append( toProcess ).toString();
    }

    private static int convertReference( String reference ) {
        reference = reference.trim();
        if ( !reference.isEmpty() && Character.isDigit( reference.charAt( 0 ) ) ) {
            try {
                return Integer.parseInt( reference );
            }
            catch ( NumberFormatException expectedAndIgnored ) {
                // fall thru
            }
        }
        return -1;
    }
}

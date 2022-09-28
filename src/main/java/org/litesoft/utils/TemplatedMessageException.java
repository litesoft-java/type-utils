package org.litesoft.utils;

import org.litesoft.annotations.NotNull;

/**
 * Class that provides the ability to substitute indexed (0-n) data into a format string (which may not be null or blank).
 * <p>
 * The indexed data reference is between ".|" and "|.", e.g a reference to the first indexed data would be ".|0|.".
 * <p>
 * Note: any indexed data reference that can not be applied to the <code>indexedFmtData</code> array will have the vertical bars replaced with question marks, e.g. ".|FRED|." will become ".?FRED?.".
 * Note: all unmatched indexed data reference starts ".|" and ends "|." are ignored!
 */
public class TemplatedMessageException extends RuntimeException {
    private final TemplatedMessage templatedMessage;

    public TemplatedMessageException( TemplatedMessage templatedMessage ) {
        super( NotNull.AssertArgument.namedValue( "templatedMessage", templatedMessage ).toString() );
        this.templatedMessage = templatedMessage;
    }

    public TemplatedMessageException( String fmtString, String... indexedFmtData ) {
        this( new TemplatedMessage( fmtString, indexedFmtData ) );
    }

    public TemplatedMessage getTemplatedMessage() {
        return templatedMessage;
    }

    @Override
    public String getMessage() {
        return templatedMessage.toString();
    }
}

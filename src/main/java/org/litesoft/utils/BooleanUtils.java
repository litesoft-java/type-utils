package org.litesoft.utils;

import org.litesoft.annotations.NotNull;

@SuppressWarnings("unused")
public class BooleanUtils {
    public static Boolean deNull(Boolean value) {
        return NotNull.ConstrainTo.valueOr( value, Boolean.FALSE );
    }

    public static Boolean falseToNull(Boolean value) {
        return Boolean.FALSE.equals( value ) ? null : value;
    }

    public static Boolean trueToNull(Boolean value) {
        return Boolean.TRUE.equals( value ) ? null : value;
    }
}

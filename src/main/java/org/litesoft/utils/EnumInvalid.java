package org.litesoft.utils;

public interface EnumInvalid<T extends Enum<T>> {
    String simpleEnumName();

    default boolean isInvalid() {
        T option = Cast.it( this );
        return "Invalid".equalsIgnoreCase( option.name() ); // name == toString
    }

    default boolean isDeprecated() {
        return false;
    }

    default void checkAcceptable() {
        EnumInvalid<T> option = Cast.it( this );
        if ( isInvalid() ) {
            throw new TemplatedMessageException( option.simpleEnumName() + " is Invalid" );
        }
        if ( option.isDeprecated() ) {
            throw new TemplatedMessageException( option.simpleEnumName() + " .|0|. is deprecated", option.toString() ); // toString == name
        }
    }
}

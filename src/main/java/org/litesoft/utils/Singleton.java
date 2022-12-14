package org.litesoft.utils;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class Singleton<T> {
    private final Object[] INDIRECT_INSTANCE_REF = new Object[1];
    private final Supplier<T> constructor;

    public Singleton( Supplier<T> constructor ) {
        this.constructor = constructor;
    }

    public T getInstance() {
        synchronized ( INDIRECT_INSTANCE_REF ) {
            T instance = getArrayEntry();
            if ( instance == null ) {
                instance = constructor.get();
                INDIRECT_INSTANCE_REF[0] = instance;
            }
            return instance;
        }
    }

    private T getArrayEntry() {
        return Cast.it( INDIRECT_INSTANCE_REF[0] );
    }
}


package org.litesoft.utils;

import java.util.function.Supplier;

/**
 * A Supplier (proxy with Caching) - NOT Thread Safe!
 */
public class SupplierWithCaching<T> implements Supplier<T> {
    private final Supplier<T> supplier;
    private T value;
    private boolean populated;

    public SupplierWithCaching( Supplier<T> pSupplier ) {
        supplier = pSupplier;
    }

    @Override
    public T get() {
        if ( !populated ) {
            populated = true;
            value = supplier.get();
        }
        return value;
    }
}

package io.github.ititus.data;

import java.util.Objects;
import java.util.function.Supplier;

public final class Lazy<T> implements Supplier<T> {

    private final Supplier<? extends T> supplier;

    private T cache;

    private Lazy(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (cache == null) {
            cache = Objects.requireNonNull(supplier.get());
        }

        return cache;
    }

    public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }
}
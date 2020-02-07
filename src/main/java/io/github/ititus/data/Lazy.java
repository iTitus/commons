package io.github.ititus.data;

import java.util.Objects;
import java.util.function.Supplier;

public final class Lazy<T> implements Supplier<T> {

    private final Supplier<T> supplier;

    private T cache;

    private Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (cache == null) {
            cache = Objects.requireNonNull(supplier.get());
        }

        return cache;
    }

    public static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }
}

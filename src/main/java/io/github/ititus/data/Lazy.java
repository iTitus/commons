package io.github.ititus.data;

import java.util.Objects;
import java.util.function.Supplier;

public final class Lazy<T> implements Supplier<T> {

    private final Supplier<? extends T> supplier;
    private transient T cache;

    private Lazy(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }

    @Override
    public T get() {
        if (cache == null) {
            cache = Objects.requireNonNull(supplier.get());
        }

        return cache;
    }
}

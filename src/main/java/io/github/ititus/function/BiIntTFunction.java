package io.github.ititus.function;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface BiIntTFunction<T, R> {

    R apply(int i, T t);

    default <V> BiIntTFunction<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (i, t) -> after.apply(apply(i, t));
    }
}

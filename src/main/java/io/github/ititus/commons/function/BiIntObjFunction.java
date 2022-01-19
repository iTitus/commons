package io.github.ititus.commons.function;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface BiIntObjFunction<T, R> {

    R apply(int i, T t);

    default <V> BiIntObjFunction<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (i, t) -> after.apply(apply(i, t));
    }
}

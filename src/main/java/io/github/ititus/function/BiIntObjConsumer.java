package io.github.ititus.function;

import java.util.Objects;

@FunctionalInterface
public interface BiIntObjConsumer<T> {

    void accept(int i, T t);

    default BiIntObjConsumer<T> andThen(BiIntObjConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (i, t) -> {
            accept(i, t);
            after.accept(i, t);
        };
    }
}

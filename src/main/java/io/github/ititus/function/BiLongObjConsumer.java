package io.github.ititus.function;

import java.util.Objects;

@FunctionalInterface
public interface BiLongObjConsumer<T> {

    void accept(long l, T t);

    default BiLongObjConsumer<T> andThen(BiLongObjConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (l, t) -> {
            accept(l, t);
            after.accept(l, t);
        };
    }
}

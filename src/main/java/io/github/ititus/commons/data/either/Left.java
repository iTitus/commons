package io.github.ititus.commons.data.either;

import io.github.ititus.commons.data.ObjectUtil;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

final class Left<L, R> implements Either<L, R> {

    private final L value;

    Left(L value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Optional<L> left() {
        return Optional.of(value);
    }

    @Override
    public Optional<R> right() {
        return Optional.empty();
    }

    @Override
    public <T> T map(Function<? super L, ? extends T> lf, Function<? super R, ? extends T> rf) {
        return lf.apply(value);
    }

    @Override
    public <T, U> Either<T, U> flatMap(Function<? super L, ? extends Either<T, U>> lf, Function<? super R, ? extends Either<T, U>> rf) {
        return lf.apply(value);
    }

    @Override
    public <T, U> Either<T, U> mapBoth(Function<? super L, ? extends T> lf, Function<? super R, ? extends U> rf) {
        return Either.left(lf.apply(value));
    }

    @Override
    public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> f) {
        return Either.left(f.apply(value));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Either<L, T> mapRight(Function<? super R, ? extends T> f) {
        return (Either<L, T>) this;
    }

    @Override
    public <T> Either<T, R> flatMapLeft(Function<? super L, ? extends Either<T, R>> f) {
        return f.apply(value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Either<L, T> flatMapRight(Function<? super R, ? extends Either<L, T>> f) {
        return (Either<L, T>) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Left)) {
            return false;
        }

        Left<?, ?> left = (Left<?, ?>) o;
        return value.equals(left.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "Left<" + value + '>';
    }

    @Override
    public String deepToString() {
        return "Left<" + ObjectUtil.deepToString(value) + ">";
    }
}

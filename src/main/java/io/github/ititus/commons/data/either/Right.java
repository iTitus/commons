package io.github.ititus.commons.data.either;

import io.github.ititus.commons.data.ObjectUtil;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

final class Right<L, R> implements Either<L, R> {

    private final R value;

    Right(R value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Optional<L> left() {
        return Optional.empty();
    }

    @Override
    public Optional<R> right() {
        return Optional.of(value);
    }

    @Override
    public <T> T map(Function<? super L, ? extends T> lf, Function<? super R, ? extends T> rf) {
        return rf.apply(value);
    }

    @Override
    public <T, U> Either<T, U> flatMap(Function<? super L, ? extends Either<T, U>> lf, Function<? super R, ? extends Either<T, U>> rf) {
        return rf.apply(value);
    }

    @Override
    public <T, U> Either<T, U> mapBoth(Function<? super L, ? extends T> lf, Function<? super R, ? extends U> rf) {
        return Either.right(rf.apply(value));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> f) {
        return (Either<T, R>) this;
    }

    @Override
    public <T> Either<L, T> mapRight(Function<? super R, ? extends T> f) {
        return Either.right(f.apply(value));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Either<T, R> flatMapLeft(Function<? super L, ? extends Either<T, R>> f) {
        return (Either<T, R>) this;
    }

    @Override
    public <T> Either<L, T> flatMapRight(Function<? super R, ? extends Either<L, T>> f) {
        return f.apply(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Right<?, ?>)) {
            return false;
        }

        Right<?, ?> right = (Right<?, ?>) o;
        return value.equals(right.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "Right<" + value + '>';
    }

    @Override
    public String deepToString() {
        return "Right<" + ObjectUtil.deepToString(value) + ">";
    }
}

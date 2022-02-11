package io.github.ititus.commons.data.either;

import io.github.ititus.commons.data.DeepToString;

import java.util.Optional;
import java.util.function.Function;

public sealed interface Either<L, R> extends DeepToString permits Left, Right {

    static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }

    Optional<L> left();

    Optional<R> right();

    default boolean isLeft() {
        return left().isPresent();
    }

    default boolean isRight() {
        return right().isPresent();
    }

    default L tryGetLeft() {
        return left().orElseThrow();
    }

    default R tryGetRight() {
        return right().orElseThrow();
    }

    <T> T map(Function<? super L, ? extends T> lf, Function<? super R, ? extends T> rf);

    <T, U> Either<T, U> flatMap(Function<? super L, ? extends Either<T, U>> lf, Function<? super R, ? extends Either<T, U>> rf);

    <T, U> Either<T, U> mapBoth(Function<? super L, ? extends T> lf, Function<? super R, ? extends U> rf);

    <T> Either<T, R> mapLeft(Function<? super L, ? extends T> f);

    <T> Either<L, T> mapRight(Function<? super R, ? extends T> f);

    <T> Either<T, R> flatMapLeft(Function<? super L, ? extends Either<T, R>> f);

    <T> Either<L, T> flatMapRight(Function<? super R, ? extends Either<L, T>> f);

}

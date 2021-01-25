package io.github.ititus.function;

import java.util.Objects;

@FunctionalInterface
public interface BooleanPredicate {

    BooleanPredicate IDENTITY = b -> b;
    BooleanPredicate INVERSE = b -> !b;
    BooleanPredicate ALWAYS_TRUE = b -> true;
    BooleanPredicate ALWAYS_FALSE = b -> false;

    boolean test(boolean b);

    default BooleanPredicate and(BooleanPredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) && other.test(value);
    }

    default BooleanPredicate negate() {
        return (value) -> !test(value);
    }

    default BooleanPredicate or(BooleanPredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) || other.test(value);
    }
}

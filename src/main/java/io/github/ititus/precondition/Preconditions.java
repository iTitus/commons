package io.github.ititus.precondition;

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public final class Preconditions {

    private Preconditions() {
    }

    public static void check(boolean value, BooleanPrecondition precondition) {
        Objects.requireNonNull(precondition);
        doCheck(() -> precondition.test(value),
                () -> precondition.getFailDescription(value));
    }

    public static void check(byte value, IntPrecondition precondition) {
        check((int) value, precondition);
    }

    public static void check(short value, IntPrecondition precondition) {
        check((int) value, precondition);
    }

    public static void check(int value, IntPrecondition precondition) {
        Objects.requireNonNull(precondition);
        doCheck(() -> precondition.test(value),
                () -> precondition.getFailDescription(value));
    }

    public static void check(long value, LongPrecondition precondition) {
        Objects.requireNonNull(precondition);
        doCheck(() -> precondition.test(value),
                () -> precondition.getFailDescription(value));
    }

    public static <T> void check(T value, Precondition<T> precondition) {
        Objects.requireNonNull(precondition);
        doCheck(() -> precondition.test(value), () -> precondition.getFailDescription(value));
    }

    private static void doCheck(BooleanSupplier precondition, Supplier<String> failDescription) {
        Objects.requireNonNull(precondition);
        Objects.requireNonNull(failDescription);

        boolean result;
        try {
            result = precondition.getAsBoolean();
        } catch (Exception e) {
            throw new PreconditionViolatedException("exception while checking precondition", e);
        }

        if (!result) {
            throw new PreconditionViolatedException(failDescription.get());
        }
    }
}

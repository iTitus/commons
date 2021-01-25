package io.github.ititus.function;

@FunctionalInterface
public interface BooleanFunction<R> {

    R apply(boolean value);

}

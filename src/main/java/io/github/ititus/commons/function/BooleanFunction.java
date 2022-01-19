package io.github.ititus.commons.function;

@FunctionalInterface
public interface BooleanFunction<R> {

    R apply(boolean value);

}

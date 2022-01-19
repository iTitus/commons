package io.github.ititus.commons.math.number;

import io.github.ititus.commons.converter.NumberConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.ititus.commons.math.number.JavaMath.gcd;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class JavaMathTests {

    static Stream<Arguments> test_gcd() {
        return Stream.of(
                arguments(0, 0, 0),
                arguments(0, 1, 1),
                arguments(0, -1, 1),
                arguments(0, 2, 2),
                arguments(0, -2, 2),
                arguments(0, 21, 21),
                arguments(0, -21, 21),
                arguments(0, 36, 36),
                arguments(0, -36, 36),
                arguments(0, 42, 42),
                arguments(0, -42, 42),
                arguments(1, 0, 1),
                arguments(1, 1, 1),
                arguments(1, -1, 1),
                arguments(1, 2, 1),
                arguments(1, -2, 1),
                arguments(1, 21, 1),
                arguments(1, -21, 1),
                arguments(1, 36, 1),
                arguments(1, -36, 1),
                arguments(1, 42, 1),
                arguments(1, -42, 1),
                arguments(-1, 0, 1),
                arguments(-1, 1, 1),
                arguments(-1, -1, 1),
                arguments(-1, 2, 1),
                arguments(-1, -2, 1),
                arguments(-1, 21, 1),
                arguments(-1, -21, 1),
                arguments(-1, 36, 1),
                arguments(-1, -36, 1),
                arguments(-1, 42, 1),
                arguments(-1, -42, 1),
                arguments(2, 0, 2),
                arguments(2, 1, 1),
                arguments(2, -1, 1),
                arguments(2, 2, 2),
                arguments(2, -2, 2),
                arguments(2, 21, 1),
                arguments(2, -21, 1),
                arguments(2, 36, 2),
                arguments(2, -36, 2),
                arguments(2, 42, 2),
                arguments(2, -42, 2),
                arguments(-2, 0, 2),
                arguments(-2, 1, 1),
                arguments(-2, -1, 1),
                arguments(-2, 2, 2),
                arguments(-2, -2, 2),
                arguments(-2, 21, 1),
                arguments(-2, -21, 1),
                arguments(-2, 36, 2),
                arguments(-2, -36, 2),
                arguments(-2, 42, 2),
                arguments(-2, -42, 2),
                arguments(21, 0, 21),
                arguments(21, 1, 1),
                arguments(21, -1, 1),
                arguments(21, 2, 1),
                arguments(21, -2, 1),
                arguments(21, 21, 21),
                arguments(21, -21, 21),
                arguments(21, 36, 3),
                arguments(21, -36, 3),
                arguments(21, 42, 21),
                arguments(21, -42, 21),
                arguments(-21, 0, 21),
                arguments(-21, 1, 1),
                arguments(-21, -1, 1),
                arguments(-21, 2, 1),
                arguments(-21, -2, 1),
                arguments(-21, 21, 21),
                arguments(-21, -21, 21),
                arguments(-21, 36, 3),
                arguments(-21, -36, 3),
                arguments(-21, 42, 21),
                arguments(-21, -42, 21),
                arguments(36, 0, 36),
                arguments(36, 1, 1),
                arguments(36, -1, 1),
                arguments(36, 2, 2),
                arguments(36, -2, 2),
                arguments(36, 21, 3),
                arguments(36, -21, 3),
                arguments(36, 36, 36),
                arguments(36, -36, 36),
                arguments(36, 42, 6),
                arguments(36, -42, 6),
                arguments(-36, 0, 36),
                arguments(-36, 1, 1),
                arguments(-36, -1, 1),
                arguments(-36, 2, 2),
                arguments(-36, -2, 2),
                arguments(-36, 21, 3),
                arguments(-36, -21, 3),
                arguments(-36, 36, 36),
                arguments(-36, -36, 36),
                arguments(-36, 42, 6),
                arguments(-36, -42, 6),
                arguments(42, 0, 42),
                arguments(42, 1, 1),
                arguments(42, -1, 1),
                arguments(42, 2, 2),
                arguments(42, -2, 2),
                arguments(42, 21, 21),
                arguments(42, -21, 21),
                arguments(42, 36, 6),
                arguments(42, -36, 6),
                arguments(42, 42, 42),
                arguments(42, -42, 42),
                arguments(-42, 0, 42),
                arguments(-42, 1, 1),
                arguments(-42, -1, 1),
                arguments(-42, 2, 2),
                arguments(-42, -2, 2),
                arguments(-42, 21, 21),
                arguments(-42, -21, 21),
                arguments(-42, 36, 6),
                arguments(-42, -36, 6),
                arguments(-42, 42, 42),
                arguments(-42, -42, 42)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void test_gcd(
            @ConvertWith(NumberConverter.class) int a,
            @ConvertWith(NumberConverter.class) int b,
            @ConvertWith(NumberConverter.class) int expected) {
        int actual = gcd(a, b);
        assertThat(actual).isEqualTo(expected);
    }
}

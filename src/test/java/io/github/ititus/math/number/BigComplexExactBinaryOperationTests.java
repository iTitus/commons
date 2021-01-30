package io.github.ititus.math.number;

import io.github.ititus.converter.NumberConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigComplex.of;
import static io.github.ititus.math.number.BigComplexConstants.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BigComplexExactBinaryOperationTests {

    private static final BigComplex ONE_I_TWO = of("1 + 2i");
    private static final BigComplex TWO_I_TWO = of("2 + 2i");

    static Stream<Arguments> test_add() {
        return Stream.of(
                arguments(ZERO, ZERO, ZERO),
                arguments(ZERO, ONE, ONE),
                arguments(ZERO, MINUS_ONE, MINUS_ONE),
                arguments(ZERO, I, I),
                arguments(ZERO, MINUS_I, MINUS_I),
                arguments(ONE, ZERO, ONE),
                arguments(ONE, ONE, TWO),
                arguments(ONE, MINUS_ONE, ZERO),
                arguments(ONE, I, "1 + i"),
                arguments(ONE, MINUS_I, "1 - i"),
                arguments(MINUS_ONE, ZERO, MINUS_ONE),
                arguments(MINUS_ONE, ONE, ZERO),
                arguments(MINUS_ONE, MINUS_ONE, MINUS_TWO),
                arguments(MINUS_ONE, I, "-1 + i"),
                arguments(MINUS_ONE, MINUS_I, "-1 - i"),
                arguments(I, ZERO, I),
                arguments(I, ONE, "1 + i"),
                arguments(I, MINUS_ONE, "-1 + i"),
                arguments(I, I, TWO_I),
                arguments(I, MINUS_I, ZERO),
                arguments(MINUS_I, ZERO, MINUS_I),
                arguments(MINUS_I, ONE, "1 - i"),
                arguments(MINUS_I, MINUS_ONE, "-1 - i"),
                arguments(MINUS_I, I, ZERO),
                arguments(MINUS_I, MINUS_I, MINUS_TWO_I),
                arguments(ONE_I_TWO, TWO_I_TWO, "3 + 4i")
        );
    }

    static Stream<Arguments> test_subtract() {
        return Stream.of(
                arguments(ZERO, ZERO, ZERO),
                arguments(ZERO, ONE, MINUS_ONE),
                arguments(ZERO, MINUS_ONE, ONE),
                arguments(ZERO, I, MINUS_I),
                arguments(ZERO, MINUS_I, I),
                arguments(ONE, ZERO, ONE),
                arguments(ONE, ONE, ZERO),
                arguments(ONE, MINUS_ONE, TWO),
                arguments(ONE, I, "1 - i"),
                arguments(ONE, MINUS_I, "1 + i"),
                arguments(MINUS_ONE, ZERO, MINUS_ONE),
                arguments(MINUS_ONE, ONE, MINUS_TWO),
                arguments(MINUS_ONE, MINUS_ONE, ZERO),
                arguments(MINUS_ONE, I, "-1 - i"),
                arguments(MINUS_ONE, MINUS_I, "-1 + i"),
                arguments(I, ZERO, I),
                arguments(I, ONE, "-1 + i"),
                arguments(I, MINUS_ONE, "1 + i"),
                arguments(I, I, ZERO),
                arguments(I, MINUS_I, TWO_I),
                arguments(MINUS_I, ZERO, MINUS_I),
                arguments(MINUS_I, ONE, "-1 - i"),
                arguments(MINUS_I, MINUS_ONE, "1 - i"),
                arguments(MINUS_I, I, MINUS_TWO_I),
                arguments(MINUS_I, MINUS_I, ZERO),
                arguments(ONE_I_TWO, TWO_I_TWO, MINUS_ONE)
        );
    }

    static Stream<Arguments> test_multiply() {
        return Stream.of(
                arguments(ZERO, ZERO, ZERO),
                arguments(ZERO, ONE, ZERO),
                arguments(ZERO, MINUS_ONE, ZERO),
                arguments(ZERO, I, ZERO),
                arguments(ZERO, MINUS_I, ZERO),
                arguments(ONE, ZERO, ZERO),
                arguments(ONE, ONE, ONE),
                arguments(ONE, MINUS_ONE, MINUS_ONE),
                arguments(ONE, I, I),
                arguments(ONE, MINUS_I, MINUS_I),
                arguments(MINUS_ONE, ZERO, ZERO),
                arguments(MINUS_ONE, ONE, MINUS_ONE),
                arguments(MINUS_ONE, MINUS_ONE, ONE),
                arguments(MINUS_ONE, I, MINUS_I),
                arguments(MINUS_ONE, MINUS_I, I),
                arguments(I, ZERO, ZERO),
                arguments(I, ONE, I),
                arguments(I, MINUS_ONE, MINUS_I),
                arguments(I, I, MINUS_ONE),
                arguments(I, MINUS_I, ONE),
                arguments(MINUS_I, ZERO, ZERO),
                arguments(MINUS_I, ONE, MINUS_I),
                arguments(MINUS_I, MINUS_ONE, I),
                arguments(MINUS_I, I, ONE),
                arguments(MINUS_I, MINUS_I, MINUS_ONE),
                arguments(ONE_I_TWO, TWO_I_TWO, "-2 + 6i")
        );
    }

    static Stream<Arguments> test_divide() {
        return Stream.of(
                arguments(ZERO, ONE, ZERO),
                arguments(ZERO, MINUS_ONE, ZERO),
                arguments(ZERO, I, ZERO),
                arguments(ZERO, MINUS_I, ZERO),
                arguments(ONE, ONE, ONE),
                arguments(ONE, MINUS_ONE, MINUS_ONE),
                arguments(ONE, I, MINUS_I),
                arguments(ONE, MINUS_I, I),
                arguments(MINUS_ONE, ONE, MINUS_ONE),
                arguments(MINUS_ONE, MINUS_ONE, ONE),
                arguments(MINUS_ONE, I, I),
                arguments(MINUS_ONE, MINUS_I, MINUS_I),
                arguments(I, ONE, I),
                arguments(I, MINUS_ONE, MINUS_I),
                arguments(I, I, ONE),
                arguments(I, MINUS_I, MINUS_ONE),
                arguments(MINUS_I, ONE, MINUS_I),
                arguments(MINUS_I, MINUS_ONE, I),
                arguments(MINUS_I, I, MINUS_ONE),
                arguments(MINUS_I, MINUS_I, ONE),
                arguments(ONE_I_TWO, TWO_I_TWO, "3/4 + 1/4 i")
        );
    }

    static Stream<Arguments> test_divide_zero() {
        return Stream.of(
                arguments(ZERO),
                arguments(ONE),
                arguments(MINUS_ONE),
                arguments(I),
                arguments(MINUS_I),
                arguments(ONE_I_TWO)
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_add(@ConvertWith(NumberConverter.class) BigComplex z,
                  @ConvertWith(NumberConverter.class) BigComplex w,
                  @ConvertWith(NumberConverter.class) BigComplex expected) {
        BigComplex actual = z.add(w);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_subtract(@ConvertWith(NumberConverter.class) BigComplex z,
                       @ConvertWith(NumberConverter.class) BigComplex w,
                       @ConvertWith(NumberConverter.class) BigComplex expected) {
        BigComplex actual = z.subtract(w);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_multiply(@ConvertWith(NumberConverter.class) BigComplex z,
                       @ConvertWith(NumberConverter.class) BigComplex w,
                       @ConvertWith(NumberConverter.class) BigComplex expected) {
        BigComplex actual = z.multiply(w);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_divide(@ConvertWith(NumberConverter.class) BigComplex z,
                     @ConvertWith(NumberConverter.class) BigComplex w,
                     @ConvertWith(NumberConverter.class) BigComplex expected) {
        BigComplex actual = z.divide(w);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_divide_zero(@ConvertWith(NumberConverter.class) BigComplex z) {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> z.divide(ZERO));
    }
}

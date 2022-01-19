package io.github.ititus.commons.math.number;

import io.github.ititus.commons.assertions.Assertions;
import io.github.ititus.commons.converter.NumberConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.ititus.commons.math.number.BigRationalConstants.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BigRationalExactBinaryOperationTests {

    static Stream<Arguments> test_add() {
        return Stream.of(
                arguments(ZERO, ZERO, ZERO),
                arguments(ZERO, ONE, ONE),
                arguments(ZERO, MINUS_ONE, MINUS_ONE),
                arguments(ONE, ZERO, ONE),
                arguments(ONE, ONE, TWO),
                arguments(ONE, MINUS_ONE, ZERO),
                arguments(MINUS_ONE, ZERO, MINUS_ONE),
                arguments(MINUS_ONE, MINUS_ONE, MINUS_TWO),
                arguments(MINUS_ONE, ONE, ZERO)
        );
    }

    static Stream<Arguments> test_subtract() {
        return Stream.of(
                arguments(ZERO, ZERO, ZERO),
                arguments(ZERO, ONE, MINUS_ONE),
                arguments(ZERO, MINUS_ONE, ONE),
                arguments(ONE, ZERO, ONE),
                arguments(ONE, ONE, ZERO),
                arguments(ONE, MINUS_ONE, TWO),
                arguments(MINUS_ONE, ZERO, MINUS_ONE),
                arguments(MINUS_ONE, MINUS_ONE, ZERO),
                arguments(MINUS_ONE, ONE, MINUS_TWO)
        );
    }

    static Stream<Arguments> test_multiply() {
        return Stream.of(
                arguments(ZERO, ZERO, ZERO),
                arguments(ZERO, ONE, ZERO),
                arguments(ZERO, MINUS_ONE, ZERO),
                arguments(ONE, ZERO, ZERO),
                arguments(ONE, ONE, ONE),
                arguments(ONE, MINUS_ONE, MINUS_ONE),
                arguments(MINUS_ONE, ZERO, ZERO),
                arguments(MINUS_ONE, MINUS_ONE, ONE),
                arguments(MINUS_ONE, ONE, MINUS_ONE)
        );
    }

    static Stream<Arguments> test_divide() {
        return Stream.of(
                arguments(ZERO, ONE, ZERO),
                arguments(ZERO, MINUS_ONE, ZERO),
                arguments(ONE, ONE, ONE),
                arguments(ONE, MINUS_ONE, MINUS_ONE),
                arguments(MINUS_ONE, MINUS_ONE, ONE),
                arguments(MINUS_ONE, ONE, MINUS_ONE)
        );
    }

    static Stream<Arguments> test_divide_zero() {
        return Stream.of(
                arguments(ZERO),
                arguments(ONE),
                arguments(MINUS_ONE)
        );
    }

    static Stream<Arguments> test_pow_exact() {
        return Stream.of(
                arguments(ZERO, ZERO, ONE),
                arguments(ZERO, ONE, ZERO),
                arguments(ZERO, MINUS_ONE, ZERO),
                arguments(ONE, ZERO, ONE),
                arguments(ONE, ONE, ONE),
                arguments(ONE, MINUS_ONE, ONE),
                arguments(MINUS_ONE, ZERO, ONE),
                arguments(MINUS_ONE, MINUS_ONE, MINUS_ONE),
                arguments(MINUS_ONE, ONE, MINUS_ONE)
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_add(@ConvertWith(NumberConverter.class) BigRational r,
                  @ConvertWith(NumberConverter.class) BigRational q,
                  @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.add(q);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_subtract(@ConvertWith(NumberConverter.class) BigRational r,
                       @ConvertWith(NumberConverter.class) BigRational q,
                       @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.subtract(q);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_multiply(@ConvertWith(NumberConverter.class) BigRational r,
                       @ConvertWith(NumberConverter.class) BigRational q,
                       @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.multiply(q);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_divide(@ConvertWith(NumberConverter.class) BigRational r,
                     @ConvertWith(NumberConverter.class) BigRational q,
                     @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.divide(q);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_divide_zero(@ConvertWith(NumberConverter.class) BigRational r) {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> r.divide(ZERO));
    }

    @ParameterizedTest
    @MethodSource
    void test_pow_exact(@ConvertWith(NumberConverter.class) BigRational r,
                        @ConvertWith(NumberConverter.class) BigRational q,
                        @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.pow(q);
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}

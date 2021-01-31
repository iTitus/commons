package io.github.ititus.math.number;

import io.github.ititus.converter.NumberConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BigRationalExactUnaryOperationTests {

    static Stream<Arguments> test_negate() {
        return Stream.of(
                arguments(ZERO, ZERO),
                arguments(ONE, MINUS_ONE),
                arguments(MINUS_ONE, ONE)
        );
    }

    static Stream<Arguments> test_inverse() {
        return Stream.of(
                arguments(ONE, ONE),
                arguments(MINUS_ONE, MINUS_ONE)
        );
    }

    static Stream<Arguments> test_abs() {
        return Stream.of(
                arguments(ZERO, ZERO),
                arguments(ONE, ONE),
                arguments(MINUS_ONE, ONE)
        );
    }

    static Stream<Arguments> test_squared() {
        return Stream.of(
                arguments(ZERO, ZERO),
                arguments(ONE, ONE),
                arguments(MINUS_ONE, ONE)
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_negate(@ConvertWith(NumberConverter.class) BigRational r,
                     @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.negate();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_inverse(@ConvertWith(NumberConverter.class) BigRational r,
                      @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.inverse();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_inverse_zero() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(ZERO::inverse);
    }

    @ParameterizedTest
    @MethodSource
    void test_abs(@ConvertWith(NumberConverter.class) BigRational r,
                  @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.abs();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_squared(@ConvertWith(NumberConverter.class) BigRational r,
                      @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.squared();
        assertThat(actual).isEqualTo(expected);
    }
}

package io.github.ititus.math.number;

import io.github.ititus.converter.NumberConverter;
import org.junit.jupiter.api.Test;
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

class BigComplexExactUnaryOperationTests {

    private static final BigComplex ONE_I_TWO = of("1 + 2i");

    static Stream<Arguments> test_real() {
        return Stream.of(
                arguments(ZERO, 0),
                arguments(ONE, 1),
                arguments(MINUS_ONE, -1),
                arguments(I, 0),
                arguments(MINUS_I, 0),
                arguments(ONE_I_TWO, 1)
        );
    }

    static Stream<Arguments> test_imag() {
        return Stream.of(
                arguments(ZERO, 0),
                arguments(ONE, 0),
                arguments(MINUS_ONE, 0),
                arguments(I, 1),
                arguments(MINUS_I, -1),
                arguments(ONE_I_TWO, 2)
        );
    }

    static Stream<Arguments> test_abs_squared() {
        return Stream.of(
                arguments(ZERO, 0),
                arguments(ONE, 1),
                arguments(MINUS_ONE, 1),
                arguments(I, 1),
                arguments(MINUS_I, 1),
                arguments(ONE_I_TWO, 5)
        );
    }

    static Stream<Arguments> test_negate() {
        return Stream.of(
                arguments(ZERO, ZERO),
                arguments(ONE, MINUS_ONE),
                arguments(MINUS_ONE, ONE),
                arguments(I, MINUS_I),
                arguments(MINUS_I, I),
                arguments(ONE_I_TWO, "-1 - 2i")
        );
    }

    static Stream<Arguments> test_conjugate() {
        return Stream.of(
                arguments(ZERO, ZERO),
                arguments(ONE, ONE),
                arguments(MINUS_ONE, MINUS_ONE),
                arguments(I, MINUS_I),
                arguments(MINUS_I, I),
                arguments(ONE_I_TWO, "1 - 2i")
        );
    }

    static Stream<Arguments> test_inverse() {
        return Stream.of(
                arguments(ONE, ONE),
                arguments(MINUS_ONE, MINUS_ONE),
                arguments(I, MINUS_I),
                arguments(MINUS_I, I),
                arguments(ONE_I_TWO, "1/5 - 2/5 i")
        );
    }

    static Stream<Arguments> test_squared() {
        return Stream.of(
                arguments(ZERO, ZERO),
                arguments(ONE, ONE),
                arguments(MINUS_ONE, ONE),
                arguments(I, MINUS_ONE),
                arguments(MINUS_I, MINUS_ONE),
                arguments(ONE_I_TWO, "-3 + 4i")
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_imag(@ConvertWith(NumberConverter.class) BigComplex z,
                   @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = z.getImag();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_real(@ConvertWith(NumberConverter.class) BigComplex z,
                   @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = z.getReal();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_abs_squared(@ConvertWith(NumberConverter.class) BigComplex z,
                          @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = z.absSquared();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_angle_zero() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(ZERO::angle);
    }

    @ParameterizedTest
    @MethodSource
    void test_negate(@ConvertWith(NumberConverter.class) BigComplex z,
                     @ConvertWith(NumberConverter.class) BigComplex expected) {
        BigComplex actual = z.negate();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_conjugate(@ConvertWith(NumberConverter.class) BigComplex z,
                        @ConvertWith(NumberConverter.class) BigComplex expected) {
        BigComplex actual = z.conjugate();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_inverse(@ConvertWith(NumberConverter.class) BigComplex z,
                      @ConvertWith(NumberConverter.class) BigComplex expected) {
        BigComplex actual = z.inverse();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_inverse_zero() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(ZERO::inverse);
    }

    @ParameterizedTest
    @MethodSource
    void test_squared(@ConvertWith(NumberConverter.class) BigComplex z,
                      @ConvertWith(NumberConverter.class) BigComplex expected) {
        BigComplex actual = z.squared();
        assertThat(actual).isEqualTo(expected);
    }
}

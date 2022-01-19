package io.github.ititus.commons.math.number;

import io.github.ititus.commons.assertions.Assertions;
import io.github.ititus.commons.converter.NumberConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.ititus.commons.math.number.BigComplex.of;
import static io.github.ititus.commons.math.number.BigComplexConstants.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BigComplexExactUnaryOperationTests {

    private static final BigComplex ONE_I_TWO = of("1 + 2i");

    static Stream<Arguments> test_real() {
        return Stream.of(
                arguments(ZERO, ZERO),
                arguments(ONE, ONE),
                arguments(MINUS_ONE, MINUS_ONE),
                arguments(I, ZERO),
                arguments(MINUS_I, ZERO),
                arguments(ONE_I_TWO, ONE)
        );
    }

    static Stream<Arguments> test_imag() {
        return Stream.of(
                arguments(ZERO, ZERO),
                arguments(ONE, ZERO),
                arguments(MINUS_ONE, ZERO),
                arguments(I, ONE),
                arguments(MINUS_I, MINUS_ONE),
                arguments(ONE_I_TWO, TWO)
        );
    }

    static Stream<Arguments> test_abs_squared() {
        return Stream.of(
                arguments(0, 0),
                arguments("i", 1),
                arguments("-i", 1),
                arguments("2i", 4),
                arguments("-2i", 4),
                arguments("5i", 25),
                arguments("-5i", 25),
                arguments("10i", 100),
                arguments("-10i", 100),
                arguments(1, 1),
                arguments(-1, 1),
                arguments("1 + i", 2),
                arguments("1 - i", 2),
                arguments("-1 + i", 2),
                arguments("-1 - i", 2),
                arguments("1 + 2i", 5),
                arguments("1 - 2i", 5),
                arguments("-1 + 2i", 5),
                arguments("-1 - 2i", 5),
                arguments("1 + 5i", 26),
                arguments("1 - 5i", 26),
                arguments("-1 + 5i", 26),
                arguments("-1 - 5i", 26),
                arguments("1 + 10i", 101),
                arguments("1 - 10i", 101),
                arguments("-1 + 10i", 101),
                arguments("-1 - 10i", 101),
                arguments(2, 4),
                arguments(-2, 4),
                arguments("2 + i", 5),
                arguments("2 - i", 5),
                arguments("-2 + i", 5),
                arguments("-2 - i", 5),
                arguments("2 + 2i", 8),
                arguments("2 - 2i", 8),
                arguments("-2 + 2i", 8),
                arguments("-2 - 2i", 8),
                arguments("2 + 5i", 29),
                arguments("2 - 5i", 29),
                arguments("-2 + 5i", 29),
                arguments("-2 - 5i", 29),
                arguments("2 + 10i", 104),
                arguments("2 - 10i", 104),
                arguments("-2 + 10i", 104),
                arguments("-2 - 10i", 104),
                arguments(5, 25),
                arguments(-5, 25),
                arguments("5 + i", 26),
                arguments("5 - i", 26),
                arguments("-5 + i", 26),
                arguments("-5 - i", 26),
                arguments("5 + 2i", 29),
                arguments("5 - 2i", 29),
                arguments("-5 + 2i", 29),
                arguments("-5 - 2i", 29),
                arguments("5 + 5i", 50),
                arguments("5 - 5i", 50),
                arguments("-5 + 5i", 50),
                arguments("-5 - 5i", 50),
                arguments("5 + 10i", 125),
                arguments("5 - 10i", 125),
                arguments("-5 + 10i", 125),
                arguments("-5 - 10i", 125),
                arguments(10, 100),
                arguments(-10, 100),
                arguments("10 + i", 101),
                arguments("10 - i", 101),
                arguments("-10 + i", 101),
                arguments("-10 - i", 101),
                arguments("10 + 2i", 104),
                arguments("10 - 2i", 104),
                arguments("-10 + 2i", 104),
                arguments("-10 - 2i", 104),
                arguments("10 + 5i", 125),
                arguments("10 - 5i", 125),
                arguments("-10 + 5i", 125),
                arguments("-10 - 5i", 125),
                arguments("10 + 10i", 200),
                arguments("10 - 10i", 200),
                arguments("-10 + 10i", 200),
                arguments("-10 - 10i", 200)
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
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_real(@ConvertWith(NumberConverter.class) BigComplex z,
                   @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = z.getReal();
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_abs_squared(@ConvertWith(NumberConverter.class) BigComplex z,
                          @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = z.absSquared();
        Assertions.assertThat(actual).isEqualTo(expected);
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
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_conjugate(@ConvertWith(NumberConverter.class) BigComplex z,
                        @ConvertWith(NumberConverter.class) BigComplex expected) {
        BigComplex actual = z.conjugate();
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void test_inverse(@ConvertWith(NumberConverter.class) BigComplex z,
                      @ConvertWith(NumberConverter.class) BigComplex expected) {
        BigComplex actual = z.inverse();
        Assertions.assertThat(actual).isEqualTo(expected);
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
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}

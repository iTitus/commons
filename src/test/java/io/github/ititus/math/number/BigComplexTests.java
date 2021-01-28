package io.github.ititus.math.number;

import io.github.ititus.converters.BigComplexArgumentConverter;
import io.github.ititus.converters.BigRationalArgumentConverter;
import org.assertj.core.data.Offset;
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
import static org.assertj.core.data.Offset.strictOffset;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BigComplexTests {

    private static final BigComplex ONE_I_TWO = of("1 + 2i");
    private static final BigComplex TWO_I_TWO = of("2 + 2i");

    private static final Offset<BigRational> EPS = strictOffset(BigRational.of("1e-33"));

    static Stream<Arguments> test_abs() {
        return Stream.of(
                arguments(ONE, 1),
                arguments(MINUS_ONE, 1),
                arguments(I, 1),
                arguments(MINUS_I, 1),
                arguments(ONE_I_TWO, BigRationalConstants.FIVE.sqrt())
        );
    }

    static Stream<Arguments> test_angle() {
        return Stream.of(
                arguments(ONE, ZERO),
                arguments(MINUS_ONE, PI),
                arguments(I, PI.divide(2)),
                arguments(MINUS_I, PI.divide(-2)),
                arguments(ONE_I_TWO, "1.1071487177940905030170654601785370400700476454014326466765392074")
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

    @Test
    void test_real() {
        BigRational actual = ONE_I_TWO.getReal();
        BigRational expected = BigRationalConstants.ONE;
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_imag() {
        BigRational actual = ONE_I_TWO.getImag();
        BigRational expected = BigRationalConstants.TWO;
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_abs_squared() {
        BigRational actual = ONE_I_TWO.absSquared();
        BigRational expected = BigRationalConstants.FIVE;
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @MethodSource
    void test_abs(@ConvertWith(BigComplexArgumentConverter.class) BigComplex z,
                  @ConvertWith(BigRationalArgumentConverter.class) BigRational expected) {
        BigRational actual = z.abs();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @MethodSource
    void test_angle(@ConvertWith(BigComplexArgumentConverter.class) BigComplex z,
                    @ConvertWith(BigRationalArgumentConverter.class) BigRational expected) {
        BigRational actual = z.angle();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_angle_zero() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(ZERO::angle);
    }

    @Test
    void test_negate() {
        BigComplex actual = ONE_I_TWO.negate();
        BigComplex expected = of("-1 - 2i");
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_conjugate() {
        BigComplex actual = ONE_I_TWO.conjugate();
        BigComplex expected = of("1 - 2i");
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @MethodSource
    void test_inverse(@ConvertWith(BigComplexArgumentConverter.class) BigComplex z,
                      @ConvertWith(BigComplexArgumentConverter.class) BigComplex expected) {
        BigComplex actual = z.inverse();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_inverse_zero() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(ZERO::inverse);
    }

    @Test
    void test_add() {
        BigComplex actual = ONE_I_TWO.add(TWO_I_TWO);
        BigComplex expected = of("3 + 4i");
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_subtract() {
        BigComplex actual = ONE_I_TWO.subtract(TWO_I_TWO);
        BigComplex expected = MINUS_ONE;
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_multiply() {
        BigComplex actual = ONE_I_TWO.multiply(TWO_I_TWO);
        BigComplex expected = of("-2 + 6i");
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_divide() {
        BigComplex actual = ONE_I_TWO.divide(TWO_I_TWO);
        BigComplex expected = of("3/4 + 1/4 i");
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_divide_zero() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> ONE_I_TWO.divide(ZERO));
    }

    @Test
    void test_squared() {
        BigComplex actual = ONE_I_TWO.squared();
        BigComplex expected = of("-3 + 4i");
        assertThat(actual).isCloseTo(expected, EPS);
    }
}

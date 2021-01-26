package io.github.ititus.math.number;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigComplex.of;
import static io.github.ititus.math.number.BigComplexConstants.MINUS_ONE;
import static io.github.ititus.math.number.BigComplexConstants.ZERO;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.data.Offset.strictOffset;

class BigComplexTests {

    private static final BigComplex ONE_I_TWO = of("1 + 2i");
    private static final BigComplex TWO_I_TWO = of("2 + 2i");

    private static final Offset<BigRational> EPS = strictOffset(BigRational.of("1e-33"));

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

    @Test
    void test_abs() {
        BigRational actual = ONE_I_TWO.abs();
        BigRational expected = BigRationalConstants.FIVE.sqrt();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_angle() {
        BigRational actual = ONE_I_TWO.angle();
        BigRational expected = BigRational.of("1.1071487177940905030170654601785370400700476454014326466765392074");
        assertThat(actual).isCloseTo(expected, EPS);
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

    @Test
    void test_inverse() {
        BigComplex actual = ONE_I_TWO.inverse();
        BigComplex expected = of("1/5 - 2/5 i");
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
    void test_squared() {
        BigComplex actual = ONE_I_TWO.squared();
        BigComplex expected = of("-3 + 4i");
        assertThat(actual).isCloseTo(expected, EPS);
    }
}

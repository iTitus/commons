package io.github.ititus.math.number;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigComplexConstants.ONE;
import static io.github.ititus.math.number.BigComplexConstants.ZERO;
import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.data.Offset.strictOffset;

public class BigComplexTests {
    public static final BigComplex ONE_I_TWO = BigComplex.of(BigRationalConstants.ONE, TWO);
    public static final BigComplex TWO_I_TWO = BigComplex.of(TWO, TWO);
    public static final BigRational TWICE_TWO_SQRT = TWO.sqrt().multiply(TWO);
    public static final Offset<BigRational> EPSILON = strictOffset(of("0.001"));
    public static final Offset<BigRational> STRICT_EPS = strictOffset(of("0.000001"));

    @Test
    public void test_one_real() {
        assertThat(ONE.getReal()).isOne();
    }

    @Test
    public void test_one_imag() {
        assertThat(ONE.getImag()).isZero();
    }

    @Test
    public void test_abs_squared() {
        assertThat(TWO_I_TWO.absSquared()).isCloseTo(EIGHT, EPSILON);
    }

    @Test
    public void test_abs() {
        assertThat(TWO_I_TWO.abs()).isCloseTo(TWICE_TWO_SQRT, EPSILON);
    }

    @Test
    public void test_angle() {
        assertThat(TWO_I_TWO.angle()).isCloseTo(PI.divide(4), EPSILON);
    }

    @Test
    public void test_negate() {
        assertThat(TWO_I_TWO.negate()).isCloseTo(BigComplex.of(MINUS_TWO, MINUS_TWO), EPSILON);
    }

    @Test
    public void test_conjugate() {
        assertThat(TWO_I_TWO.conjugate()).isCloseTo(BigComplex.of(TWO, MINUS_TWO), EPSILON);
    }

    @Test
    public void test_inverse() {
        assertThat(TWO_I_TWO.inverse()).isCloseTo(BigComplex.of(ONE_OVER_FOUR, MINUS_ONE_OVER_FOUR), EPSILON);
    }

    @Test
    public void test_inverse_zero() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> ZERO.inverse());
    }

    @Test
    public void test_add() {
        assertThat(TWO_I_TWO.add(ONE_I_TWO)).isCloseTo(BigComplex.of(THREE, FOUR), EPSILON);
    }

    @Test
    public void test_subtract() {
        assertThat(TWO_I_TWO.subtract(ONE_I_TWO)).isCloseTo(BigComplex.of(BigRationalConstants.ONE,
                BigRationalConstants.ZERO), EPSILON);
    }

    @Test
    public void test_multiply() {
        assertThat(TWO_I_TWO.multiply(ONE_I_TWO)).isCloseTo(BigComplex.of(MINUS_TWO, SIX), EPSILON);
    }

    @Test
    public void test_sqrt() {
        assertThat(TWO_I_TWO.sqrt()).isCloseTo(BigComplex.of(BigRational.of(1.55377), BigRational.of(0.64359)),
                EPSILON);
    }

    @Test
    public void test_divide() {
        assertThat(TWO_I_TWO.divide(ONE_I_TWO)).isCloseTo(BigComplex.of(BigRational.of(6.0 / 5.0), BigRational.of(-2.0 / 5.0)),
                EPSILON);
    }

    @Test
    public void test_squared() {
        assertThat(TWO_I_TWO.squared()).isCloseTo(BigComplex.of(BigRationalConstants.ZERO, EIGHT), EPSILON);
    }

    @Test
    public void test_exp() {
        // expected = e^2*(cos(2)+i*sin(2))
        BigComplex expected = BigComplex.of(E.squared().multiply(TWO.cos()), E.squared().multiply(TWO.sin()));
        assertThat(TWO_I_TWO.exp()).isCloseTo(expected, STRICT_EPS);
    }
}

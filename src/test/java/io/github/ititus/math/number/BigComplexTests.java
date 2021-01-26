package io.github.ititus.math.number;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigComplex.imag;
import static io.github.ititus.math.number.BigComplex.of;
import static io.github.ititus.math.number.BigComplexConstants.ONE;
import static io.github.ititus.math.number.BigComplexConstants.ZERO;
import static io.github.ititus.math.number.BigRational.ofExp;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.data.Offset.strictOffset;

public class BigComplexTests {
    private static final BigRational TWICE_TWO_SQRT = TWO.multiply(TWO.sqrt());
    private static final BigComplex ONE_I_TWO = of(BigRationalConstants.ONE, TWO);
    private static final BigComplex TWO_I_TWO = of(TWO, TWO);

    private static final Offset<BigRational> EPSILON = strictOffset(ofExp(1,-32));

    @Test
    public void test_one_real() {
        assertThat(ONE).real().isOne();
    }

    @Test
    public void test_one_imag() {
        assertThat(ONE).imag().isZero();
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
        assertThat(TWO_I_TWO.negate()).isCloseTo(of(MINUS_TWO, MINUS_TWO), EPSILON);
    }

    @Test
    public void test_conjugate() {
        assertThat(TWO_I_TWO.conjugate()).isCloseTo(of(TWO, MINUS_TWO), EPSILON);
    }

    @Test
    public void test_inverse() {
        assertThat(TWO_I_TWO.inverse()).isCloseTo(of(ONE_OVER_FOUR, MINUS_ONE_OVER_FOUR), EPSILON);
    }

    @Test
    public void test_inverse_zero() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> ZERO.inverse());
    }

    @Test
    public void test_add() {
        assertThat(TWO_I_TWO.add(ONE_I_TWO)).isCloseTo(of(THREE, FOUR), EPSILON);
    }

    @Test
    public void test_subtract() {
        assertThat(TWO_I_TWO.subtract(ONE_I_TWO)).isCloseTo(of(BigRationalConstants.ONE,
                BigRationalConstants.ZERO), EPSILON);
    }

    @Test
    public void test_multiply() {
        assertThat(TWO_I_TWO.multiply(ONE_I_TWO)).isCloseTo(of(MINUS_TWO, SIX), EPSILON);
    }

    @Test
    public void test_divide() {
        assertThat(TWO_I_TWO.divide(ONE_I_TWO)).isCloseTo(of(BigRational.of(6.0 / 5.0), BigRational.of(-2.0 / 5.0)),
                EPSILON);
    }

    @Test
    public void test_squared() {
        assertThat(TWO_I_TWO.squared()).isCloseTo(imag(BigRationalConstants.EIGHT), EPSILON);
    }
}

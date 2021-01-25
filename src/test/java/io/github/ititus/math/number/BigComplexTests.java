package io.github.ititus.math.number;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigComplexConstants.ONE;
import static io.github.ititus.math.number.BigComplexConstants.ZERO;
import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRationalConstants.PI;
import static org.assertj.core.data.Offset.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BigComplexTests {
    public static final BigComplex TWO_I_TWO = BigComplex.of(BigRational.of(2), BigRational.of(2));
    public static final BigRational TWICE_TWO_SQRT = BigRational.of(2).sqrt().multiply(BigRational.of(2));
    public static final Offset<BigRational> EPSILON = offset(of("0.001"));

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
        assertThat(TWO_I_TWO.absSquared()).isCloseTo(BigRational.of(8), EPSILON);
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
        assertThat(TWO_I_TWO.negate()).isCloseTo(BigComplex.of(BigRational.of(-2), BigRational.of(-2)), EPSILON);
    }

    @Test
    public void test_conjugate() {
        assertThat(TWO_I_TWO.conjugate()).isCloseTo(BigComplex.of(BigRational.of(2), BigRational.of(-2)), EPSILON);
    }

    @Test
    public void test_inverse() {
        assertThat(TWO_I_TWO.inverse()).isCloseTo(BigComplex.of(BigRational.of(1/4), BigRational.of(-1/4)), EPSILON);
    }

    @Test
    public void test_inverse_zero() {
        assertThrows(ArithmeticException.class, () -> ZERO.inverse());
    }
}

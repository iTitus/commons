package io.github.ititus.math.number;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRationalConstants.PI;
import static org.assertj.core.data.Offset.offset;

public class BigComplexTests {
    public static final BigComplex TWO_I_TWO = BigComplex.of(BigRational.of(2), BigRational.of(2));
    public static final BigRational TWICE_TWO_SQRT = BigRational.of(2).sqrt().multiply(BigRational.of(2));
    public static final Offset<BigRational> EPSILON = offset(of("0.001"));

    @Test
    public void test_one_real() {
        assertThat(BigComplexConstants.ONE.getReal()).isOne();
    }

    @Test
    public void test_one_imag() {
        assertThat(BigComplexConstants.ONE.getImag()).isZero();
    }

    @Test
    public void test_abs_squared() {
        assertThat(TWO_I_TWO.absSquared()).isCloseTo(BigRational.of(8), EPSILON);
    }

    @Test
    public void test_abs() {
        assertThat(TWO_I_TWO.abs()).isCloseTo(TWICE_TWO_SQRT, EPSILON);
    }
}

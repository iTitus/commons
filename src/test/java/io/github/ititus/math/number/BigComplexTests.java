package io.github.ititus.math.number;

import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;

public class BigComplexTests {
    @Test
    public void test_one_real() {
        assertThat(BigComplex.ONE.getReal()).isOne();
    }

    @Test
    public void test_one_imag() {
        assertThat(BigComplex.ONE.getImag()).isZero();
    }
}

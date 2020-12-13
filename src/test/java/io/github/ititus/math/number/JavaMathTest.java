package io.github.ititus.math.number;

import org.junit.jupiter.api.Test;

import static io.github.ititus.math.number.JavaMath.gcd;
import static org.assertj.core.api.Assertions.assertThat;

class JavaMathTest {

    @Test
    void test_gcd_zero() {
        assertThat(gcd(0, 0)).isEqualTo(0);

        assertThat(gcd(0, 1)).isEqualTo(1);
        assertThat(gcd(1, 0)).isEqualTo(1);

        assertThat(gcd(0, 2)).isEqualTo(2);
        assertThat(gcd(2, 0)).isEqualTo(2);

        assertThat(gcd(0, 42)).isEqualTo(42);
        assertThat(gcd(42, 0)).isEqualTo(42);
    }

    @Test
    void test_gcd_all_positive() {
        assertThat(gcd(42, 36)).isEqualTo(6);
        assertThat(gcd(36, 42)).isEqualTo(6);
    }

    @Test
    void test_gcd_all_negative() {
        assertThat(gcd(-42, -36)).isEqualTo(6);
        assertThat(gcd(-36, -42)).isEqualTo(6);
    }

    @Test
    void test_gcd_mixed_sign() {
        assertThat(gcd(-42, 36)).isEqualTo(6);
        assertThat(gcd(42, -36)).isEqualTo(6);
        assertThat(gcd(-36, 42)).isEqualTo(6);
        assertThat(gcd(36, -42)).isEqualTo(6);
    }
}
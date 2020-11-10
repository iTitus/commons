package io.github.ititus.math.number;


import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static io.github.ititus.math.number.BigRationalMath.sin;
import static org.assertj.core.data.Offset.offset;

public class BigRationalSinTests {

    @Test
    public void sin_zero() {
        assertThat(sin(ZERO)).isCloseTo(ZERO, offset(of("0.001")));
    }

    @Test
    public void sin_pi_over_four() {
        assertThat(sin(PI.divide(FOUR))).isCloseTo(TWO.sqrt().inverse(), offset(of("0.001")));
    }

    @Test
    public void sin_half_pi() {
        assertThat(sin(PI_OVER_TWO)).isCloseTo(ONE, offset(of("0.001")));
    }

    @Test
    public void sin_three_pi_over_four() {
        assertThat(sin(PI.multiply(THREE_OVER_FOUR))).isCloseTo(TWO.sqrt().inverse(), offset(of("0.001")));
    }

    @Test
    public void sin_pi() {
        assertThat(sin(PI)).isCloseTo(ZERO, offset(of("0.001")));
    }

    @Test
    public void sin_five_pi_over_four() {
        assertThat(sin(PI.multiply(FIVE_OVER_FOUR))).isCloseTo(TWO.sqrt().inverse().negate(), offset(of("0.001")));
    }

    @Test
    public void sin_three_half_pi() {
        assertThat(sin(PI.add(PI_OVER_TWO))).isCloseTo(MINUS_ONE, offset(of("0.001")));
    }

    @Test
    public void sin_seven_pi_over_four() {
        assertThat(sin(PI.add(PI.multiply(THREE_OVER_FOUR)))).isCloseTo(TWO.sqrt().inverse().negate(), offset(of("0" +
                ".001")));
    }

    @Test
    public void sin_two_pi() {
        assertThat(sin(TWO_PI)).isCloseTo(ZERO, offset(of("0.001")));
    }
}

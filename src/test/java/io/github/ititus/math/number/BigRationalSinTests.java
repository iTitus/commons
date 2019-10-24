package io.github.ititus.math.number;

import org.junit.Test;

import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static io.github.ititus.math.number.BigRationalMath.sin;
import static io.github.ititus.TestUtil.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BigRationalSinTests {

    @Test
    public void sin_zero() {
        assertThat(sin(ZERO), is(closeTo(ZERO, of("0.001"))));
    }

    @Test
    public void sin_pi_over_four() {
        assertThat(sin(PI.divide(FOUR)), is(closeTo(TWO.sqrt().inverse(), of("0.001"))));
    }

    @Test
    public void sin_half_pi() {
        assertThat(sin(PI_OVER_TWO), is(closeTo(ONE, of("0.001"))));
    }

    @Test
    public void sin_three_pi_over_four() {
        assertThat(sin(PI.multiply(THREE_OVER_FOUR)), is(closeTo(TWO.sqrt().inverse(), of("0.001"))));
    }

    @Test
    public void sin_pi() {
        assertThat(sin(PI), is(closeTo(ZERO, of("0.001"))));
    }

    @Test
    public void sin_five_pi_over_four() {
        assertThat(sin(PI.multiply(FIVE_OVER_FOUR)), is(closeTo(TWO.sqrt().inverse().negate(), of("0.001"))));
    }

    @Test
    public void sin_three_half_pi() {
        assertThat(sin(PI.add(PI_OVER_TWO)), is(closeTo(MINUS_ONE, of("0.001"))));
    }

    @Test
    public void sin_seven_pi_over_four() {
        assertThat(sin(PI.add(PI.multiply(THREE_OVER_FOUR))), is(closeTo(TWO.sqrt().inverse().negate(), of("0.001"))));
    }

    @Test
    public void sin_two_pi() {
        assertThat(sin(TWO_PI), is(closeTo(ZERO, of("0.001"))));
    }
}

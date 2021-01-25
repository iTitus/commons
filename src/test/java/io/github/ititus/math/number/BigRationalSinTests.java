package io.github.ititus.math.number;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigRational.ofExp;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static io.github.ititus.math.number.BigRationalMath.sin;
import static org.assertj.core.data.Offset.strictOffset;

public class BigRationalSinTests {

    private static final BigRational THREE_OVER_FOUR = new BigRational(BigIntegerConstants.THREE,
            BigIntegerConstants.FOUR);
    private static final BigRational FIVE_OVER_FOUR = new BigRational(BigIntegerConstants.FIVE,
            BigIntegerConstants.FOUR);

    private static final Offset<BigRational> EPS = strictOffset(ofExp(1, -3));

    @Test
    public void sin_zero() {
        assertThat(sin(ZERO)).isCloseTo(ZERO, EPS);
    }

    @Test
    public void sin_pi_over_four() {
        assertThat(sin(PI.divide(FOUR))).isCloseTo(TWO.sqrt().inverse(), EPS);
    }

    @Test
    public void sin_half_pi() {
        assertThat(sin(PI_OVER_TWO)).isCloseTo(ONE, EPS);
    }

    @Test
    public void sin_three_pi_over_four() {
        assertThat(sin(PI.multiply(THREE_OVER_FOUR))).isCloseTo(TWO.sqrt().inverse(), EPS);
    }

    @Test
    public void sin_pi() {
        assertThat(sin(PI)).isCloseTo(ZERO, EPS);
    }

    @Test
    public void sin_five_pi_over_four() {
        assertThat(sin(PI.multiply(FIVE_OVER_FOUR))).isCloseTo(TWO.sqrt().inverse().negate(), EPS);
    }

    @Test
    public void sin_three_half_pi() {
        assertThat(sin(PI.add(PI_OVER_TWO))).isCloseTo(MINUS_ONE, EPS);
    }

    @Test
    public void sin_seven_pi_over_four() {
        assertThat(sin(PI.add(PI.multiply(THREE_OVER_FOUR)))).isCloseTo(TWO.sqrt().inverse().negate(), EPS);
    }

    @Test
    public void sin_two_pi() {
        assertThat(sin(TWO_PI)).isCloseTo(ZERO, EPS);
    }
}

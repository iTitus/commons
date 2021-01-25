package io.github.ititus.math.number;


import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigRational.ofExp;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static io.github.ititus.math.number.BigRationalMath.atan;
import static org.assertj.core.data.Offset.strictOffset;

public class BigRationalAtanTests {

    private static final Offset<BigRational> EPS = strictOffset(ofExp(1, -3));

    @Test
    public void atan_zero() {
        assertThat(atan(ZERO)).isCloseTo(ZERO, EPS);
    }

    @Test
    public void atan_one() {
        assertThat(atan(ONE)).isCloseTo(PI.divide(FOUR), EPS);
    }
}

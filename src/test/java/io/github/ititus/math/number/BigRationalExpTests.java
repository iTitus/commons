package io.github.ititus.math.number;


import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRational.ofExp;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static io.github.ititus.math.number.BigRationalMath.exp;
import static org.assertj.core.data.Offset.strictOffset;

public class BigRationalExpTests {

    private static final Offset<BigRational> EPS = strictOffset(ofExp(1, -3));

    @Test
    public void exp_0() {
        assertThat(exp(ZERO)).isEqualTo(ONE);
    }

    @Test
    public void exp_1() {
        assertThat(exp(ONE)).isCloseTo(of("2.718"), EPS);
    }

    @Test
    public void exp_neg1() {
        assertThat(exp(MINUS_ONE)).isCloseTo(of("0.368"), EPS);
    }

    @Test
    public void exp_e() {
        assertThat(exp(exp(ONE))).isCloseTo(of("15.154"), EPS);
    }

    @Test
    public void exp_e_e() {
        assertThat(exp(exp(exp(ONE)))).isCloseTo(of("3814279.105"), EPS);
    }
}

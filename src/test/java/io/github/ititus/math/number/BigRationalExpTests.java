package io.github.ititus.math.number;


import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static io.github.ititus.math.number.BigRationalMath.exp;
import static org.assertj.core.data.Offset.offset;

public class BigRationalExpTests {

    @Test
    public void exp_0() {
        assertThat(exp(ZERO)).isEqualTo(ONE);
    }

    @Test
    public void exp_1() {
        assertThat(exp(ONE)).isCloseTo(of("2.718"), offset(of("0.001")));
    }

    @Test
    public void exp_neg1() {
        assertThat(exp(MINUS_ONE)).isCloseTo(of("0.368"), offset(of("0.001")));
    }

    @Test
    public void exp_e() {
        assertThat(exp(exp(ONE))).isCloseTo(of("15.154"), offset(of("0.001")));
    }

    @Test
    public void exp_e_e() {
        assertThat(exp(exp(exp(ONE)))).isCloseTo(of("3814279.105"), offset(of("0.001")));
    }
}

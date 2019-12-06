package io.github.ititus.math.number;

import org.junit.Test;

import static io.github.ititus.TestUtil.closeTo;
import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static io.github.ititus.math.number.BigRationalMath.exp;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BigRationalExpTests {

    @Test
    public void exp_0() {
        assertThat(exp(ZERO), is(equalTo(ONE)));
    }

    @Test
    public void exp_1() {
        assertThat(exp(ONE), is(closeTo(of("2.718"), of("0.001"))));
    }

    @Test
    public void exp_neg1() {
        assertThat(exp(MINUS_ONE), is(closeTo(of("0.368"), of("0.001"))));
    }

    @Test
    public void exp_e() {
        assertThat(exp(exp(ONE)), is(closeTo(of("15.154"), of("0.001"))));
    }

    @Test
    public void exp_e_e() {
        assertThat(exp(exp(exp(ONE))), is(closeTo(of("3814279.105"), of("0.001"))));
    }
}

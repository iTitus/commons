package io.github.ititus.math.number;

import org.junit.Test;

import static io.github.ititus.math.number.BigIntegerMath.*;
import static java.math.BigInteger.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BigIntegerPowTests {

    @Test
    public void pow0_0() {
        assertThat(pow(ZERO, ZERO), is(equalTo(ONE)));
    }

    @Test
    public void pow1_0() {
        assertThat(pow(ONE, ZERO), is(equalTo(ONE)));
    }

    @Test
    public void pow0_1() {
        assertThat(pow(ZERO, ONE), is(equalTo(ZERO)));
    }

    @Test
    public void pow0_2() {
        assertThat(pow(ZERO, TWO), is(equalTo(ZERO)));
    }

    @Test
    public void pow0_42() {
        assertThat(pow(ZERO, of(42)), is(equalTo(ZERO)));
    }

    @Test
    public void pow1_1() {
        assertThat(pow(ONE, ONE), is(equalTo(ONE)));
    }

    @Test
    public void pow1_2() {
        assertThat(pow(ONE, TWO), is(equalTo(ONE)));
    }

    @Test
    public void pow1_42() {
        assertThat(pow(ONE, of(42)), is(equalTo(ONE)));
    }

    @Test
    public void pow2_1() {
        assertThat(pow(TWO, ONE), is(equalTo(TWO)));
    }

    @Test
    public void pow2_2() {
        assertThat(pow(TWO, TWO), is(equalTo(of(4))));
    }

    @Test
    public void pow2_3() {
        assertThat(pow(TWO, THREE), is(equalTo(of(8))));
    }

    @Test
    public void pow2_4() {
        assertThat(pow(TWO, of(4)), is(equalTo(of(16))));
    }

    @Test
    public void pow2_42() {
        assertThat(pow(TWO, of(42)), is(equalTo(of(4398046511104L))));
    }

    @Test
    public void pow_42_21() {
        assertThat(pow(of(42), of(21)), is(equalTo(of("12252793050782200016679467841748992"))));
    }

    @Test
    public void pow_42_42() {
        assertThat(pow(of(42), of(42)), is(equalTo(of("150130937545296572356771972164254457814047970568738777235893533016064"))));
    }

    @Test
    public void pow_neg1_0() {
        assertThat(pow(MINUS_ONE, ZERO), is(equalTo(ONE)));
    }

    @Test
    public void pow_neg1_1() {
        assertThat(pow(MINUS_ONE, ONE), is(equalTo(MINUS_ONE)));
    }

    @Test
    public void pow_neg1_2() {
        assertThat(pow(MINUS_ONE, TWO), is(equalTo(ONE)));
    }

    @Test
    public void pow_neg1_3() {
        assertThat(pow(MINUS_ONE, THREE), is(equalTo(MINUS_ONE)));
    }

    @Test
    public void pow_neg1_21() {
        assertThat(pow(MINUS_ONE, of(21)), is(equalTo(MINUS_ONE)));
    }

    @Test
    public void pow_neg1_42() {
        assertThat(pow(MINUS_ONE, of(42)), is(equalTo(ONE)));
    }

    @Test
    public void pow_neg2_0() {
        assertThat(pow(TWO.negate(), ZERO), is(equalTo(ONE)));
    }

    @Test
    public void pow_neg2_1() {
        assertThat(pow(TWO.negate(), ONE), is(equalTo(TWO.negate())));
    }

    @Test
    public void pow_neg2_2() {
        assertThat(pow(TWO.negate(), TWO), is(equalTo(of(4))));
    }

    @Test
    public void pow_neg2_3() {
        assertThat(pow(TWO.negate(), THREE), is(equalTo(of(-8))));
    }

    @Test
    public void pow_neg2_21() {
        assertThat(pow(TWO.negate(), of(21)), is(equalTo(of(-2097152))));
    }

    @Test
    public void pow_neg2_42() {
        assertThat(pow(TWO.negate(), of(42)), is(equalTo(of(4398046511104L))));
    }

    @Test
    public void pow_neg42_21() {
        assertThat(pow(of(-42), of(21)), is(equalTo(of("-12252793050782200016679467841748992"))));
    }

    @Test
    public void pow_neg42_42() {
        assertThat(pow(of(-42), of(42)), is(equalTo(of("150130937545296572356771972164254457814047970568738777235893533016064"))));
    }
}

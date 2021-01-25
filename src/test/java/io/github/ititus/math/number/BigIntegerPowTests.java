package io.github.ititus.math.number;


import org.junit.jupiter.api.Test;

import static io.github.ititus.math.number.BigIntegerConstants.*;
import static io.github.ititus.math.number.BigIntegerMath.of;
import static io.github.ititus.math.number.BigIntegerMath.pow;
import static org.assertj.core.api.Assertions.assertThat;

public class BigIntegerPowTests {

    @Test
    public void pow0_0() {
        assertThat(pow(ZERO, ZERO)).isEqualTo(ONE);
    }

    @Test
    public void pow1_0() {
        assertThat(pow(ONE, ZERO)).isEqualTo(ONE);
    }

    @Test
    public void pow0_1() {
        assertThat(pow(ZERO, ONE)).isEqualTo(ZERO);
    }

    @Test
    public void pow0_2() {
        assertThat(pow(ZERO, TWO)).isEqualTo(ZERO);
    }

    @Test
    public void pow0_42() {
        assertThat(pow(ZERO, of(42))).isEqualTo(ZERO);
    }

    @Test
    public void pow1_1() {
        assertThat(pow(ONE, ONE)).isEqualTo(ONE);
    }

    @Test
    public void pow1_2() {
        assertThat(pow(ONE, TWO)).isEqualTo(ONE);
    }

    @Test
    public void pow1_42() {
        assertThat(pow(ONE, of(42))).isEqualTo(ONE);
    }

    @Test
    public void pow2_1() {
        assertThat(pow(TWO, ONE)).isEqualTo(TWO);
    }

    @Test
    public void pow2_2() {
        assertThat(pow(TWO, TWO)).isEqualTo(FOUR);
    }

    @Test
    public void pow2_3() {
        assertThat(pow(TWO, THREE)).isEqualTo(EIGHT);
    }

    @Test
    public void pow2_4() {
        assertThat(pow(TWO, FOUR)).isEqualTo(of(16));
    }

    @Test
    public void pow2_42() {
        assertThat(pow(TWO, of(42))).isEqualTo(of(4398046511104L));
    }

    @Test
    public void pow_42_21() {
        assertThat(pow(of(42), of(21))).isEqualTo(of("12252793050782200016679467841748992"));
    }

    @Test
    public void pow_42_42() {
        assertThat(pow(of(42), of(42))).isEqualTo(of(
                "150130937545296572356771972164254457814047970568738777235893533016064"));
    }

    @Test
    public void pow_neg1_0() {
        assertThat(pow(MINUS_ONE, ZERO)).isEqualTo(ONE);
    }

    @Test
    public void pow_neg1_1() {
        assertThat(pow(MINUS_ONE, ONE)).isEqualTo(MINUS_ONE);
    }

    @Test
    public void pow_neg1_2() {
        assertThat(pow(MINUS_ONE, TWO)).isEqualTo(ONE);
    }

    @Test
    public void pow_neg1_3() {
        assertThat(pow(MINUS_ONE, THREE)).isEqualTo(MINUS_ONE);
    }

    @Test
    public void pow_neg1_21() {
        assertThat(pow(MINUS_ONE, of(21))).isEqualTo(MINUS_ONE);
    }

    @Test
    public void pow_neg1_42() {
        assertThat(pow(MINUS_ONE, of(42))).isEqualTo(ONE);
    }

    @Test
    public void pow_neg2_0() {
        assertThat(pow(TWO.negate(), ZERO)).isEqualTo(ONE);
    }

    @Test
    public void pow_neg2_1() {
        assertThat(pow(TWO.negate(), ONE)).isEqualTo(TWO.negate());
    }

    @Test
    public void pow_neg2_2() {
        assertThat(pow(TWO.negate(), TWO)).isEqualTo(FOUR);
    }

    @Test
    public void pow_neg2_3() {
        assertThat(pow(TWO.negate(), THREE)).isEqualTo(of(-8));
    }

    @Test
    public void pow_neg2_21() {
        assertThat(pow(TWO.negate(), of(21))).isEqualTo(of(-2097152));
    }

    @Test
    public void pow_neg2_42() {
        assertThat(pow(TWO.negate(), of(42))).isEqualTo(of(4398046511104L));
    }

    @Test
    public void pow_neg42_21() {
        assertThat(pow(of(-42), of(21))).isEqualTo(of("-12252793050782200016679467841748992"));
    }

    @Test
    public void pow_neg42_42() {
        assertThat(pow(of(-42), of(42))).isEqualTo(of(
                "150130937545296572356771972164254457814047970568738777235893533016064"));
    }
}

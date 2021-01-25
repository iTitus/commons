package io.github.ititus.math.number;


import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRational.ofExp;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static io.github.ititus.math.number.BigRationalMath.ln;
import static org.assertj.core.data.Offset.strictOffset;

public class BigRationalLnTests {

    private static final Offset<BigRational> EPS = strictOffset(ofExp(1, -3));

    @Test
    public void ln_1() {
        assertThat(ln(ONE)).isEqualTo(ZERO);
    }

    @Test
    public void ln_2() {
        assertThat(ln(TWO)).isCloseTo(of("0.693"), EPS);
    }

    @Test
    public void ln_100() {
        assertThat(ln(of(100))).isCloseTo(of("4.605"), EPS);
    }

    @Test
    public void ln_1000() {
        assertThat(ln(of(1000))).isCloseTo(of("6.908"), EPS);
    }
}

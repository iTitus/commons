package io.github.ititus.math.number;


import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.MyAssertions.assertThat;
import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static io.github.ititus.math.number.BigRationalMath.ln;
import static org.assertj.core.data.Offset.offset;

public class BigRationalLnTests {

    @Test
    public void ln_1() {
        assertThat(ln(ONE)).isEqualTo(ZERO);
    }

    @Test
    public void ln_2() {
        assertThat(ln(TWO)).isCloseTo(of("0.693"), offset(of("0.001")));
    }

    @Test
    public void ln_100() {
        assertThat(ln(of(100))).isCloseTo(of("4.605"), offset(of("0.001")));
    }

    @Test
    public void ln_1000() {
        assertThat(ln(of(1000))).isCloseTo(of("6.908"), offset(of("0.001")));
    }
}

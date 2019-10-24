package io.github.ititus.math.number;

import org.junit.Test;

import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static io.github.ititus.math.number.BigRationalMath.ln;
import static io.github.ititus.TestUtil.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BigRationalLnTests {

    @Test
    public void ln_1() {
        assertThat(ln(ONE), is(equalTo(ZERO)));
    }

    @Test
    public void ln_2() {
        assertThat(ln(TWO), is(closeTo(of("0.693"), of("0.001"))));
    }

    @Test
    public void ln_100() {
        assertThat(ln(of(100)), is(closeTo(of("4.605"), of("0.001"))));
    }

    @Test
    public void ln_1000() {
        assertThat(ln(of(1000)), is(closeTo(of("6.908"), of("0.001"))));
    }
}

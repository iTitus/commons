package io.github.ititus.assertions;

import io.github.ititus.math.number.BigRational;

public class Assertions {

    public static BigRationalAssert assertThat(BigRational actual) {
        return new BigRationalAssert(actual);
    }
}

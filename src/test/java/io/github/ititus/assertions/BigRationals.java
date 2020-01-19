package io.github.ititus.assertions;

import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;
import org.assertj.core.internal.Numbers;

public class BigRationals extends Numbers<BigRational> {

    private static final BigRationals INSTANCE = new BigRationals();

    private BigRationals() {
    }

    public static BigRationals instance() {
        return INSTANCE;
    }

    @Override
    protected BigRational zero() {
        return BigRationalConstants.ZERO;
    }

    @Override
    protected BigRational one() {
        return BigRationalConstants.ONE;
    }

    @Override
    protected BigRational absDiff(BigRational actual, BigRational other) {
        return actual.subtract(other).abs();
    }

    @Override
    protected boolean isGreaterThan(BigRational value, BigRational other) {
        return value.compareTo(other) > 0;
    }
}

package io.github.ititus.commons.assertions;

import io.github.ititus.commons.math.number.BigRational;
import org.assertj.core.api.AbstractComparableAssert;
import org.assertj.core.api.NumberAssert;
import org.assertj.core.data.Offset;
import org.assertj.core.data.Percentage;

public class BigRationalAssert extends AbstractComparableAssert<BigRationalAssert, BigRational> implements NumberAssert<BigRationalAssert, BigRational> {

    private final BigRationals bigRationals = BigRationals.instance();

    public BigRationalAssert(BigRational actual) {
        super(actual, BigRationalAssert.class);
    }

    @Override
    public BigRationalAssert isZero() {
        bigRationals.assertIsZero(info, actual);
        return myself;
    }

    @Override
    public BigRationalAssert isNotZero() {
        bigRationals.assertIsNotZero(info, actual);
        return myself;
    }

    @Override
    public BigRationalAssert isOne() {
        bigRationals.assertIsOne(info, actual);
        return myself;
    }

    @Override
    public BigRationalAssert isPositive() {
        bigRationals.assertIsPositive(info, actual);
        return myself;
    }

    @Override
    public BigRationalAssert isNegative() {
        bigRationals.assertIsNegative(info, actual);
        return myself;
    }

    @Override
    public BigRationalAssert isNotNegative() {
        bigRationals.assertIsNotNegative(info, actual);
        return myself;
    }

    @Override
    public BigRationalAssert isNotPositive() {
        bigRationals.assertIsNotPositive(info, actual);
        return myself;
    }

    @Override
    public BigRationalAssert isBetween(BigRational start, BigRational end) {
        bigRationals.assertIsBetween(info, actual, start, end);
        return myself;
    }

    @Override
    public BigRationalAssert isStrictlyBetween(BigRational start, BigRational end) {
        bigRationals.assertIsStrictlyBetween(info, actual, start, end);
        return myself;
    }

    @Override
    public BigRationalAssert isCloseTo(BigRational expected, Offset<BigRational> offset) {
        bigRationals.assertIsCloseTo(info, actual, expected, offset);
        return myself;
    }

    @Override
    public BigRationalAssert isNotCloseTo(BigRational expected, Offset<BigRational> offset) {
        bigRationals.assertIsNotCloseTo(info, actual, expected, offset);
        return myself;
    }

    @Override
    public BigRationalAssert isCloseTo(BigRational expected, Percentage percentage) {
        bigRationals.assertIsCloseToPercentage(info, actual, expected, percentage);
        return myself;
    }

    @Override
    public BigRationalAssert isNotCloseTo(BigRational expected, Percentage percentage) {
        bigRationals.assertIsNotCloseToPercentage(info, actual, expected, percentage);
        return myself;
    }
}

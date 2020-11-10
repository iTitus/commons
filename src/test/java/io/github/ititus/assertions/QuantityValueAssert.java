package io.github.ititus.assertions;

import io.github.ititus.si.quantity.value.QuantityValue;
import org.assertj.core.api.AbstractComparableAssert;
import org.assertj.core.api.NumberAssert;
import org.assertj.core.data.Offset;
import org.assertj.core.data.Percentage;

public class QuantityValueAssert extends AbstractComparableAssert<QuantityValueAssert, QuantityValue> implements NumberAssert<QuantityValueAssert, QuantityValue> {

    private final QuantityValues bigRationals = QuantityValues.instance();

    public QuantityValueAssert(QuantityValue actual) {
        super(actual, QuantityValueAssert.class);
    }

    @Override
    public QuantityValueAssert isZero() {
        bigRationals.assertIsZero(info, actual);
        return this;
    }

    @Override
    public QuantityValueAssert isNotZero() {
        bigRationals.assertIsNotZero(info, actual);
        return this;
    }

    @Override
    public QuantityValueAssert isOne() {
        bigRationals.assertIsOne(info, actual);
        return this;
    }

    @Override
    public QuantityValueAssert isPositive() {
        bigRationals.assertIsPositive(info, actual);
        return this;
    }

    @Override
    public QuantityValueAssert isNegative() {
        bigRationals.assertIsNegative(info, actual);
        return this;
    }

    @Override
    public QuantityValueAssert isNotNegative() {
        bigRationals.assertIsNotNegative(info, actual);
        return this;
    }

    @Override
    public QuantityValueAssert isNotPositive() {
        bigRationals.assertIsNotPositive(info, actual);
        return this;
    }

    @Override
    public QuantityValueAssert isBetween(QuantityValue start, QuantityValue end) {
        bigRationals.assertIsBetween(info, actual, start, end);
        return this;
    }

    @Override
    public QuantityValueAssert isStrictlyBetween(QuantityValue start, QuantityValue end) {
        bigRationals.assertIsStrictlyBetween(info, actual, start, end);
        return this;
    }

    @Override
    public QuantityValueAssert isCloseTo(QuantityValue expected, Offset<QuantityValue> offset) {
        bigRationals.assertIsCloseTo(info, actual, expected, offset);
        return this;
    }

    @Override
    public QuantityValueAssert isNotCloseTo(QuantityValue expected, Offset<QuantityValue> offset) {
        bigRationals.assertIsNotCloseTo(info, actual, expected, offset);
        return this;
    }

    @Override
    public QuantityValueAssert isCloseTo(QuantityValue expected, Percentage percentage) {
        bigRationals.assertIsCloseToPercentage(info, actual, expected, percentage);
        return this;
    }

    @Override
    public QuantityValueAssert isNotCloseTo(QuantityValue expected, Percentage percentage) {
        bigRationals.assertIsNotCloseToPercentage(info, actual, expected, percentage);
        return this;
    }
}

package io.github.ititus.assertions;

import io.github.ititus.si.quantity.value.QuantityValue;
import org.assertj.core.internal.Numbers;

public class QuantityValues extends Numbers<QuantityValue> {

    private static final QuantityValues INSTANCE = new QuantityValues();

    private QuantityValues() {
    }

    public static QuantityValues instance() {
        return INSTANCE;
    }

    @Override
    protected QuantityValue zero() {
        return QuantityValue.ZERO;
    }

    @Override
    protected QuantityValue one() {
        return QuantityValue.ONE;
    }

    @Override
    protected QuantityValue absDiff(QuantityValue actual, QuantityValue other) {
        return actual.subtract(other).abs();
    }

    @Override
    protected boolean isGreaterThan(QuantityValue value, QuantityValue other) {
        return value.compareTo(other) > 0;
    }
}

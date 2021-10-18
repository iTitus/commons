package io.github.ititus.si.unit.converter;

import io.github.ititus.si.quantity.value.QuantityValue;

import java.util.List;
import java.util.Objects;

final class LinearConverter implements UnitConverter {

    private final QuantityValue factor;
    private final QuantityValue shift;

    private LinearConverter(QuantityValue factor, QuantityValue shift) {
        this.factor = factor;
        this.shift = shift;
    }

    static UnitConverter ofFactor(QuantityValue factor) {
        if (factor.isZero()) {
            throw new IllegalArgumentException("0 not allowed");
        } else if (factor.isOne()) {
            return IDENTITY;
        }

        return new LinearConverter(factor, QuantityValue.ZERO);
    }

    static UnitConverter ofShift(QuantityValue shift) {
        if (shift.isZero()) {
            return IDENTITY;
        }

        return new LinearConverter(QuantityValue.ONE, shift);
    }

    static UnitConverter of(QuantityValue factor, QuantityValue shift) {
        if (factor.isZero()) {
            throw new IllegalArgumentException("0 not allowed");
        } else if (factor.isOne() && shift.isZero()) {
            return IDENTITY;
        }

        return new LinearConverter(factor, shift);
    }

    @Override
    public QuantityValue convert(QuantityValue value) {
        return value.multiply(factor).add(shift);
    }

    @Override
    public UnitConverter inverse() {
        return of(factor.inverse(), shift.divide(factor).negate());
    }

    @Override
    public UnitConverter concat(UnitConverter converter) {
        if (converter.isIdentity()) {
            return this;
        } else if (converter instanceof LinearConverter) {
            LinearConverter that = (LinearConverter) converter;
            return of(factor.multiply(that.factor), that.factor.multiply(shift).add(this.shift));
        }

        return UnitConverter.compound(List.of(this, converter));
    }

    @Override
    public boolean isIdentity() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof LinearConverter)) {
            return false;
        }
        LinearConverter that = (LinearConverter) o;
        return factor.equals(that.factor) && shift.equals(that.shift);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factor, shift);
    }
}

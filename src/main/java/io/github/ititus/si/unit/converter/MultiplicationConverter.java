package io.github.ititus.si.unit.converter;

import io.github.ititus.si.quantity.value.QuantityValue;

import java.util.List;
import java.util.Objects;

final class MultiplicationConverter implements UnitConverter {

    private final QuantityValue factor;

    private MultiplicationConverter(QuantityValue factor) {
        this.factor = factor;
    }

    static UnitConverter of(QuantityValue factor) {
        if (factor.isZero()) {
            throw new IllegalArgumentException("0 not allowed");
        } else if (factor.isOne()) {
            return IDENTITY;
        }

        return new MultiplicationConverter(factor);
    }

    @Override
    public QuantityValue convert(QuantityValue value) {
        return value.multiply(factor);
    }

    @Override
    public UnitConverter inverse() {
        return of(factor.inverse());
    }

    @Override
    public UnitConverter concat(UnitConverter converter) {
        if (converter.isIdentity()) {
            return this;
        } else if (converter instanceof MultiplicationConverter) {
            return of(factor.multiply(((MultiplicationConverter) converter).factor));
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
        }
        if (!(o instanceof MultiplicationConverter)) {
            return false;
        }
        MultiplicationConverter that = (MultiplicationConverter) o;
        return factor.equals(that.factor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factor);
    }
}

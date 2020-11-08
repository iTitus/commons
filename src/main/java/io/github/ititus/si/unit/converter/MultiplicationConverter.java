package io.github.ititus.si.unit.converter;

import java.util.List;
import java.util.Objects;

public class MultiplicationConverter implements UnitConverter {

    private final double factor;

    public static UnitConverter of(double factor) {
        if (factor == 0) {
            throw new IllegalArgumentException("0 not allowed");
        } else if (factor == 1) {
            return IDENTITY;
        }

        return new MultiplicationConverter(factor);
    }

    private MultiplicationConverter(double factor) {
        this.factor = factor;
    }

    @Override
    public double convert(double value) {
        return factor * value;
    }

    @Override
    public UnitConverter inverse() {
        return new MultiplicationConverter(1 / factor);
    }

    @Override
    public UnitConverter concat(UnitConverter converter) {
        return CompoundConverter.of(List.of(this, converter));
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
        return Double.compare(that.factor, factor) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(factor);
    }
}

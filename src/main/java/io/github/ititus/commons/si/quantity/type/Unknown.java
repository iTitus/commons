package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.Dimension;
import io.github.ititus.commons.si.unit.Unit;

public final class Unknown implements QuantityType<Unknown> {

    public static final Unknown UNKNOWN = new Unknown();

    private Unknown() {
    }

    @Override
    public Dimension getDimension() {
        throw new UnsupportedOperationException("the type of this quantity is unknown, thus it has no associated dimension");
    }

    @Override
    public Unit<Unknown> getStandardUnit() {
        throw new UnsupportedOperationException("the type of this quantity is unknown, thus it has no associated standard unit");
    }

    @Override
    public String toString() {
        return "Unknown";
    }
}

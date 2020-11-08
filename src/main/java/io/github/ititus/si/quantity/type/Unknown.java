package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.Dimension;
import io.github.ititus.si.unit.Unit;

public class Unknown implements QuantityType<Unknown> {

    public static final Unknown UNKNOWN = new Unknown();

    private Unknown() {
    }

    @Override
    public Dimension getDimension() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unit<Unknown> getStandardUnit() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends QuantityType<T>> boolean isCommensurableWith(T type) {
        return true;
    }
}

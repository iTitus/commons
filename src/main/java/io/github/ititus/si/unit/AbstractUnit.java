package io.github.ititus.si.unit;

import io.github.ititus.si.dimension.Dimension;
import io.github.ititus.si.quantity.type.QuantityType;

abstract class AbstractUnit<Q extends QuantityType<Q>> implements Unit<Q> {

    private final Q type;
    private final Dimension dimension;

    protected AbstractUnit(Q type, Dimension dimension) {
        this.type = type;
        this.dimension = dimension;
    }

    @Override
    public Q getType() {
        return type;
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "type=" + type +
                ", symbol=" + getSymbol() +
                '}';
    }
}

package io.github.ititus.si.unit;

import io.github.ititus.si.quantity.type.QuantityType;

abstract class AbstractUnit<Q extends QuantityType<Q>> implements Unit<Q> {

    private final Q type;

    protected AbstractUnit(Q type) {
        this.type = type;
    }

    @Override
    public Q getType() {
        return type;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "type=" + type +
                ", symbol=" + getSymbol() +
                '}';
    }
}

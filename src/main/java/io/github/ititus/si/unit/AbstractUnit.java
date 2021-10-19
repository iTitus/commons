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
                ", dimension=" + getDimension().getString() +
                ", symbol=" + getSymbol() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Unit)) {
            return false;
        }

        Unit<?> that = (Unit<?>) o;
        return getDimension().equals(that.getDimension());
    }

    @Override
    public int hashCode() {
        return getDimension().hashCode();
    }
}

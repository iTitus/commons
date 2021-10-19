package io.github.ititus.si.unit;

import io.github.ititus.si.dimension.Dimension;
import io.github.ititus.si.quantity.type.QuantityType;

import java.util.Objects;

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
                ", dimension=" + dimension.getString() +
                ", symbol=" + getSymbol() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AbstractUnit)) {
            return false;
        }

        AbstractUnit<?> that = (AbstractUnit<?>) o;
        return /*type.equals(that.type) &&*/ dimension.equals(that.dimension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(/*type,*/ dimension);
    }
}

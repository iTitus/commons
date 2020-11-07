package io.github.ititus.si.quantity;

import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.unit.Unit;

public class QuantityImpl<Q extends QuantityType<Q>> extends AbstractQuantity<Q> {

    private final double value;
    private final Unit<Q> unit;

    public QuantityImpl(double value, Unit<Q> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Q> getUnit() {
        return unit;
    }

    @Override
    public Quantity<Q> convertTo(Unit<Q> unit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends QuantityType<T>> Quantity<T> as(T type) throws ClassCastException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Quantity<?> multiply(Quantity<?> qty) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Quantity<?> inverse() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Quantity<?> pow(int n) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Quantity<?> root(int n) {
        throw new UnsupportedOperationException();
    }
}

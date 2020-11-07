package io.github.ititus.si.unit;

import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.Quantity;
import io.github.ititus.si.quantity.type.QuantityType;

class BaseUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    BaseUnit(Q type) {
        super(type);
    }

    @Override
    public String getSymbol() {
        throw new UnsupportedOperationException();
    }

    @Override
    public UnitConverter getConverterTo(Unit<Q> unit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Quantity<Q> get(double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends QuantityType<T>> Unit<T> as(T type) throws ClassCastException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unit<Q> multiply(double d) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unit<?> multiply(Unit<?> unit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unit<?> inverse() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unit<Q> divide(double d) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unit<?> divide(Unit<?> unit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unit<?> pow(int n) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unit<?> root(int n) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unit<Q> alternate(String symbol) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unit<Q> prefix(Prefix prefix) {
        throw new UnsupportedOperationException();
    }
}

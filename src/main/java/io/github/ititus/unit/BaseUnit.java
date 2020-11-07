package io.github.ititus.unit;

class BaseUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    BaseUnit(Dimension dimension) {
        super(dimension);
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
}

package io.github.ititus.unit;

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
    public Quantity<Q> add(Quantity<Q> qty) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Quantity<Q> negate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Quantity<Q> subtract(Quantity<Q> qty) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Quantity<Q> multiply(double d) {
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
    public Quantity<Q> divide(double d) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Quantity<?> divide(Quantity<?> qty) {
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

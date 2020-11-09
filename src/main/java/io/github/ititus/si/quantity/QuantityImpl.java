package io.github.ititus.si.quantity;

import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.unit.Unit;

import java.util.Objects;

final class QuantityImpl<Q extends QuantityType<Q>> extends AbstractQuantity<Q> {

    private final double value;
    private final Unit<Q> unit;

    QuantityImpl(double value, Unit<Q> unit) {
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
        if (this.unit.equals(unit)) {
            return this;
        }

        return new QuantityImpl<>(this.unit.getConverterTo(unit).convert(value), unit);
    }

    @Override
    public <T extends QuantityType<T>> Quantity<T> as(T type) {
        return new QuantityImpl<>(value, unit.as(type));
    }

    @Override
    public Quantity<?> multiply(Quantity<?> qty) {
        return new QuantityImpl<>(value * qty.getValue(), unit.multiply(qty.getUnit()));
    }

    @Override
    public Quantity<?> inverse() {
        return new QuantityImpl<>(1 / value, unit.inverse());
    }

    @Override
    public Quantity<?> pow(int n) {
        return new QuantityImpl<>(Math.pow(value, n), unit.pow(n));
    }

    @Override
    public Quantity<?> root(int n) {
        return new QuantityImpl<>(Math.pow(value, 1.0 / n), unit.root(n));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuantityImpl)) {
            return false;
        }
        QuantityImpl<?> quantity = (QuantityImpl<?>) o;
        return Double.compare(quantity.value, value) == 0 && unit.equals(quantity.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }
}

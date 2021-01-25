package io.github.ititus.si.quantity;

import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.quantity.value.QuantityValue;
import io.github.ititus.si.unit.Unit;

import java.util.Objects;

final class QuantityImpl<Q extends QuantityType<Q>> implements Quantity<Q> {

    private final QuantityValue value;
    private final Unit<Q> unit;

    QuantityImpl(QuantityValue value, Unit<Q> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public QuantityValue getValue() {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuantityImpl)) {
            return false;
        }
        QuantityImpl<?> quantity = (QuantityImpl<?>) o;
        return value.equals(quantity.value) && unit.equals(quantity.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}

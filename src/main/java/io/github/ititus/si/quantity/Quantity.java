package io.github.ititus.si.quantity;

import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.unit.Unit;

public interface Quantity<Q extends QuantityType<Q>> {

    static <T extends QuantityType<T>> Quantity<T> of(double value, Unit<T> unit) {
        return new QuantityImpl<>(value, unit);
    }

    double getValue();

    Unit<Q> getUnit();

    Quantity<Q> convertTo(Unit<Q> unit);

    default Quantity<Q> convertToStandard() {
        return convertTo(getUnit().getType().getStandardUnit());
    }

    <T extends QuantityType<T>> Quantity<T> as(T type);

    default <T extends QuantityType<T>> Quantity<T> asStandard(T type) {
        return as(type.getStandardUnit());
    }

    default <T extends QuantityType<T>> Quantity<T> as(Unit<T> unit) {
        return as(unit.getType()).convertTo(unit);
    }

    default Quantity<Q> add(Quantity<Q> qty) {
        return getUnit().get(getValue() + qty.convertTo(getUnit()).getValue());
    }

    default Quantity<Q> negate() {
        return getUnit().get(-getValue());
    }

    default Quantity<Q> subtract(Quantity<Q> qty) {
        return add(qty.negate());
    }

    default Quantity<Q> multiply(double d) {
        return getUnit().get(getValue() * d);
    }

    Quantity<?> multiply(Quantity<?> qty);

    Quantity<?> inverse();

    default Quantity<Q> divide(double d) {
        return getUnit().get(getValue() / d);
    }

    default Quantity<?> divide(Quantity<?> qty) {
        return multiply(qty.inverse());
    }

    Quantity<?> pow(int n);

    Quantity<?> root(int n);

}

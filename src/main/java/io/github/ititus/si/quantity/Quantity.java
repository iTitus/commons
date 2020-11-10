package io.github.ititus.si.quantity;

import io.github.ititus.math.number.BigRational;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.quantity.value.QuantityValue;
import io.github.ititus.si.unit.Unit;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface Quantity<Q extends QuantityType<Q>> {

    static <T extends QuantityType<T>> Quantity<T> of(QuantityValue value, Unit<T> unit) {
        return new QuantityImpl<>(value, unit);
    }

    QuantityValue getValue();

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
        return getUnit().get(getValue().add(qty.convertTo(getUnit()).getValue()));
    }

    default Quantity<Q> negate() {
        return getUnit().get(getValue().negate());
    }

    default Quantity<Q> subtract(Quantity<Q> qty) {
        return add(qty.negate());
    }

    default Quantity<Q> multiply(int n) {
        return multiply(QuantityValue.of(n));
    }

    default Quantity<Q> multiply(long n) {
        return multiply(QuantityValue.of(n));
    }

    default Quantity<Q> multiply(BigInteger n) {
        return multiply(QuantityValue.of(n));
    }

    default Quantity<Q> multiply(float f) {
        return multiply(QuantityValue.of(f));
    }

    default Quantity<Q> multiply(double d) {
        return multiply(QuantityValue.of(d));
    }

    default Quantity<Q> multiply(BigDecimal d) {
        return multiply(QuantityValue.of(d));
    }

    default Quantity<Q> multiply(BigRational r) {
        return multiply(QuantityValue.of(r));
    }

    default Quantity<Q> multiply(QuantityValue value) {
        return getUnit().get(getValue().multiply(value));
    }

    default Quantity<?> multiply(Quantity<?> qty) {
        return getUnit().multiply(qty.getUnit()).get(getValue().multiply(qty.getValue()));
    }

    default Quantity<?> inverse() {
        return getUnit().inverse().get(getValue().inverse());
    }

    default Quantity<Q> divide(int n) {
        return divide(BigRational.of(n));
    }

    default Quantity<Q> divide(long n) {
        return divide(BigRational.of(n));
    }

    default Quantity<Q> divide(BigInteger n) {
        return divide(QuantityValue.of(n));
    }

    default Quantity<Q> divide(float f) {
        return divide(QuantityValue.of(f));
    }

    default Quantity<Q> divide(double d) {
        return divide(QuantityValue.of(d));
    }

    default Quantity<Q> divide(BigDecimal d) {
        return divide(QuantityValue.of(d));
    }

    default Quantity<Q> divide(BigRational r) {
        return divide(QuantityValue.of(r));
    }

    default Quantity<Q> divide(QuantityValue value) {
        return getUnit().get(getValue().divide(value));
    }

    default Quantity<?> divide(Quantity<?> qty) {
        return multiply(qty.inverse());
    }

    default Quantity<?> pow(int n) {
        return getUnit().pow(n).get(getValue().pow(n));
    }

    default Quantity<?> root(int n) {
        return getUnit().root(n).get(getValue().root(n));
    }
}

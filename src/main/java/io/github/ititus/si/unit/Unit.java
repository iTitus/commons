package io.github.ititus.si.unit;

import io.github.ititus.math.number.BigRational;
import io.github.ititus.si.dimension.Dimension;
import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.Quantity;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.quantity.value.QuantityValue;
import io.github.ititus.si.unit.converter.UnitConverter;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface Unit<Q extends QuantityType<Q>> {

    String getSymbol();

    Q getType();

    Dimension getDimension();

    <T extends QuantityType<T>> UnitConverter getConverterTo(Unit<T> unit);

    default Quantity<Q> get(int value) {
        return Quantity.of(QuantityValue.of(value), this);
    }

    default Quantity<Q> get(long value) {
        return Quantity.of(QuantityValue.of(value), this);
    }

    default Quantity<Q> get(BigInteger value) {
        return Quantity.of(QuantityValue.of(value), this);
    }

    default Quantity<Q> get(float value) {
        return Quantity.of(QuantityValue.of(value), this);
    }

    default Quantity<Q> get(double value) {
        return Quantity.of(QuantityValue.of(value), this);
    }

    default Quantity<Q> get(BigDecimal value) {
        return Quantity.of(QuantityValue.of(value), this);
    }

    default Quantity<Q> get(BigRational value) {
        return Quantity.of(QuantityValue.of(value), this);
    }

    default Quantity<Q> get(QuantityValue value) {
        return Quantity.of(value, this);
    }

    default <T extends QuantityType<T>> boolean isCommensurableWith(T type) {
        return getDimension().isCommensurableWith(type.getDimension());
    }

    <T extends QuantityType<T>> Unit<T> as(T type);

    default Unit<Q> shift(int n) {
        return shift(BigRational.of(n));
    }

    default Unit<Q> shift(long n) {
        return shift(QuantityValue.of(n));
    }

    default Unit<Q> shift(BigInteger n) {
        return shift(QuantityValue.of(n));
    }

    default Unit<Q> shift(float f) {
        return shift(QuantityValue.of(f));
    }

    default Unit<Q> shift(double d) {
        return shift(QuantityValue.of(d));
    }

    default Unit<Q> shift(BigDecimal d) {
        return shift(QuantityValue.of(d));
    }

    default Unit<Q> shift(BigRational r) {
        return shift(QuantityValue.of(r));
    }

    Unit<Q> shift(QuantityValue v);

    default Unit<Q> multiply(int n) {
        return multiply(BigRational.of(n));
    }

    default Unit<Q> multiply(long n) {
        return multiply(QuantityValue.of(n));
    }

    default Unit<Q> multiply(BigInteger n) {
        return multiply(QuantityValue.of(n));
    }

    default Unit<Q> multiply(float f) {
        return multiply(QuantityValue.of(f));
    }

    default Unit<Q> multiply(double d) {
        return multiply(QuantityValue.of(d));
    }

    default Unit<Q> multiply(BigDecimal d) {
        return multiply(QuantityValue.of(d));
    }

    default Unit<Q> multiply(BigRational r) {
        return multiply(QuantityValue.of(r));
    }

    Unit<Q> multiply(QuantityValue v);

    Unit<?> multiply(Unit<?> unit);

    Unit<?> inverse();

    default Unit<Q> divide(int n) {
        return divide(QuantityValue.of(n));
    }

    default Unit<Q> divide(long n) {
        return divide(QuantityValue.of(n));
    }

    default Unit<Q> divide(BigInteger n) {
        return divide(QuantityValue.of(n));
    }

    default Unit<Q> divide(float f) {
        return divide(QuantityValue.of(f));
    }

    default Unit<Q> divide(double d) {
        return divide(QuantityValue.of(d));
    }

    default Unit<Q> divide(BigDecimal d) {
        return divide(QuantityValue.of(d));
    }

    default Unit<Q> divide(BigRational r) {
        return divide(QuantityValue.of(r));
    }

    default Unit<Q> divide(QuantityValue r) {
        return multiply(r.inverse());
    }

    default Unit<?> divide(Unit<?> unit) {
        return multiply(unit.inverse());
    }

    Unit<?> pow(int n);

    Unit<?> root(int n);

    Unit<Q> alternate(String symbol);

    Unit<Q> prefix(Prefix prefix);

}

package io.github.ititus.si.unit;

import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.Quantity;
import io.github.ititus.si.quantity.type.QuantityType;

public interface Unit<Q extends QuantityType<Q>> {

    String getSymbol();

    Q getType();

    UnitConverter getConverterTo(Unit<Q> unit);

    Quantity<Q> get(double value);

    <T extends QuantityType<T>> Unit<T> as(T type) throws ClassCastException;

    Unit<Q> multiply(double d);

    Unit<?> multiply(Unit<?> unit);

    Unit<?> inverse();

    Unit<Q> divide(double d);

    Unit<?> divide(Unit<?> unit);

    Unit<?> pow(int n);

    Unit<?> root(int n);

    Unit<Q> alternate(String symbol);

    Unit<Q> prefix(Prefix prefix);

}

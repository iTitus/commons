package io.github.ititus.si.unit;

import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.Quantity;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.unit.converter.UnitConverter;

public interface Unit<Q extends QuantityType<Q>> {

    String getSymbol();

    Q getType();

    UnitConverter getConverterTo(Unit<Q> unit);

    default Quantity<Q> get(double value) {
        return Quantity.of(value, this);
    }

    <T extends QuantityType<T>> Unit<T> as(T type);

    Unit<Q> multiply(double d);

    Unit<?> multiply(Unit<?> unit);

    Unit<?> inverse();

    default Unit<Q> divide(double d) {
        return multiply(1 / d);
    }

    default Unit<?> divide(Unit<?> unit) {
        return multiply(unit.inverse());
    }

    Unit<?> pow(int n);

    Unit<?> root(int n);

    Unit<Q> alternate(String symbol);

    Unit<Q> prefix(Prefix prefix);

}

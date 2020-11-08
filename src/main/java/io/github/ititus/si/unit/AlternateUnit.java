package io.github.ititus.si.unit;

import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.unit.converter.UnitConverter;

final class AlternateUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    private final Unit<Q> baseUnit;
    private final String alternateSymbol;

    AlternateUnit(Unit<Q> baseUnit, String alternateSymbol) {
        super(baseUnit.getType());
        this.baseUnit = baseUnit;
        this.alternateSymbol = alternateSymbol;
    }

    @Override
    public String getSymbol() {
        return alternateSymbol;
    }

    @Override
    public UnitConverter getConverterTo(Unit<Q> unit) {
        return baseUnit.getConverterTo(unit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends QuantityType<T>> Unit<T> as(T type) {
        if (getType().equals(type)) {
            return (Unit<T>) this;
        }

        return new AlternateUnit<>(baseUnit.as(type), alternateSymbol);
    }

    @Override
    public Unit<Q> multiply(double d) {
        return baseUnit.multiply(d);
    }

    @Override
    public Unit<?> multiply(Unit<?> unit) {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public Unit<?> inverse() {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public Unit<?> pow(int n) {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public Unit<?> root(int n) {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public Unit<Q> alternate(String symbol) {
        return new AlternateUnit<>(baseUnit, symbol);
    }

    @Override
    public Unit<Q> prefix(Prefix prefix) {
        return new PrefixUnit<>(this, prefix);
    }
}

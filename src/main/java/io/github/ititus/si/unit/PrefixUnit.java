package io.github.ititus.si.unit;

import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.unit.converter.MultiplicationConverter;
import io.github.ititus.si.unit.converter.UnitConverter;

final class PrefixUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    private final Unit<Q> baseUnit;
    private final Prefix prefix;

    PrefixUnit(Unit<Q> baseUnit, Prefix prefix) {
        super(baseUnit.getType(), baseUnit.getDimension());
        this.baseUnit = baseUnit;
        this.prefix = prefix;
    }

    @Override
    public String getSymbol() {
        return prefix.getSymbol() + baseUnit.getSymbol();
    }

    @Override
    public UnitConverter getConverterTo(Unit<Q> unit) {
        if (equals(unit)) {
            return UnitConverter.IDENTITY;
        }

        UnitConverter c = MultiplicationConverter.of(Math.pow(prefix.getBase(), prefix.getExponent()));

        if (baseUnit.equals(unit)) {
            return c;
        }

        return c.concat(baseUnit.getConverterTo(unit));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends QuantityType<T>> Unit<T> as(T type) {
        if (getType().equals(type)) {
            return (Unit<T>) this;
        }

        return new PrefixUnit<>(baseUnit.as(type), prefix);
    }

    @Override
    public Unit<Q> multiply(double d) {
        return new ConvertedUnit<>(this, MultiplicationConverter.of(d));
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
        return new AlternateUnit<>(this, symbol);
    }

    @Override
    public Unit<Q> prefix(Prefix prefix) {
        return new PrefixUnit<>(baseUnit, prefix);
    }
}

package io.github.ititus.si.unit;

import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.unit.converter.MultiplicationConverter;
import io.github.ititus.si.unit.converter.UnitConverter;

final class ConvertedUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    private final Unit<Q> baseUnit;
    private final UnitConverter converter;

    ConvertedUnit(Unit<Q> baseUnit, UnitConverter converter) {
        super(baseUnit.getType());
        this.baseUnit = baseUnit;
        this.converter = converter;
    }

    @Override
    public String getSymbol() {
        throw new UnsupportedOperationException("converted units have not symbol");
    }

    @Override
    public UnitConverter getConverterTo(Unit<Q> unit) {
        if (equals(unit)) {
            return UnitConverter.IDENTITY;
        }

        return converter.concat(baseUnit.getConverterTo(unit));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends QuantityType<T>> Unit<T> as(T type) {
        if (!getType().isCommensurableWith(type)) {
            throw new ClassCastException();
        } else if (getType().equals(type)) {
            return (Unit<T>) this;
        }

        return new ConvertedUnit<>(baseUnit.as(type), converter);
    }

    @Override
    public Unit<Q> multiply(double d) {
        return new ConvertedUnit<>(baseUnit, converter.concat(MultiplicationConverter.of(d)));
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
        return new PrefixUnit<>(this, prefix);
    }
}

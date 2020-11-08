package io.github.ititus.si.unit;

import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.unit.converter.MultiplicationConverter;
import io.github.ititus.si.unit.converter.UnitConverter;

public final class BaseUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    private final String symbol;

    public BaseUnit(Q type, String symbol) {
        super(type, type.getDimension());
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public UnitConverter getConverterTo(Unit<Q> unit) {
        if (!isCommensurableWith(unit.getType())) {
            throw new ClassCastException();
        } else if (equals(unit)) {
            return UnitConverter.IDENTITY;
        }

        return unit.getConverterTo(this).inverse();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends QuantityType<T>> Unit<T> as(T type) {
        if (!isCommensurableWith(type)) {
            throw new ClassCastException();
        } else if (getType().equals(type)) {
            return (Unit<T>) this;
        }

        return new BaseUnit<>(type, symbol);
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
        throw new UnsupportedOperationException("cannot assign alternate symbol to a base unit");
    }

    @Override
    public Unit<Q> prefix(Prefix prefix) {
        return new PrefixUnit<>(this, prefix);
    }
}

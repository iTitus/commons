package io.github.ititus.si.unit;

import io.github.ititus.si.dimension.Dimension;
import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.unit.converter.UnitConverter;

final class CompoundUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    CompoundUnit(Q type, Dimension dimension) {
        // TODO: implement
        super(type, dimension);
    }

    @Override
    public String getSymbol() {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public UnitConverter getConverterTo(Unit<Q> unit) {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends QuantityType<T>> Unit<T> as(T type) {
        if (!isCommensurableWith(type)) {
            throw new ClassCastException();
        } else if (getType().equals(type)) {
            return (Unit<T>) this;
        }

        return new CompoundUnit<>(type, getDimension());
    }

    @Override
    public Unit<Q> multiply(double d) {
        throw new UnsupportedOperationException("NYI");
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

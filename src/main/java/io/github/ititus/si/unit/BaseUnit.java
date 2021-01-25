package io.github.ititus.si.unit;

import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.quantity.value.QuantityValue;
import io.github.ititus.si.unit.converter.UnitConverter;

import java.util.Objects;

final class BaseUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    private final String symbol;

    BaseUnit(Q type, String symbol) {
        super(type, type.getDimension());
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public <T extends QuantityType<T>> UnitConverter getConverterTo(Unit<T> unit) {
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
    public Unit<Q> multiply(QuantityValue v) {
        return ConvertedUnit.of(this, UnitConverter.factor(v));
    }

    @Override
    public Unit<?> multiply(Unit<?> unit) {
        return CompoundUnit.ofProduct(this, unit);
    }

    @Override
    public Unit<?> inverse() {
        return CompoundUnit.inverse(this);
    }

    @Override
    public Unit<?> pow(int n) {
        return CompoundUnit.ofPow(this, n);
    }

    @Override
    public Unit<?> root(int n) {
        return CompoundUnit.ofRoot(this, n);
    }

    @Override
    public Unit<Q> alternate(String symbol) {
        return new AlternateUnit<>(this, symbol);
    }

    @Override
    public Unit<Q> prefix(Prefix prefix) {
        return new PrefixUnit<>(this, prefix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseUnit)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BaseUnit<?> baseUnit = (BaseUnit<?>) o;
        return symbol.equals(baseUnit.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), symbol);
    }
}

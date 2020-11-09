package io.github.ititus.si.unit;

import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.unit.converter.UnitConverter;

import java.util.Objects;

final class AlternateUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    private final Unit<Q> baseUnit;
    private final String alternateSymbol;

    AlternateUnit(Unit<Q> baseUnit, String alternateSymbol) {
        super(baseUnit.getType(), baseUnit.getDimension());
        this.baseUnit = baseUnit;
        this.alternateSymbol = alternateSymbol;
    }

    @Override
    public String getSymbol() {
        return alternateSymbol;
    }

    @Override
    public <T extends QuantityType<T>> UnitConverter getConverterTo(Unit<T> unit) {
        if (equals(unit)) {
            return UnitConverter.IDENTITY;
        } else if (unit instanceof AlternateUnit) {
            return baseUnit.getConverterTo(((AlternateUnit<T>) unit).baseUnit);
        }

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
        return new AlternateUnit<>(baseUnit, symbol);
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
        if (!(o instanceof AlternateUnit)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AlternateUnit<?> that = (AlternateUnit<?>) o;
        return baseUnit.equals(that.baseUnit) && alternateSymbol.equals(that.alternateSymbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), baseUnit, alternateSymbol);
    }
}

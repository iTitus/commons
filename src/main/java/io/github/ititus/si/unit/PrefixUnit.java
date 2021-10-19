package io.github.ititus.si.unit;

import io.github.ititus.math.number.BigRational;
import io.github.ititus.si.NotCommensurableException;
import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.quantity.value.QuantityValue;
import io.github.ititus.si.unit.converter.UnitConverter;

import java.util.Objects;

final class PrefixUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    private final Unit<Q> baseUnit;
    private final Prefix prefix;

    PrefixUnit(Unit<Q> baseUnit, Prefix prefix) {
        super(baseUnit.getType());
        this.baseUnit = baseUnit;
        this.prefix = prefix;
    }

    @Override
    public String getSymbol() {
        return prefix.getSymbol() + baseUnit.getSymbol();
    }

    @Override
    public <T extends QuantityType<T>> UnitConverter getConverterTo(Unit<T> unit) {
        if (!isCommensurableWith(unit.getType())) {
            throw new NotCommensurableException();
        } else if (equals(unit)) {
            return UnitConverter.IDENTITY;
        }

        UnitConverter c = UnitConverter.factor(QuantityValue.of(
                BigRational.of(prefix.getBase()).pow(prefix.getExponent())
        ));

        if (baseUnit.equals(unit)) {
            return c;
        } else if (unit instanceof PrefixUnit) {
            PrefixUnit<T> prefixUnit = (PrefixUnit<T>) unit;
            if (baseUnit.equals(prefixUnit.baseUnit)) {
                return c.concat(prefixUnit.getConverterTo(prefixUnit.baseUnit).inverse());
            }
        }

        return c.concat(baseUnit.getConverterTo(unit));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends QuantityType<T>> Unit<T> as(T type) {
        if (!isCommensurableWith(type)) {
            throw new NotCommensurableException();
        } else if (getType().equals(type)) {
            return (Unit<T>) this;
        }

        return new PrefixUnit<>(baseUnit.as(type), prefix);
    }

    @Override
    public Unit<Q> shift(QuantityValue v) {
        return ConvertedUnit.of(this, UnitConverter.shift(v));
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
        return new PrefixUnit<>(baseUnit, prefix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof PrefixUnit)) {
            return false;
        } else if (!super.equals(o)) {
            return false;
        }

        PrefixUnit<?> that = (PrefixUnit<?>) o;
        return baseUnit.equals(that.baseUnit) && prefix.equals(that.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), baseUnit, prefix);
    }
}

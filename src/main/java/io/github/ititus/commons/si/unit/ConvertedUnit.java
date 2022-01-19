package io.github.ititus.commons.si.unit;

import io.github.ititus.commons.si.NotCommensurableException;
import io.github.ititus.commons.si.prefix.Prefix;
import io.github.ititus.commons.si.quantity.type.QuantityType;
import io.github.ititus.commons.si.quantity.value.QuantityValue;
import io.github.ititus.commons.si.unit.converter.UnitConverter;

import java.util.Objects;

final class ConvertedUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    private final Unit<Q> baseUnit;
    private final UnitConverter converter;

    private ConvertedUnit(Unit<Q> baseUnit, UnitConverter converter) {
        super(baseUnit.getType());
        this.baseUnit = baseUnit;
        this.converter = converter;
    }

    public static <Q extends QuantityType<Q>> Unit<Q> of(Unit<Q> baseUnit, UnitConverter converter) {
        if (converter.isIdentity()) {
            return baseUnit;
        }

        return new ConvertedUnit<>(baseUnit, converter);
    }

    @Override
    public String getSymbol() {
        throw new UnsupportedOperationException("converted units have no symbol");
    }

    @Override
    public <T extends QuantityType<T>> UnitConverter getConverterTo(Unit<T> unit) {
        if (!isCommensurableWith(unit.getType())) {
            throw new NotCommensurableException();
        } else if (equals(unit)) {
            return UnitConverter.IDENTITY;
        } else if (baseUnit.equals(unit)) {
            return converter;
        }

        return converter.concat(baseUnit.getConverterTo(unit));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends QuantityType<T>> Unit<T> as(T type) {
        if (getType().equals(type)) {
            return (Unit<T>) this;
        }

        return new ConvertedUnit<>(baseUnit.as(type), converter);
    }

    @Override
    public Unit<Q> shift(QuantityValue v) {
        return of(baseUnit, converter.concat(UnitConverter.shift(v)));
    }

    @Override
    public Unit<Q> multiply(QuantityValue v) {
        return of(baseUnit, converter.concat(UnitConverter.factor(v)));
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
        } else if (!(o instanceof ConvertedUnit)) {
            return false;
        } else if (!super.equals(o)) {
            return false;
        }

        ConvertedUnit<?> that = (ConvertedUnit<?>) o;
        return baseUnit.equals(that.baseUnit) && converter.equals(that.converter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), baseUnit, converter);
    }
}

package io.github.ititus.commons.si.unit.converter;

import io.github.ititus.commons.si.quantity.value.QuantityValue;

import java.util.List;

public interface UnitConverter {

    UnitConverter IDENTITY = IdentityConverter.IDENTITY;

    static UnitConverter shift(QuantityValue shift) {
        return LinearConverter.ofShift(shift);
    }

    static UnitConverter factor(QuantityValue factor) {
        return LinearConverter.ofFactor(factor);
    }

    static UnitConverter linear(QuantityValue factor, QuantityValue shift) {
        return LinearConverter.of(factor, shift);
    }

    static UnitConverter compound(List<UnitConverter> converters) {
        return CompoundConverter.of(converters);
    }

    QuantityValue convert(QuantityValue value);

    UnitConverter inverse();

    UnitConverter concat(UnitConverter converter);

    boolean isIdentity();

}

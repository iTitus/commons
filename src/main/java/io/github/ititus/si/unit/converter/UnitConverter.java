package io.github.ititus.si.unit.converter;

import io.github.ititus.si.quantity.value.QuantityValue;

import java.util.List;

public interface UnitConverter {

    IdentityConverter IDENTITY = IdentityConverter.IDENTITY;

    static UnitConverter factor(QuantityValue factor) {
        return MultiplicationConverter.of(factor);
    }

    static UnitConverter compound(List<UnitConverter> converters) {
        return CompoundConverter.of(converters);
    }

    QuantityValue convert(QuantityValue value);

    UnitConverter inverse();

    UnitConverter concat(UnitConverter converter);

    boolean isIdentity();

}

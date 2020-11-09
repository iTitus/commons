package io.github.ititus.si.unit.converter;

import java.util.List;

public interface UnitConverter {

    IdentityConverter IDENTITY = IdentityConverter.IDENTITY;

    static UnitConverter factor(double factor) {
        return MultiplicationConverter.of(factor);
    }

    static UnitConverter compound(List<UnitConverter> converters) {
        return CompoundConverter.of(converters);
    }

    double convert(double value);

    UnitConverter inverse();

    UnitConverter concat(UnitConverter converter);

    boolean isIdentity();

}

package io.github.ititus.si.unit.converter;

public interface UnitConverter {

    IdentityConverter IDENTITY = IdentityConverter.IDENTITY;

    double convert(double value);

    UnitConverter inverse();

    UnitConverter concat(UnitConverter converter);

    boolean isIdentity();

}

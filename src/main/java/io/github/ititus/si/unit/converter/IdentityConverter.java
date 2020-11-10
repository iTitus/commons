package io.github.ititus.si.unit.converter;

import io.github.ititus.si.quantity.value.QuantityValue;

final class IdentityConverter implements UnitConverter {

    static final IdentityConverter IDENTITY = new IdentityConverter();

    private IdentityConverter() {
    }

    @Override
    public QuantityValue convert(QuantityValue value) {
        return value;
    }

    @Override
    public UnitConverter inverse() {
        return this;
    }

    @Override
    public UnitConverter concat(UnitConverter converter) {
        return converter;
    }

    @Override
    public boolean isIdentity() {
        return true;
    }
}

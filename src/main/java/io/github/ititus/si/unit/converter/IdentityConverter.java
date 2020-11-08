package io.github.ititus.si.unit.converter;

final class IdentityConverter implements UnitConverter {

    static final IdentityConverter IDENTITY = new IdentityConverter();

    private IdentityConverter() {
    }

    @Override
    public double convert(double value) {
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

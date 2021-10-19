package io.github.ititus.si.dimension;

import java.util.Map;

final class NoneDimension extends AbstractDimension {

    static final NoneDimension NONE = new NoneDimension();

    private NoneDimension() {
    }

    @Override
    public String getString() {
        return "[]";
    }

    @Override
    public Map<BaseDimension, Integer> getBaseDimensions() {
        return Map.of();
    }
}

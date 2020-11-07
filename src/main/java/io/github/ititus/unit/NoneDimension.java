package io.github.ititus.unit;

import java.util.Map;

class NoneDimension implements Dimension {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dimension)) {
            return false;
        }
        Dimension that = (Dimension) o;
        return getBaseDimensions().equals(that.getBaseDimensions());
    }

    @Override
    public int hashCode() {
        return getBaseDimensions().hashCode();
    }
}

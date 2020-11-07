package io.github.ititus.unit;

import java.util.Map;

public interface Dimension {

    Dimension NONE = NoneDimension.NONE;

    String getString();

    Map<BaseDimension, Integer> getBaseDimensions();

    default boolean isCommensurableWith(Dimension dim) {
        return equals(dim);
    }

    default Dimension multiply(Dimension dim) {
        return CompoundDimension.multiply(getBaseDimensions(), dim.getBaseDimensions());
    }

    default Dimension inverse() {
        return CompoundDimension.inverse(getBaseDimensions());
    }

    default Dimension divide(Dimension dim) {
        return CompoundDimension.divide(getBaseDimensions(), dim.getBaseDimensions());
    }

    default Dimension pow(int n) {
        return CompoundDimension.pow(getBaseDimensions(), n);
    }

    default Dimension root(int n) {
        return CompoundDimension.root(getBaseDimensions(), n);
    }
}

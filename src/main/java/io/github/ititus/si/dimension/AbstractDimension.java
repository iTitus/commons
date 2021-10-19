package io.github.ititus.si.dimension;

public abstract class AbstractDimension implements Dimension {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Dimension)) {
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

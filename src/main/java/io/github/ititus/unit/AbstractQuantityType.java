package io.github.ititus.unit;

public abstract class AbstractQuantityType<Q extends QuantityType<Q>> implements QuantityType<Q> {

    private final Dimension dimension;

    public AbstractQuantityType(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }
}

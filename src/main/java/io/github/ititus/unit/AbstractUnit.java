package io.github.ititus.unit;

abstract class AbstractUnit<Q extends QuantityType<Q>> implements Unit<Q> {

    private final Dimension dimension;

    protected AbstractUnit(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }
}

package io.github.ititus.unit;

public interface QuantityType<Q extends QuantityType<Q>> {



    Dimension getDimension();

}

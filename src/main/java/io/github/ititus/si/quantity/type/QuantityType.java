package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.Dimension;
import io.github.ititus.si.unit.Unit;

public interface QuantityType<Q extends QuantityType<Q>> {

    Dimension getDimension();

    Unit<Q> getStandardUnit();

}

package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.Dimension;
import io.github.ititus.commons.si.unit.Unit;

public interface QuantityType<Q extends QuantityType<Q>> {

    Dimension getDimension();

    Unit<Q> getStandardUnit();

}

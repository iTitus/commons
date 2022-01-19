package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.Dimension;

import static io.github.ititus.commons.si.unit.Units.STERADIAN;

public final class SolidAngle extends AbstractQuantityType<SolidAngle> {

    public static final SolidAngle SOLID_ANGLE = new SolidAngle();

    private SolidAngle() {
        super(Dimension.NONE, () -> STERADIAN);
    }
}

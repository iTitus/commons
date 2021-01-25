package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.Dimension.NONE;
import static io.github.ititus.si.unit.Units.STERADIAN;

public final class SolidAngle extends AbstractQuantityType<SolidAngle> {

    public static final SolidAngle SOLID_ANGLE = new SolidAngle();

    private SolidAngle() {
        super(NONE, () -> STERADIAN);
    }
}

package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.Dimension.NONE;
import static io.github.ititus.si.unit.Units.RADIAN;

public final class Angle extends AbstractQuantityType<Angle> {

    public static final Angle ANGLE = new Angle();

    private Angle() {
        super(NONE, () -> RADIAN);
    }
}

package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.Dimension;

import static io.github.ititus.commons.si.unit.Units.RADIAN;

public final class Angle extends AbstractQuantityType<Angle> {

    public static final Angle ANGLE = new Angle();

    private Angle() {
        super(Dimension.NONE, () -> RADIAN);
    }
}

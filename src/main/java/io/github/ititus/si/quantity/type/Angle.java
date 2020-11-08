package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.Dimension;
import io.github.ititus.si.unit.Unit;

public final class Angle extends AbstractQuantityType<Angle> {

    public static final Angle ANGLE = new Angle();

    public static final Unit<Angle> RADIANS = Dimensionless.ONE.as(ANGLE);

    private Angle() {
        super(Dimension.NONE, () -> RADIANS);
    }
}

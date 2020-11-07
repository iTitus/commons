package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.unit.Unit;

public final class Force extends AbstractQuantityType<Force> {

    public static final Force FORCE = new Force();

    public static final Unit<Force> NEWTON =
            Mass.KILOGRAM.multiply(Acceleration.METRES_PER_SECOND_SQUARED).alternate("N").as(FORCE);

    private Force() {
        super(BaseDimension.MASS.multiply(BaseDimension.LENGTH.divide(BaseDimension.TIME.pow(2))), () -> NEWTON);
    }
}

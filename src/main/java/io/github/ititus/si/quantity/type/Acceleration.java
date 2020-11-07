package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.unit.Unit;

public final class Acceleration extends AbstractQuantityType<Acceleration> {

    public static final Acceleration ACCELERATION = new Acceleration();

    public static final Unit<Acceleration> METRES_PER_SECOND_SQUARED =
            Length.METRE.divide(Time.SECOND.pow(2)).as(ACCELERATION);

    private Acceleration() {
        super(BaseDimension.LENGTH.divide(BaseDimension.TIME.pow(2)), () -> METRES_PER_SECOND_SQUARED);
    }
}

package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.si.dimension.BaseDimension.TIME;
import static io.github.ititus.si.unit.Units.METRES_PER_SECOND_SQUARED;

public final class Acceleration extends AbstractQuantityType<Acceleration> {

    public static final Acceleration ACCELERATION = new Acceleration();

    private Acceleration() {
        super(LENGTH.divide(TIME.pow(2)), () -> METRES_PER_SECOND_SQUARED);
    }
}

package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.*;
import static io.github.ititus.si.unit.Units.KILOGRAM_METRES_PER_SECOND;

public final class Momentum extends AbstractQuantityType<Momentum> {

    public static final Momentum MOMENTUM = new Momentum();

    private Momentum() {
        super(MASS.multiply(LENGTH.divide(TIME)), () -> KILOGRAM_METRES_PER_SECOND);
    }
}

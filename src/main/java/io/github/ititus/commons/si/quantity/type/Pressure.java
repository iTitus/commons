package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.*;
import static io.github.ititus.commons.si.unit.Units.PASCAL;

public final class Pressure extends AbstractQuantityType<Pressure> {

    public static final Pressure PRESSURE = new Pressure();

    private Pressure() {
        super(MASS.divide(LENGTH.multiply(TIME.pow(2))), () -> PASCAL);
    }
}

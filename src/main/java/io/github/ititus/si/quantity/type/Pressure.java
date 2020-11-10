package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.*;
import static io.github.ititus.si.unit.Units.PASCAL;

public final class Pressure extends AbstractQuantityType<Pressure> {

    public static final Pressure PRESSURE = new Pressure();

    private Pressure() {
        super(MASS.divide(LENGTH.multiply(TIME.pow(2))), () -> PASCAL);
    }
}

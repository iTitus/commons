package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.*;
import static io.github.ititus.si.unit.Units.NEWTON;

public final class Force extends AbstractQuantityType<Force> {

    public static final Force FORCE = new Force();

    private Force() {
        super(MASS.multiply(LENGTH.divide(TIME.pow(2))), () -> NEWTON);
    }
}

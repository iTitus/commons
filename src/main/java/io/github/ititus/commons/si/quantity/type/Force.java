package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.*;
import static io.github.ititus.commons.si.unit.Units.NEWTON;

public final class Force extends AbstractQuantityType<Force> {

    public static final Force FORCE = new Force();

    private Force() {
        super(MASS.multiply(LENGTH).divide(TIME.pow(2)), () -> NEWTON);
    }
}

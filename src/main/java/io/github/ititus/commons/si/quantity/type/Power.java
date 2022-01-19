package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.*;
import static io.github.ititus.commons.si.unit.Units.WATT;

public final class Power extends AbstractQuantityType<Power> {

    public static final Power POWER = new Power();

    private Power() {
        super(MASS.multiply(LENGTH.pow(2)).divide(TIME.pow(3)), () -> WATT);
    }
}

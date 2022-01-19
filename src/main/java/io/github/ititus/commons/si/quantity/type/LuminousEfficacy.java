package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.*;
import static io.github.ititus.commons.si.unit.Units.LUMEN_PER_WATT;

public final class LuminousEfficacy extends AbstractQuantityType<LuminousEfficacy> {

    public static final LuminousEfficacy LUMINOUS_EFFICACY = new LuminousEfficacy();

    private LuminousEfficacy() {
        super(TIME.pow(3).multiply(LUMINOUS_INTENSITY).divide(MASS.multiply(LENGTH.pow(2))), () -> LUMEN_PER_WATT);
    }
}

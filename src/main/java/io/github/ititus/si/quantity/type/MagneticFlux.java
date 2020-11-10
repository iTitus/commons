package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.*;
import static io.github.ititus.si.unit.Units.WEBER;

public final class MagneticFlux extends AbstractQuantityType<MagneticFlux> {

    public static final MagneticFlux MAGNETIC_FLUX = new MagneticFlux();

    private MagneticFlux() {
        super(MASS.multiply(LENGTH.pow(2)).divide(TIME.pow(2).multiply(ELECTRIC_CURRENT)), () -> WEBER);
    }
}

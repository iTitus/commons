package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.*;
import static io.github.ititus.commons.si.unit.Units.TESLA;

public final class MagneticFluxDensity extends AbstractQuantityType<MagneticFluxDensity> {

    public static final MagneticFluxDensity MAGNETIC_FLUX_DENSITY = new MagneticFluxDensity();

    private MagneticFluxDensity() {
        super(MASS.divide(TIME.pow(2).multiply(ELECTRIC_CURRENT)), () -> TESLA);
    }
}

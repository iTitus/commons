package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.*;
import static io.github.ititus.si.unit.Units.HENRY_PER_METRE;

public final class MagneticPermeability extends AbstractQuantityType<MagneticPermeability> {

    public static final MagneticPermeability MAGNETIC_PERMEABILITY = new MagneticPermeability();

    private MagneticPermeability() {
        super(LENGTH.multiply(MASS).divide(TIME.pow(2).multiply(ELECTRIC_CURRENT.pow(2))), () -> HENRY_PER_METRE);
    }
}

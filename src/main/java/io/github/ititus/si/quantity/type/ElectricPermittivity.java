package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.*;
import static io.github.ititus.si.unit.Units.FARAD_PER_METRE;

public final class ElectricPermittivity extends AbstractQuantityType<ElectricPermittivity> {

    public static final ElectricPermittivity ELECTRIC_PERMITTIVITY = new ElectricPermittivity();

    private ElectricPermittivity() {
        super(TIME.pow(4).multiply(ELECTRIC_CURRENT.pow(2)).divide(LENGTH.pow(3).multiply(MASS)),
                () -> FARAD_PER_METRE);
    }
}

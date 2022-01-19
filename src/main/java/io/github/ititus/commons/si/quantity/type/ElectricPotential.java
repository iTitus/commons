package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.*;
import static io.github.ititus.commons.si.unit.Units.VOLT;

public final class ElectricPotential extends AbstractQuantityType<ElectricPotential> {

    public static final ElectricPotential ELECTRIC_POTENTIAL = new ElectricPotential();

    private ElectricPotential() {
        super(MASS.multiply(LENGTH.pow(2)).divide(TIME.pow(3).multiply(ELECTRIC_CURRENT)), () -> VOLT);
    }
}

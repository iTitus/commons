package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.*;
import static io.github.ititus.si.unit.Units.SIEMENS;

public final class ElectricConductance extends AbstractQuantityType<ElectricConductance> {

    public static final ElectricConductance ELECTRIC_CONDUCTANCE = new ElectricConductance();

    private ElectricConductance() {
        super(TIME.pow(3).multiply(ELECTRIC_CURRENT.pow(2)).divide(MASS.multiply(LENGTH.pow(2))), () -> SIEMENS);
    }
}

package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.*;
import static io.github.ititus.commons.si.unit.Units.FARAD;

public final class ElectricCapacitance extends AbstractQuantityType<ElectricCapacitance> {

    public static final ElectricCapacitance ELECTRIC_CAPACITANCE = new ElectricCapacitance();

    private ElectricCapacitance() {
        super(TIME.pow(4).multiply(ELECTRIC_CURRENT.pow(2)).divide(MASS.multiply(LENGTH.pow(2))), () -> FARAD);
    }
}

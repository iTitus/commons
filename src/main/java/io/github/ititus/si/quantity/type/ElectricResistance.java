package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.*;
import static io.github.ititus.si.unit.Units.OHM;

public final class ElectricResistance extends AbstractQuantityType<ElectricResistance> {

    public static final ElectricResistance ELECTRIC_RESISTANCE = new ElectricResistance();

    private ElectricResistance() {
        super(MASS.multiply(LENGTH.pow(2)).divide(TIME.pow(3).multiply(ELECTRIC_CURRENT.pow(2))), () -> OHM);
    }
}

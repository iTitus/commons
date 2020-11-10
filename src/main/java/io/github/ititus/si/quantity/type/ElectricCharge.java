package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.ELECTRIC_CURRENT;
import static io.github.ititus.si.dimension.BaseDimension.TIME;
import static io.github.ititus.si.unit.Units.COULOMB;

public final class ElectricCharge extends AbstractQuantityType<ElectricCharge> {

    public static final ElectricCharge ELECTRIC_CHARGE = new ElectricCharge();

    private ElectricCharge() {
        super(ELECTRIC_CURRENT.multiply(TIME), () -> COULOMB);
    }
}

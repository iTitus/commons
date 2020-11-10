package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.*;
import static io.github.ititus.si.unit.Units.HENRY;

public final class ElectricInductance extends AbstractQuantityType<ElectricInductance> {

    public static final ElectricInductance ELECTRIC_INDUCTANCE = new ElectricInductance();

    private ElectricInductance() {
        super(MASS.multiply(LENGTH.pow(2)).divide(TIME.pow(2).multiply(ELECTRIC_CURRENT.pow(2))), () -> HENRY);
    }
}

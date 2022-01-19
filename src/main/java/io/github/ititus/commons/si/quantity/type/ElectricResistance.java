package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.BaseDimension;

import static io.github.ititus.commons.si.unit.Units.OHM;

public final class ElectricResistance extends AbstractQuantityType<ElectricResistance> {

    public static final ElectricResistance ELECTRIC_RESISTANCE = new ElectricResistance();

    private ElectricResistance() {
        super(BaseDimension.MASS.multiply(BaseDimension.LENGTH.pow(2)).divide(BaseDimension.TIME.pow(3).multiply(BaseDimension.ELECTRIC_CURRENT.pow(2))), () -> OHM);
    }
}

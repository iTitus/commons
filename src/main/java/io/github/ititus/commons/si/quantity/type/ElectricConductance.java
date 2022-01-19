package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.BaseDimension;

import static io.github.ititus.commons.si.unit.Units.SIEMENS;

public final class ElectricConductance extends AbstractQuantityType<ElectricConductance> {

    public static final ElectricConductance ELECTRIC_CONDUCTANCE = new ElectricConductance();

    private ElectricConductance() {
        super(BaseDimension.TIME.pow(3).multiply(BaseDimension.ELECTRIC_CURRENT.pow(2)).divide(BaseDimension.MASS.multiply(BaseDimension.LENGTH.pow(2))), () -> SIEMENS);
    }
}

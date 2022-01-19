package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.BaseDimension;

import static io.github.ititus.commons.si.unit.Units.AMPERE_PER_SQUARE_METRE;

public final class ElectricCurrentDensity extends AbstractQuantityType<ElectricCurrentDensity> {

    public static final ElectricCurrentDensity ELECTRIC_CURRENT_DENSITY = new ElectricCurrentDensity();

    private ElectricCurrentDensity() {
        super(BaseDimension.ELECTRIC_CURRENT.divide(BaseDimension.LENGTH.pow(2)), () -> AMPERE_PER_SQUARE_METRE);
    }
}

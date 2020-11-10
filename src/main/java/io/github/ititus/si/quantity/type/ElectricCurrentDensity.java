package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.ELECTRIC_CURRENT;
import static io.github.ititus.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.si.unit.Units.AMPERE_PER_SQUARE_METRE;

public final class ElectricCurrentDensity extends AbstractQuantityType<ElectricCurrentDensity> {

    public static final ElectricCurrentDensity ELECTRIC_CURRENT_DENSITY = new ElectricCurrentDensity();

    private ElectricCurrentDensity() {
        super(ELECTRIC_CURRENT.divide(LENGTH.pow(2)), () -> AMPERE_PER_SQUARE_METRE);
    }
}

package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.BaseDimension;

import static io.github.ititus.commons.si.unit.Units.AMPERE;

public final class ElectricCurrent extends AbstractQuantityType<ElectricCurrent> {

    public static final ElectricCurrent ELECTRIC_CURRENT = new ElectricCurrent();

    private ElectricCurrent() {
        super(BaseDimension.ELECTRIC_CURRENT, () -> AMPERE);
    }
}

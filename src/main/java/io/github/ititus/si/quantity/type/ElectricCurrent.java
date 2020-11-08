package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.unit.BaseUnit;
import io.github.ititus.si.unit.Unit;

public final class ElectricCurrent extends AbstractQuantityType<ElectricCurrent> {

    public static final ElectricCurrent ELECTRIC_CURRENT = new ElectricCurrent();

    public static final Unit<ElectricCurrent> AMPERE = new BaseUnit<>(ELECTRIC_CURRENT, "A");

    private ElectricCurrent() {
        super(BaseDimension.ELECTRIC_CURRENT, () -> AMPERE);
    }
}

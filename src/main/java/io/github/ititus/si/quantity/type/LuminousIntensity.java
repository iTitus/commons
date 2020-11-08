package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.unit.BaseUnit;
import io.github.ititus.si.unit.Unit;

public final class LuminousIntensity extends AbstractQuantityType<LuminousIntensity> {

    public static final LuminousIntensity LUMINOUS_INTENSITY = new LuminousIntensity();

    public static final Unit<LuminousIntensity> CANDELA = new BaseUnit<>(LUMINOUS_INTENSITY, "cd");

    private LuminousIntensity() {
        super(BaseDimension.LUMINOUS_INTENSITY, () -> CANDELA);
    }
}

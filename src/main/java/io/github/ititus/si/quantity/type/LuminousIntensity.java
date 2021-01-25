package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;

import static io.github.ititus.si.unit.Units.CANDELA;

public final class LuminousIntensity extends AbstractQuantityType<LuminousIntensity> {

    public static final LuminousIntensity LUMINOUS_INTENSITY = new LuminousIntensity();

    private LuminousIntensity() {
        super(BaseDimension.LUMINOUS_INTENSITY, () -> CANDELA);
    }
}

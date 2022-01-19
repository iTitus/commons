package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.BaseDimension;

import static io.github.ititus.commons.si.unit.Units.CANDELA;

public final class LuminousIntensity extends AbstractQuantityType<LuminousIntensity> {

    public static final LuminousIntensity LUMINOUS_INTENSITY = new LuminousIntensity();

    private LuminousIntensity() {
        super(BaseDimension.LUMINOUS_INTENSITY, () -> CANDELA);
    }
}

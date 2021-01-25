package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.LUMINOUS_INTENSITY;
import static io.github.ititus.si.unit.Units.LUMEN;

public final class LuminousFlux extends AbstractQuantityType<LuminousFlux> {

    public static final LuminousFlux LUMINOUS_FLUX = new LuminousFlux();

    private LuminousFlux() {
        super(LUMINOUS_INTENSITY, () -> LUMEN);
    }
}

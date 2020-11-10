package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.si.dimension.BaseDimension.LUMINOUS_INTENSITY;
import static io.github.ititus.si.unit.Units.LUX;

public final class Illuminance extends AbstractQuantityType<Illuminance> {

    public static final Illuminance ILLUMINANCE = new Illuminance();

    private Illuminance() {
        super(LUMINOUS_INTENSITY.divide(LENGTH.pow(2)), () -> LUX);
    }
}

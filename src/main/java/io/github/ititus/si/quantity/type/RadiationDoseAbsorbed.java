package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.si.dimension.BaseDimension.TIME;
import static io.github.ititus.si.unit.Units.GRAY;

public final class RadiationDoseAbsorbed extends AbstractQuantityType<RadiationDoseAbsorbed> {

    public static final RadiationDoseAbsorbed RADIATION_DOSE_ABSORBED = new RadiationDoseAbsorbed();

    private RadiationDoseAbsorbed() {
        super(LENGTH.pow(2).divide(TIME.pow(2)), () -> GRAY);
    }
}

package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.commons.si.dimension.BaseDimension.TIME;
import static io.github.ititus.commons.si.unit.Units.GRAY;

public final class RadiationDoseAbsorbed extends AbstractQuantityType<RadiationDoseAbsorbed> {

    public static final RadiationDoseAbsorbed RADIATION_DOSE_ABSORBED = new RadiationDoseAbsorbed();

    private RadiationDoseAbsorbed() {
        super(LENGTH.pow(2).divide(TIME.pow(2)), () -> GRAY);
    }
}

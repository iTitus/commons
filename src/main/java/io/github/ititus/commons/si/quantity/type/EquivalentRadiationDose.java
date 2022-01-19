package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.commons.si.dimension.BaseDimension.TIME;
import static io.github.ititus.commons.si.unit.Units.SIEVERT;

public final class EquivalentRadiationDose extends AbstractQuantityType<EquivalentRadiationDose> {

    public static final EquivalentRadiationDose EQUIVALENT_RADIATION_DOSE = new EquivalentRadiationDose();

    private EquivalentRadiationDose() {
        super(LENGTH.pow(2).divide(TIME.pow(2)), () -> SIEVERT);
    }
}

package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.unit.Unit;

public final class ThermodynamicTemperature extends AbstractQuantityType<ThermodynamicTemperature> {

    public static final ThermodynamicTemperature THERMODYNAMIC_TEMPERATURE = new ThermodynamicTemperature();

    public static final Unit<ThermodynamicTemperature> KELVIN = null; //new BaseUnit<>(BaseDimension
    // .THERMODYNAMIC_TEMPERATURE);

    private ThermodynamicTemperature() {
        super(BaseDimension.THERMODYNAMIC_TEMPERATURE, () -> KELVIN);
    }
}

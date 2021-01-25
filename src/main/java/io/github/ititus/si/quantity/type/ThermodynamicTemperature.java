package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;

import static io.github.ititus.si.unit.Units.KELVIN;

public final class ThermodynamicTemperature extends AbstractQuantityType<ThermodynamicTemperature> {

    public static final ThermodynamicTemperature THERMODYNAMIC_TEMPERATURE = new ThermodynamicTemperature();

    private ThermodynamicTemperature() {
        super(BaseDimension.THERMODYNAMIC_TEMPERATURE, () -> KELVIN);
    }
}

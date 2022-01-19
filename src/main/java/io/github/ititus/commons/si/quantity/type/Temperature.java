package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.BaseDimension;

import static io.github.ititus.commons.si.unit.Units.KELVIN;

public final class Temperature extends AbstractQuantityType<Temperature> {

    public static final Temperature TEMPERATURE = new Temperature();

    private Temperature() {
        super(BaseDimension.TEMPERATURE, () -> KELVIN);
    }
}

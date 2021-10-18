package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;

import static io.github.ititus.si.unit.Units.KELVIN;

public final class Temperature extends AbstractQuantityType<Temperature> {

    public static final Temperature TEMPERATURE = new Temperature();

    private Temperature() {
        super(BaseDimension.TEMPERATURE, () -> KELVIN);
    }
}

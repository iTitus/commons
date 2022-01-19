package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.BaseDimension;

import static io.github.ititus.commons.si.unit.Units.NEWTON_METER;

public final class Torque extends AbstractQuantityType<Torque> {

    public static final Torque TORQUE = new Torque();

    private Torque() {
        super(BaseDimension.MASS.multiply(BaseDimension.LENGTH.pow(2)).divide(BaseDimension.TIME.pow(2)), () -> NEWTON_METER);
    }
}

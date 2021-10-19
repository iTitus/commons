package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.*;
import static io.github.ititus.si.unit.Units.NEWTON_METER;

public final class Torque extends AbstractQuantityType<Torque> {

    public static final Torque TORQUE = new Torque();

    private Torque() {
        super(MASS.multiply(LENGTH.pow(2)).divide(TIME.pow(2)), () -> NEWTON_METER);
    }
}

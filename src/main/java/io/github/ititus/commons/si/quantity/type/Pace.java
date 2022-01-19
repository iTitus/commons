package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.quantity.Quantity;

import static io.github.ititus.commons.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.commons.si.dimension.BaseDimension.TIME;
import static io.github.ititus.commons.si.unit.Units.SECONDS_PER_METRE;

public final class Pace extends AbstractQuantityType<Pace> {

    public static final Pace PACE = new Pace();

    private Pace() {
        super(TIME.divide(LENGTH), () -> SECONDS_PER_METRE);
    }

    public static Quantity<Pace> asPace(Quantity<Speed> qty) {
        return qty.inverse().as(PACE);
    }
}

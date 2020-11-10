package io.github.ititus.si.quantity.type;

import io.github.ititus.si.quantity.Quantity;

import static io.github.ititus.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.si.dimension.BaseDimension.TIME;
import static io.github.ititus.si.unit.Units.SECONDS_PER_METRE;

public final class Pace extends AbstractQuantityType<Pace> {

    public static final Pace PACE = new Pace();

    public static Quantity<Pace> asPace(Quantity<Speed> qty) {
        return qty.inverse().as(PACE);
    }

    private Pace() {
        super(TIME.divide(LENGTH), () -> SECONDS_PER_METRE);
    }
}

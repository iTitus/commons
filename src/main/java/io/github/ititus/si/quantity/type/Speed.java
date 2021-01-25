package io.github.ititus.si.quantity.type;

import io.github.ititus.si.quantity.Quantity;

import static io.github.ititus.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.si.dimension.BaseDimension.TIME;
import static io.github.ititus.si.unit.Units.METRES_PER_SECOND;

public final class Speed extends AbstractQuantityType<Speed> {

    public static final Speed SPEED = new Speed();

    private Speed() {
        super(LENGTH.divide(TIME), () -> METRES_PER_SECOND);
    }

    public static Quantity<Speed> asSpeed(Quantity<Pace> qty) {
        return qty.inverse().as(SPEED);
    }
}

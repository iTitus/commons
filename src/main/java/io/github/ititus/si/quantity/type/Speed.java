package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.unit.Unit;

public final class Speed extends AbstractQuantityType<Speed> {

    public static final Speed SPEED = new Speed();

    public static final Unit<Speed> METRES_PER_SECOND = Length.METRE.divide(Time.SECOND).as(SPEED);
    public static final Unit<Speed> KILOMETRES_PER_HOUR = Length.KILOMETRE.divide(Time.HOUR).as(SPEED);
    public static final Unit<Speed> MILES_PER_HOUR = Length.MILE.divide(Time.HOUR).as(SPEED);
    public static final Unit<Speed> KNOT = Length.NAUTICAL_MILE.divide(Time.HOUR).as(SPEED);

    private Speed() {
        super(BaseDimension.LENGTH.divide(BaseDimension.TIME), () -> METRES_PER_SECOND);
    }
}

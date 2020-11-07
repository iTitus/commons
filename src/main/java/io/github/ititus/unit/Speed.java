package io.github.ititus.unit;

public class Speed extends AbstractQuantityType<Speed> {

    public static final Speed SPEED = new Speed();

    public static final Unit<Speed> METRES_PER_SECOND = Length.METER.divide(Time.SECOND).as(SPEED);
    public static final Unit<Speed> KILOMETRES_PER_HOUR = Length.KILOMETER.divide(Time.HOUR).as(SPEED);
    public static final Unit<Speed> MILES_PER_HOUR = Length.MILE.divide(Time.HOUR).as(SPEED);
    public static final Unit<Speed> KNOT = Length.NAUTICAL_MILE.divide(Time.HOUR).as(SPEED);

    Speed() {
        super(BaseDimension.LENGTH.divide(BaseDimension.TIME));
    }
}

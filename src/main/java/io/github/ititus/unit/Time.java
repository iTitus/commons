package io.github.ititus.unit;

public class Time extends AbstractQuantityType<Time> {

    public static final Time TIME = new Time();

    public static final Unit<Time> SECOND = new BaseUnit<>(BaseDimension.TIME);
    public static final Unit<Time> MINUTE = SECOND.multiply(60);
    public static final Unit<Time> HOUR = MINUTE.multiply(60);

    Time() {
        super(BaseDimension.TIME);
    }
}

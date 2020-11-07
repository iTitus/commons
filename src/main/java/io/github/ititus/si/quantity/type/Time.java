package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.unit.Unit;

public final class Time extends AbstractQuantityType<Time> {

    public static final Time TIME = new Time();

    public static final Unit<Time> SECOND = null; //new BaseUnit<>(BaseDimension.TIME);
    public static final Unit<Time> MINUTE = SECOND.multiply(60);
    public static final Unit<Time> HOUR = MINUTE.multiply(60);

    private Time() {
        super(BaseDimension.TIME, () -> SECOND);
    }
}

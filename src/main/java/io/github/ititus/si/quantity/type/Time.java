package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;

import static io.github.ititus.si.unit.Units.SECOND;

public final class Time extends AbstractQuantityType<Time> {

    public static final Time TIME = new Time();

    private Time() {
        super(BaseDimension.TIME, () -> SECOND);
    }
}

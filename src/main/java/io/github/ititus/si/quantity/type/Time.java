package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.quantity.Quantity;

import static io.github.ititus.si.unit.Units.SECOND;

public final class Time extends AbstractQuantityType<Time> {

    public static final Time TIME = new Time();

    public static Quantity<Time> asFrequency(Quantity<Frequency> qty) {
        return qty.inverse().as(TIME);
    }

    private Time() {
        super(BaseDimension.TIME, () -> SECOND);
    }
}

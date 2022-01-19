package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.BaseDimension;
import io.github.ititus.commons.si.quantity.Quantity;

import java.time.Duration;

import static io.github.ititus.commons.si.unit.Units.NANOSECOND;
import static io.github.ititus.commons.si.unit.Units.SECOND;

public final class Time extends AbstractQuantityType<Time> {

    public static final Time TIME = new Time();

    private Time() {
        super(BaseDimension.TIME, () -> SECOND);
    }

    public static Quantity<Time> asFrequency(Quantity<Frequency> qty) {
        return qty.inverse().as(TIME);
    }

    public static Duration asDuration(Quantity<Time> qty) {
        long seconds = qty.convertTo(SECOND).getValue().longValue();
        long nanoAdjustment = qty.subtract(SECOND.get(seconds)).convertTo(NANOSECOND).getValue().longValue();

        return Duration.ofSeconds(seconds, nanoAdjustment);
    }

    public static Quantity<Time> of(Duration d) {
        return SECOND.get(d.getSeconds()).add(NANOSECOND.get(d.getNano())).convertToStandard();
    }
}

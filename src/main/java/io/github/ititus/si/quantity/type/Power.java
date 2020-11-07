package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.prefix.MetricPrefix;
import io.github.ititus.si.unit.Unit;

public final class Power extends AbstractQuantityType<Power> {

    public static final Power POWER = new Power();

    public static final Unit<Power> WATT =
            Energy.JOULE.divide(Time.SECOND).alternate("W").as(POWER);
    public static final Unit<Power> KILOWATT = WATT.prefix(MetricPrefix.KILO);

    private Power() {
        super(BaseDimension.MASS.multiply(BaseDimension.LENGTH.pow(2).divide(BaseDimension.TIME.pow(3))), () -> WATT);
    }
}

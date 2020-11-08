package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.prefix.MetricPrefix;
import io.github.ititus.si.unit.BaseUnit;
import io.github.ititus.si.unit.Unit;

public final class Length extends AbstractQuantityType<Length> {

    public static final Length LENGTH = new Length();

    public static final Unit<Length> METRE = new BaseUnit<>(LENGTH, "m");
    public static final Unit<Length> KILOMETRE = METRE.prefix(MetricPrefix.KILO);
    public static final Unit<Length> MILE = METRE.multiply(1_609.344).alternate("mi");
    public static final Unit<Length> NAUTICAL_MILE = METRE.multiply(1_852).alternate("nmi");

    private Length() {
        super(BaseDimension.LENGTH, () -> METRE);
    }
}

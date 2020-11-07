package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.prefix.MetricPrefix;
import io.github.ititus.si.unit.Unit;

public final class Mass extends AbstractQuantityType<Mass> {

    public static final Mass MASS = new Mass();

    public static final Unit<Mass> GRAM = null; //new BaseUnit<>(BaseDimension.MASS);
    public static final Unit<Mass> KILOGRAM = GRAM.prefix(MetricPrefix.KILO);

    private Mass() {
        super(BaseDimension.MASS, () -> KILOGRAM);
    }
}

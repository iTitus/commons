package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.unit.Unit;

public final class Energy extends AbstractQuantityType<Energy> {

    public static final Energy ENERGY = new Energy();

    public static final Unit<Energy> JOULE =
            Force.NEWTON.multiply(Length.METRE).alternate("J").as(ENERGY);
    public static final Unit<Energy> KILOWATT_HOUR = Power.KILOWATT.multiply(Time.HOUR).as(ENERGY);

    private Energy() {
        super(BaseDimension.MASS.multiply(BaseDimension.LENGTH.pow(2).divide(BaseDimension.TIME.pow(2))), () -> JOULE);
    }
}

package io.github.ititus.unit;

public class Length extends AbstractQuantityType<Length> {

    public static final Length LENGTH = new Length();

    public static final Unit<Length> METER = new BaseUnit<>(BaseDimension.LENGTH);
    public static final Unit<Length> KILOMETER = METER.multiply(1_000);
    public static final Unit<Length> MILE = METER.multiply(1_609.344);
    public static final Unit<Length> NAUTICAL_MILE = METER.multiply(1_852);

    private Length() {
        super(BaseDimension.LENGTH);
    }
}

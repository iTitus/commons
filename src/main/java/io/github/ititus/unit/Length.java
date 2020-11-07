package io.github.ititus.unit;

public class Length extends AbstractQuantityType<Length> {

    public static final Length LENGTH = new Length();

    public static final Unit<Length> METRE = new BaseUnit<>(BaseDimension.LENGTH);
    public static final Unit<Length> KILOMETRE = METRE.multiply(1_000);
    public static final Unit<Length> MILE = METRE.multiply(1_609.344);
    public static final Unit<Length> NAUTICAL_MILE = METRE.multiply(1_852);

    private Length() {
        super(BaseDimension.LENGTH);
    }
}

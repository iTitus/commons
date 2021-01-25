package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;

import static io.github.ititus.si.unit.Units.METRE;


public final class Length extends AbstractQuantityType<Length> {

    public static final Length LENGTH = new Length();

    private Length() {
        super(BaseDimension.LENGTH, () -> METRE);
    }
}

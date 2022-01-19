package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.BaseDimension;

import static io.github.ititus.commons.si.unit.Units.METRE;


public final class Length extends AbstractQuantityType<Length> {

    public static final Length LENGTH = new Length();

    private Length() {
        super(BaseDimension.LENGTH, () -> METRE);
    }
}

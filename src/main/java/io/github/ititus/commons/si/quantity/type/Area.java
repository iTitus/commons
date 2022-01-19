package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.commons.si.unit.Units.SQUARE_METRE;

public final class Area extends AbstractQuantityType<Area> {

    public static final Area AREA = new Area();

    private Area() {
        super(LENGTH.pow(2), () -> SQUARE_METRE);
    }
}

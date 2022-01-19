package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.commons.si.unit.Units.CUBIC_METRE;

public final class Volume extends AbstractQuantityType<Volume> {

    public static final Volume VOLUME = new Volume();

    private Volume() {
        super(LENGTH.pow(3), () -> CUBIC_METRE);
    }
}

package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.LENGTH;
import static io.github.ititus.si.dimension.BaseDimension.MASS;
import static io.github.ititus.si.unit.Units.KILOGRAM_PER_CUBIC_METRE;

public final class Density extends AbstractQuantityType<Density> {

    public static final Density DENSITY = new Density();

    private Density() {
        super(MASS.divide(LENGTH.pow(3)), () -> KILOGRAM_PER_CUBIC_METRE);
    }
}

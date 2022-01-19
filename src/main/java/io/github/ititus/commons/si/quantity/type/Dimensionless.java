package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.Dimension;

import static io.github.ititus.commons.si.unit.Units.ONE;

public final class Dimensionless extends AbstractQuantityType<Dimensionless> {

    public static final Dimensionless DIMENSIONLESS = new Dimensionless();

    private Dimensionless() {
        super(Dimension.NONE, () -> ONE);
    }
}

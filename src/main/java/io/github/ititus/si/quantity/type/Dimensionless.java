package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.Dimension.NONE;
import static io.github.ititus.si.unit.Units.ONE;

public final class Dimensionless extends AbstractQuantityType<Dimensionless> {

    public static final Dimensionless DIMENSIONLESS = new Dimensionless();

    private Dimensionless() {
        super(NONE, () -> ONE);
    }
}

package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.Dimension;
import io.github.ititus.si.unit.Unit;

public final class Dimensionless extends AbstractQuantityType<Dimensionless> {

    public static final Dimensionless DIMENSIONLESS = new Dimensionless();

    public static final Unit<Dimensionless> ONE = null; // new BaseUnit<>(Dimension.NONE);

    private Dimensionless() {
        super(Dimension.NONE, () -> ONE);
    }
}

package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.*;
import static io.github.ititus.si.unit.Units.JOULE;

public final class Energy extends AbstractQuantityType<Energy> {

    public static final Energy ENERGY = new Energy();

    private Energy() {
        super(MASS.multiply(LENGTH.pow(2).divide(TIME.pow(2))), () -> JOULE);
    }
}

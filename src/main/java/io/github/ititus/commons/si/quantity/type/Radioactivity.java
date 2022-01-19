package io.github.ititus.commons.si.quantity.type;

import static io.github.ititus.commons.si.dimension.BaseDimension.TIME;
import static io.github.ititus.commons.si.unit.Units.BECQUEREL;

public final class Radioactivity extends AbstractQuantityType<Radioactivity> {

    public static final Radioactivity RADIOACTIVITY = new Radioactivity();

    private Radioactivity() {
        super(TIME.inverse(), () -> BECQUEREL);
    }
}

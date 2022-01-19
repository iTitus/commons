package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.quantity.Quantity;

import static io.github.ititus.commons.si.dimension.BaseDimension.TIME;
import static io.github.ititus.commons.si.unit.Units.HERTZ;

public final class Frequency extends AbstractQuantityType<Frequency> {

    public static final Frequency FREQUENCY = new Frequency();

    private Frequency() {
        super(TIME.inverse(), () -> HERTZ);
    }

    public static Quantity<Frequency> asFrequency(Quantity<Time> qty) {
        return qty.inverse().as(FREQUENCY);
    }
}

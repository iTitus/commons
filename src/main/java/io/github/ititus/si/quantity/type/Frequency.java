package io.github.ititus.si.quantity.type;

import io.github.ititus.si.quantity.Quantity;

import static io.github.ititus.si.dimension.BaseDimension.TIME;
import static io.github.ititus.si.unit.Units.HERTZ;

public final class Frequency extends AbstractQuantityType<Frequency> {

    public static final Frequency FREQUENCY = new Frequency();

    public static Quantity<Frequency> asFrequency(Quantity<Time> qty) {
        return qty.inverse().as(FREQUENCY);
    }

    private Frequency() {
        super(TIME.inverse(), () -> HERTZ);
    }
}

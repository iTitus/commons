package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;

import static io.github.ititus.si.unit.Units.KILOGRAM;

public final class Mass extends AbstractQuantityType<Mass> {

    public static final Mass MASS = new Mass();

    private Mass() {
        super(BaseDimension.MASS, () -> KILOGRAM);
    }
}

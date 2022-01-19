package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.BaseDimension;

import static io.github.ititus.commons.si.unit.Units.KILOGRAM;

public final class Mass extends AbstractQuantityType<Mass> {

    public static final Mass MASS = new Mass();

    private Mass() {
        super(BaseDimension.MASS, () -> KILOGRAM);
    }
}

package io.github.ititus.commons.si.quantity.type;

import io.github.ititus.commons.si.dimension.BaseDimension;

import static io.github.ititus.commons.si.unit.Units.MOLE;

public final class SubstanceAmount extends AbstractQuantityType<SubstanceAmount> {

    public static final SubstanceAmount SUBSTANCE_AMOUNT = new SubstanceAmount();

    private SubstanceAmount() {
        super(BaseDimension.SUBSTANCE_AMOUNT, () -> MOLE);
    }
}

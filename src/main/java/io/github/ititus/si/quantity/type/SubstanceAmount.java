package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;

import static io.github.ititus.si.unit.Units.MOLE;

public final class SubstanceAmount extends AbstractQuantityType<SubstanceAmount> {

    public static final SubstanceAmount SUBSTANCE_AMOUNT = new SubstanceAmount();

    private SubstanceAmount() {
        super(BaseDimension.SUBSTANCE_AMOUNT, () -> MOLE);
    }
}

package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.unit.BaseUnit;
import io.github.ititus.si.unit.Unit;

public final class SubstanceAmount extends AbstractQuantityType<SubstanceAmount> {

    public static final SubstanceAmount SUBSTANCE_AMOUNT = new SubstanceAmount();

    public static final Unit<SubstanceAmount> MOLE = new BaseUnit<>(SUBSTANCE_AMOUNT, "mol");

    private SubstanceAmount() {
        super(BaseDimension.SUBSTANCE_AMOUNT, () -> MOLE);
    }
}

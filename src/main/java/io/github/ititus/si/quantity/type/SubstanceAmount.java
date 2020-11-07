package io.github.ititus.si.quantity.type;

import io.github.ititus.si.dimension.BaseDimension;
import io.github.ititus.si.unit.Unit;

public final class SubstanceAmount extends AbstractQuantityType<SubstanceAmount> {

    public static final SubstanceAmount SUBSTANCE_AMOUNT = new SubstanceAmount();

    public static final Unit<SubstanceAmount> MOLE = null; //new BaseUnit<>(BaseDimension.SUBSTANCE_AMOUNT);

    private SubstanceAmount() {
        super(BaseDimension.SUBSTANCE_AMOUNT, () -> MOLE);
    }
}

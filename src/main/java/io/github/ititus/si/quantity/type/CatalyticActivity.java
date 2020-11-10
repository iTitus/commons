package io.github.ititus.si.quantity.type;

import static io.github.ititus.si.dimension.BaseDimension.SUBSTANCE_AMOUNT;
import static io.github.ititus.si.dimension.BaseDimension.TIME;
import static io.github.ititus.si.unit.Units.KATAL;

public final class CatalyticActivity extends AbstractQuantityType<CatalyticActivity> {

    public static final CatalyticActivity CATALYTIC_ACTIVITY = new CatalyticActivity();

    private CatalyticActivity() {
        super(SUBSTANCE_AMOUNT.divide(TIME), () -> KATAL);
    }
}

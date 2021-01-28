package io.github.ititus.math.number;

import java.math.BigDecimal;

import static io.github.ititus.math.number.BigDecimalMath.inverse;
import static io.github.ititus.math.number.BigDecimalMath.of;

public final class BigDecimalConstants {

    public static final BigDecimal ZERO = BigDecimal.ZERO;

    public static final BigDecimal ONE = BigDecimal.ONE;
    public static final BigDecimal TWO = of(2);
    public static final BigDecimal THREE = of(3);
    public static final BigDecimal FOUR = of(4);
    public static final BigDecimal FIVE = of(5);
    public static final BigDecimal SIX = of(6);
    public static final BigDecimal SEVEN = of(7);
    public static final BigDecimal EIGHT = of(8);
    public static final BigDecimal NINE = of(9);
    public static final BigDecimal TEN = BigDecimal.TEN;

    public static final BigDecimal MINUS_ONE = ONE.negate();
    public static final BigDecimal MINUS_TWO = TWO.negate();
    public static final BigDecimal MINUS_THREE = THREE.negate();
    public static final BigDecimal MINUS_FOUR = FOUR.negate();
    public static final BigDecimal MINUS_FIVE = FIVE.negate();
    public static final BigDecimal MINUS_SIX = SIX.negate();
    public static final BigDecimal MINUS_SEVEN = SEVEN.negate();
    public static final BigDecimal MINUS_EIGHT = EIGHT.negate();
    public static final BigDecimal MINUS_NINE = NINE.negate();
    public static final BigDecimal MINUS_TEN = TEN.negate();

    public static final BigDecimal ONE_OVER_TWO = inverse(TWO);
    public static final BigDecimal ONE_OVER_FOUR = inverse(FOUR);
    public static final BigDecimal ONE_OVER_FIVE = inverse(FIVE);
    public static final BigDecimal ONE_OVER_EIGHT = inverse(EIGHT);
    public static final BigDecimal ONE_OVER_TEN = inverse(TEN);

    public static final BigDecimal MINUS_ONE_OVER_TWO = ONE_OVER_TWO.negate();
    public static final BigDecimal MINUS_ONE_OVER_FOUR = ONE_OVER_FOUR.negate();
    public static final BigDecimal MINUS_ONE_OVER_FIVE = ONE_OVER_FIVE.negate();
    public static final BigDecimal MINUS_ONE_OVER_EIGHT = ONE_OVER_EIGHT.negate();
    public static final BigDecimal MINUS_ONE_OVER_TEN = ONE_OVER_TEN.negate();

    private BigDecimalConstants() {
    }
}

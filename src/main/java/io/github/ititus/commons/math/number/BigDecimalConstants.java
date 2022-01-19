package io.github.ititus.commons.math.number;

import java.math.BigDecimal;

public final class BigDecimalConstants {

    public static final BigDecimal ZERO = BigDecimal.ZERO;

    public static final BigDecimal ONE = BigDecimal.ONE;
    public static final BigDecimal TWO = BigDecimalMath.of(2);
    public static final BigDecimal THREE = BigDecimalMath.of(3);
    public static final BigDecimal FOUR = BigDecimalMath.of(4);
    public static final BigDecimal FIVE = BigDecimalMath.of(5);
    public static final BigDecimal SIX = BigDecimalMath.of(6);
    public static final BigDecimal SEVEN = BigDecimalMath.of(7);
    public static final BigDecimal EIGHT = BigDecimalMath.of(8);
    public static final BigDecimal NINE = BigDecimalMath.of(9);
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

    public static final BigDecimal ONE_OVER_TWO = BigDecimalMath.inverse(TWO);
    public static final BigDecimal MINUS_ONE_OVER_TWO = ONE_OVER_TWO.negate();
    public static final BigDecimal ONE_OVER_FOUR = BigDecimalMath.inverse(FOUR);
    public static final BigDecimal MINUS_ONE_OVER_FOUR = ONE_OVER_FOUR.negate();
    public static final BigDecimal ONE_OVER_FIVE = BigDecimalMath.inverse(FIVE);
    public static final BigDecimal MINUS_ONE_OVER_FIVE = ONE_OVER_FIVE.negate();
    public static final BigDecimal ONE_OVER_EIGHT = BigDecimalMath.inverse(EIGHT);
    public static final BigDecimal MINUS_ONE_OVER_EIGHT = ONE_OVER_EIGHT.negate();
    public static final BigDecimal ONE_OVER_TEN = BigDecimalMath.inverse(TEN);
    public static final BigDecimal MINUS_ONE_OVER_TEN = ONE_OVER_TEN.negate();

    private BigDecimalConstants() {
    }
}

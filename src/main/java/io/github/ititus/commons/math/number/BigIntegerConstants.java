package io.github.ititus.commons.math.number;

import java.math.BigInteger;

public final class BigIntegerConstants {

    public static final BigInteger ZERO = BigInteger.ZERO;
    public static final BigInteger ONE = BigInteger.ONE;
    public static final BigInteger TWO = BigInteger.TWO;
    public static final BigInteger THREE = BigIntegerMath.of(3);
    public static final BigInteger FOUR = BigIntegerMath.of(4);
    public static final BigInteger FIVE = BigIntegerMath.of(5);
    public static final BigInteger SIX = BigIntegerMath.of(6);
    public static final BigInteger SEVEN = BigIntegerMath.of(7);
    public static final BigInteger EIGHT = BigIntegerMath.of(8);
    public static final BigInteger NINE = BigIntegerMath.of(9);
    public static final BigInteger TEN = BigInteger.TEN;

    public static final BigInteger MINUS_ONE = ONE.negate();
    public static final BigInteger MINUS_TWO = TWO.negate();
    public static final BigInteger MINUS_THREE = THREE.negate();
    public static final BigInteger MINUS_FOUR = FOUR.negate();
    public static final BigInteger MINUS_FIVE = FIVE.negate();
    public static final BigInteger MINUS_SIX = SIX.negate();
    public static final BigInteger MINUS_SEVEN = SEVEN.negate();
    public static final BigInteger MINUS_EIGHT = EIGHT.negate();
    public static final BigInteger MINUS_NINE = NINE.negate();
    public static final BigInteger MINUS_TEN = TEN.negate();

    public static final BigInteger LONG_MAX_VALUE = BigIntegerMath.of(Long.MAX_VALUE);
    public static final BigInteger MINUS_LONG_MIN_VALUE = ONE.shiftLeft(Long.SIZE - 1);
    public static final BigInteger UNSIGNED_LONG_MAX_VALUE = ONE.shiftLeft(Long.SIZE).subtract(ONE);
    public static final BigInteger MINUS_UNSIGNED_LONG_MIN_VALUE = ONE.shiftLeft(Long.SIZE);

    private BigIntegerConstants() {
    }
}

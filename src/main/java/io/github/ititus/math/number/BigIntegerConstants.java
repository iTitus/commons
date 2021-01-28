package io.github.ititus.math.number;

import java.math.BigInteger;

import static io.github.ititus.math.number.BigIntegerMath.of;

public final class BigIntegerConstants {

    public static final BigInteger ZERO = BigInteger.ZERO;
    public static final BigInteger ONE = BigInteger.ONE;
    public static final BigInteger TWO = BigInteger.TWO;
    public static final BigInteger THREE = of(3);
    public static final BigInteger FOUR = of(4);
    public static final BigInteger FIVE = of(5);
    public static final BigInteger SIX = of(6);
    public static final BigInteger SEVEN = of(7);
    public static final BigInteger EIGHT = of(8);
    public static final BigInteger NINE = of(9);
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

    public static final BigInteger MINUS_LONG_MIN_VALUE = ONE.shiftLeft(63);

    private BigIntegerConstants() {
    }
}

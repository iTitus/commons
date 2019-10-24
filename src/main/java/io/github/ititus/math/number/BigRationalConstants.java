package io.github.ititus.math.number;

import java.math.BigInteger;

import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRationalMath.exp;

public final class BigRationalConstants {

    public static final BigRational ZERO = new BigRational(BigInteger.ZERO, BigInteger.ONE);
    public static final BigRational ONE = new BigRational(BigInteger.ONE, BigInteger.ONE);
    public static final BigRational TWO = new BigRational(BigInteger.TWO, BigInteger.ONE);
    public static final BigRational THREE = new BigRational(BigIntegerMath.THREE, BigInteger.ONE);
    public static final BigRational FOUR = new BigRational(BigIntegerMath.FOUR, BigInteger.ONE);
    public static final BigRational TEN = new BigRational(BigInteger.TEN, BigInteger.ONE);
    public static final BigRational ONE_OVER_TWO = new BigRational(BigInteger.ONE, BigInteger.TWO);
    public static final BigRational ONE_OVER_THREE = new BigRational(BigInteger.ONE, BigIntegerMath.THREE);
    public static final BigRational THREE_OVER_FOUR = new BigRational(BigIntegerMath.THREE, BigIntegerMath.FOUR);
    public static final BigRational FIVE_OVER_FOUR = new BigRational(BigIntegerMath.FIVE, BigIntegerMath.FOUR);
    public static final BigRational MINUS_ONE = new BigRational(BigIntegerMath.MINUS_ONE, BigInteger.ONE);
    public static final BigRational MINUS_FOUR_OVER_FIVE = new BigRational(BigIntegerMath.FOUR.negate(), BigIntegerMath.FIVE);
    public static final BigRational PI = of("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
    public static final BigRational TWO_PI = PI.multiply(TWO);
    public static final BigRational PI_OVER_TWO = PI.divide(TWO);
    public static final BigRational E = exp(ONE);

    private BigRationalConstants() {
    }
}

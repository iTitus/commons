package io.github.ititus.math.number;

import static io.github.ititus.math.number.BigRational.of;

public final class BigRationalConstants {

    public static final BigRational ZERO = new BigRational(BigIntegerConstants.ZERO, BigIntegerConstants.ONE);
    public static final BigRational ONE = new BigRational(BigIntegerConstants.ONE, BigIntegerConstants.ONE);
    public static final BigRational MINUS_ONE = new BigRational(BigIntegerConstants.MINUS_ONE, BigIntegerConstants.ONE);

    public static final BigRational TWO = of(2);
    public static final BigRational MINUS_TWO = TWO.negate();
    public static final BigRational ONE_OVER_TWO = TWO.inverse();
    public static final BigRational MINUS_ONE_OVER_TWO = ONE_OVER_TWO.negate();
    public static final BigRational THREE = of(3);
    public static final BigRational MINUS_THREE = THREE.negate();
    public static final BigRational ONE_OVER_THREE = THREE.inverse();
    public static final BigRational MINUS_ONE_OVER_THREE = ONE_OVER_THREE.negate();
    public static final BigRational FOUR = of(4);
    public static final BigRational MINUS_FOUR = FOUR.negate();
    public static final BigRational ONE_OVER_FOUR = FOUR.inverse();
    public static final BigRational MINUS_ONE_OVER_FOUR = ONE_OVER_FOUR.negate();
    public static final BigRational FIVE = of(5);
    public static final BigRational MINUS_FIVE = FIVE.negate();
    public static final BigRational ONE_OVER_FIVE = FIVE.inverse();
    public static final BigRational MINUS_ONE_OVER_FIVE = ONE_OVER_FIVE.negate();
    public static final BigRational SIX = of(6);
    public static final BigRational MINUS_SIX = SIX.negate();
    public static final BigRational ONE_OVER_SIX = SIX.inverse();
    public static final BigRational MINUS_ONE_OVER_SIX = ONE_OVER_SIX.negate();
    public static final BigRational SEVEN = of(7);
    public static final BigRational MINUS_SEVEN = SEVEN.negate();
    public static final BigRational ONE_OVER_SEVEN = SEVEN.inverse();
    public static final BigRational MINUS_ONE_OVER_SEVEN = ONE_OVER_SEVEN.negate();
    public static final BigRational EIGHT = of(8);
    public static final BigRational MINUS_EIGHT = EIGHT.negate();
    public static final BigRational ONE_OVER_EIGHT = EIGHT.inverse();
    public static final BigRational MINUS_ONE_OVER_EIGHT = ONE_OVER_EIGHT.negate();
    public static final BigRational NINE = of(9);
    public static final BigRational MINUS_NINE = NINE.negate();
    public static final BigRational ONE_OVER_NINE = NINE.inverse();
    public static final BigRational MINUS_ONE_OVER_NINE = ONE_OVER_NINE.negate();
    public static final BigRational TEN = of(10);
    public static final BigRational MINUS_TEN = TEN.negate();
    public static final BigRational ONE_OVER_TEN = TEN.inverse();
    public static final BigRational MINUS_ONE_OVER_TEN = ONE_OVER_TEN.negate();

    public static final BigRational PI = of(
            "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446"
    );
    public static final BigRational TWO_PI = PI.multiply(TWO);
    public static final BigRational MINUS_TWO_PI = TWO_PI.negate();
    public static final BigRational PI_OVER_TWO = PI.divide(TWO);
    public static final BigRational MINUS_PI_OVER_TWO = PI_OVER_TWO.negate();
    public static final BigRational MINUS_PI = PI.negate();
    public static final BigRational E = of(
            "2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174136"
    );

    public static final BigRational MINUS_E = E.negate();

    private BigRationalConstants() {
    }
}

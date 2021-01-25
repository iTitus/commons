package io.github.ititus.math.number;

import static io.github.ititus.math.number.BigComplex.real;

public final class BigComplexConstants {

    public static final BigComplex ZERO = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.ZERO);
    public static final BigComplex ONE = new BigComplex(BigRationalConstants.ONE, BigRationalConstants.ZERO);
    public static final BigComplex MINUS_ONE = new BigComplex(BigRationalConstants.MINUS_ONE,
            BigRationalConstants.ZERO);
    public static final BigComplex I = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.ONE);
    public static final BigComplex MINUS_I = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.MINUS_ONE);

    public static final BigComplex TWO = real(2);
    public static final BigComplex THREE = real(3);
    public static final BigComplex FOUR = real(4);
    public static final BigComplex FIVE = real(5);
    public static final BigComplex SIX = real(6);
    public static final BigComplex SEVEN = real(7);
    public static final BigComplex EIGHT = real(8);
    public static final BigComplex NINE = real(9);
    public static final BigComplex TEN = real(10);

    public static final BigComplex MINUS_TWO = TWO.negate();
    public static final BigComplex MINUS_THREE = THREE.negate();
    public static final BigComplex MINUS_FOUR = FOUR.negate();
    public static final BigComplex MINUS_FIVE = FIVE.negate();
    public static final BigComplex MINUS_SIX = SIX.negate();
    public static final BigComplex MINUS_SEVEN = SEVEN.negate();
    public static final BigComplex MINUS_EIGHT = EIGHT.negate();
    public static final BigComplex MINUS_NINE = NINE.negate();
    public static final BigComplex MINUS_TEN = TEN.negate();

    public static final BigComplex ONE_OVER_TWO = real(BigRationalConstants.ONE_OVER_TWO);
    public static final BigComplex ONE_OVER_THREE = real(BigRationalConstants.ONE_OVER_THREE);
    public static final BigComplex ONE_OVER_FOUR = real(BigRationalConstants.ONE_OVER_FOUR);
    public static final BigComplex ONE_OVER_FIVE = real(BigRationalConstants.ONE_OVER_FIVE);
    public static final BigComplex ONE_OVER_SIX = real(BigRationalConstants.ONE_OVER_SIX);
    public static final BigComplex ONE_OVER_SEVEN = real(BigRationalConstants.ONE_OVER_SEVEN);
    public static final BigComplex ONE_OVER_EIGHT = real(BigRationalConstants.ONE_OVER_EIGHT);
    public static final BigComplex ONE_OVER_NINE = real(BigRationalConstants.ONE_OVER_NINE);
    public static final BigComplex ONE_OVER_TEN = real(BigRationalConstants.ONE_OVER_TEN);

    public static final BigComplex MINUS_ONE_OVER_TWO = ONE_OVER_TWO.negate();
    public static final BigComplex MINUS_ONE_OVER_THREE = ONE_OVER_THREE.negate();
    public static final BigComplex MINUS_ONE_OVER_FOUR = ONE_OVER_FOUR.negate();
    public static final BigComplex MINUS_ONE_OVER_FIVE = ONE_OVER_FIVE.negate();
    public static final BigComplex MINUS_ONE_OVER_SIX = ONE_OVER_SIX.negate();
    public static final BigComplex MINUS_ONE_OVER_SEVEN = ONE_OVER_SEVEN.negate();
    public static final BigComplex MINUS_ONE_OVER_EIGHT = ONE_OVER_EIGHT.negate();
    public static final BigComplex MINUS_ONE_OVER_NINE = ONE_OVER_NINE.negate();
    public static final BigComplex MINUS_ONE_OVER_TEN = ONE_OVER_TEN.negate();

    public static final BigComplex PI = real(BigRationalConstants.PI);
    public static final BigComplex TWO_PI = PI.multiply(TWO);
    public static final BigComplex PI_OVER_TWO = PI.divide(TWO);

    public static final BigComplex E = real(BigRationalConstants.E);

    private BigComplexConstants() {
    }
}

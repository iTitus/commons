package io.github.ititus.math.number;

import static io.github.ititus.math.number.BigComplex.imag;
import static io.github.ititus.math.number.BigComplex.real;

public final class BigComplexConstants {

    public static final BigComplex ZERO = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.ZERO);
    public static final BigComplex ONE = new BigComplex(BigRationalConstants.ONE, BigRationalConstants.ZERO);
    public static final BigComplex MINUS_ONE = new BigComplex(BigRationalConstants.MINUS_ONE,
            BigRationalConstants.ZERO);
    public static final BigComplex I = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.ONE);
    public static final BigComplex MINUS_I = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.MINUS_ONE);

    public static final BigComplex TWO = real(2);
    public static final BigComplex MINUS_TWO = TWO.negate();
    public static final BigComplex THREE = real(3);
    public static final BigComplex MINUS_THREE = THREE.negate();
    public static final BigComplex FOUR = real(4);
    public static final BigComplex MINUS_FOUR = FOUR.negate();
    public static final BigComplex FIVE = real(5);
    public static final BigComplex MINUS_FIVE = FIVE.negate();
    public static final BigComplex SIX = real(6);
    public static final BigComplex MINUS_SIX = SIX.negate();
    public static final BigComplex SEVEN = real(7);
    public static final BigComplex MINUS_SEVEN = SEVEN.negate();
    public static final BigComplex EIGHT = real(8);
    public static final BigComplex MINUS_EIGHT = EIGHT.negate();
    public static final BigComplex NINE = real(9);
    public static final BigComplex MINUS_NINE = NINE.negate();
    public static final BigComplex TEN = real(10);
    public static final BigComplex MINUS_TEN = TEN.negate();

    public static final BigComplex TWO_I = imag(2);
    public static final BigComplex MINUS_TWO_I = TWO_I.negate();
    public static final BigComplex THREE_I = imag(3);
    public static final BigComplex MINUS_THREE_I = THREE_I.negate();
    public static final BigComplex FOUR_I = imag(4);
    public static final BigComplex MINUS_FOUR_I = FOUR_I.negate();
    public static final BigComplex FIVE_I = imag(5);
    public static final BigComplex MINUS_FIVE_I = FIVE_I.negate();
    public static final BigComplex SIX_I = imag(6);
    public static final BigComplex MINUS_SIX_I = SIX_I.negate();
    public static final BigComplex SEVEN_I = imag(7);
    public static final BigComplex MINUS_SEVEN_I = SEVEN_I.negate();
    public static final BigComplex EIGHT_I = imag(8);
    public static final BigComplex MINUS_EIGHT_I = EIGHT_I.negate();
    public static final BigComplex NINE_I = imag(9);
    public static final BigComplex MINUS_NINE_I = NINE_I.negate();
    public static final BigComplex TEN_I = imag(10);
    public static final BigComplex MINUS_TEN_I = TEN_I.negate();

    public static final BigComplex ONE_OVER_TWO = real(BigRationalConstants.ONE_OVER_TWO);
    public static final BigComplex MINUS_ONE_OVER_TWO = ONE_OVER_TWO.negate();
    public static final BigComplex ONE_OVER_THREE = real(BigRationalConstants.ONE_OVER_THREE);
    public static final BigComplex MINUS_ONE_OVER_THREE = ONE_OVER_THREE.negate();
    public static final BigComplex ONE_OVER_FOUR = real(BigRationalConstants.ONE_OVER_FOUR);
    public static final BigComplex MINUS_ONE_OVER_FOUR = ONE_OVER_FOUR.negate();
    public static final BigComplex ONE_OVER_FIVE = real(BigRationalConstants.ONE_OVER_FIVE);
    public static final BigComplex MINUS_ONE_OVER_FIVE = ONE_OVER_FIVE.negate();
    public static final BigComplex ONE_OVER_SIX = real(BigRationalConstants.ONE_OVER_SIX);
    public static final BigComplex MINUS_ONE_OVER_SIX = ONE_OVER_SIX.negate();
    public static final BigComplex ONE_OVER_SEVEN = real(BigRationalConstants.ONE_OVER_SEVEN);
    public static final BigComplex MINUS_ONE_OVER_SEVEN = ONE_OVER_SEVEN.negate();
    public static final BigComplex ONE_OVER_EIGHT = real(BigRationalConstants.ONE_OVER_EIGHT);
    public static final BigComplex MINUS_ONE_OVER_EIGHT = ONE_OVER_EIGHT.negate();
    public static final BigComplex ONE_OVER_NINE = real(BigRationalConstants.ONE_OVER_NINE);
    public static final BigComplex MINUS_ONE_OVER_NINE = ONE_OVER_NINE.negate();
    public static final BigComplex ONE_OVER_TEN = real(BigRationalConstants.ONE_OVER_TEN);
    public static final BigComplex MINUS_ONE_OVER_TEN = ONE_OVER_TEN.negate();

    public static final BigComplex ONE_OVER_TWO_I = imag(BigRationalConstants.ONE_OVER_TWO);
    public static final BigComplex MINUS_ONE_OVER_TWO_I = ONE_OVER_TWO_I.negate();
    public static final BigComplex ONE_OVER_THREE_I = imag(BigRationalConstants.ONE_OVER_THREE);
    public static final BigComplex MINUS_ONE_OVER_THREE_I = ONE_OVER_THREE_I.negate();
    public static final BigComplex ONE_OVER_FOUR_I = imag(BigRationalConstants.ONE_OVER_FOUR);
    public static final BigComplex MINUS_ONE_OVER_FOUR_I = ONE_OVER_FOUR_I.negate();
    public static final BigComplex ONE_OVER_FIVE_I = imag(BigRationalConstants.ONE_OVER_FIVE);
    public static final BigComplex MINUS_ONE_OVER_FIVE_I = ONE_OVER_FIVE_I.negate();
    public static final BigComplex ONE_OVER_SIX_I = imag(BigRationalConstants.ONE_OVER_SIX);
    public static final BigComplex MINUS_ONE_OVER_SIX_I = ONE_OVER_SIX_I.negate();
    public static final BigComplex ONE_OVER_SEVEN_I = imag(BigRationalConstants.ONE_OVER_SEVEN);
    public static final BigComplex MINUS_ONE_OVER_SEVEN_I = ONE_OVER_SEVEN_I.negate();
    public static final BigComplex ONE_OVER_EIGHT_I = imag(BigRationalConstants.ONE_OVER_EIGHT);
    public static final BigComplex MINUS_ONE_OVER_EIGHT_I = ONE_OVER_EIGHT_I.negate();
    public static final BigComplex ONE_OVER_NINE_I = imag(BigRationalConstants.ONE_OVER_NINE);
    public static final BigComplex MINUS_ONE_OVER_NINE_I = ONE_OVER_NINE_I.negate();
    public static final BigComplex ONE_OVER_TEN_I = imag(BigRationalConstants.ONE_OVER_TEN);
    public static final BigComplex MINUS_ONE_OVER_TEN_I = ONE_OVER_TEN_I.negate();

    public static final BigComplex PI = real(BigRationalConstants.PI);
    public static final BigComplex MINUS_PI = PI.negate();
    public static final BigComplex TWO_PI = real(BigRationalConstants.TWO_PI);
    public static final BigComplex MINUS_TWO_PI = TWO_PI.negate();
    public static final BigComplex PI_OVER_TWO = real(BigRationalConstants.PI_OVER_TWO);
    public static final BigComplex MINUS_PI_OVER_TWO = PI_OVER_TWO.negate();

    public static final BigComplex PI_I = imag(BigRationalConstants.PI);
    public static final BigComplex MINUS_PI_I = PI_I.negate();
    public static final BigComplex TWO_PI_I = imag(BigRationalConstants.TWO_PI);
    public static final BigComplex MINUS_TWO_PI_I = TWO_PI_I.negate();
    public static final BigComplex PI_OVER_TWO_I = imag(BigRationalConstants.PI_OVER_TWO);
    public static final BigComplex MINUS_PI_OVER_TWO_I = PI_OVER_TWO_I.negate();

    public static final BigComplex E = real(BigRationalConstants.E);

    public static final BigComplex MINUS_E = E.negate();

    public static final BigComplex E_I = imag(BigRationalConstants.E);

    public static final BigComplex MINUS_E_I = E_I.negate();

    private BigComplexConstants() {
    }
}

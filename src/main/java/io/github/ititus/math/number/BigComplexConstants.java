package io.github.ititus.math.number;

import static io.github.ititus.math.number.BigComplex.real;

public final class BigComplexConstants {

    public static final BigComplex ZERO = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.ZERO);
    public static final BigComplex ONE = new BigComplex(BigRationalConstants.ONE, BigRationalConstants.ZERO);
    public static final BigComplex MINUS_ONE = new BigComplex(BigRationalConstants.MINUS_ONE,
            BigRationalConstants.ZERO);
    public static final BigComplex I = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.ONE);
    public static final BigComplex MINUS_I = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.MINUS_ONE);

    public static final BigComplex TWO = real(BigRationalConstants.TWO);
    public static final BigComplex ONE_OVER_TWO = real(BigRationalConstants.ONE_OVER_TWO);

    public static final BigComplex PI = real(BigRationalConstants.PI);
    public static final BigComplex TWO_PI = PI.multiply(TWO);
    public static final BigComplex PI_OVER_TWO = PI.divide(TWO);

    public static final BigComplex E = real(BigRationalConstants.E);


    private BigComplexConstants() {
    }
}

package io.github.ititus.math.number;

import java.math.BigInteger;

import static io.github.ititus.math.number.BigComplex.*;
import static io.github.ititus.math.number.BigComplexConstants.*;

public final class BigComplexMath {

    private BigComplexMath() {
    }

    public static BigRational angle(BigComplex z) {
        return BigRationalMath.atan2(z.getImag(), z.getReal());
    }

    public static BigComplex sqrt(BigComplex z) {
        BigRational abs = z.abs();
        return of(abs.add(z.getReal()).divide(BigRationalConstants.TWO).sqrt(),
                abs.subtract(z.getReal()).divide(BigRationalConstants.TWO).sqrt());
    }

    @SuppressWarnings("Duplicates")
    public static BigComplex pow(BigComplex base, BigInteger exponent) {
        if (exponent.signum() < 0) {
            return pow(base, exponent.negate()).inverse();
        }

        BigComplex z = ONE;
        while (exponent.signum() > 0) {
            if (BigIntegerMath.isOdd(exponent)) {
                z = z.multiply(base);
                exponent = exponent.subtract(BigInteger.ONE);
            } else {
                base = base.squared();
                exponent = exponent.shiftRight(1);
            }
        }

        return z;
    }

    public static BigComplex pow(BigComplex base, BigRational exponent) {
        if (exponent.isBigInteger()) {
            return pow(base, exponent.toBigInteger());
        } else if (exponent.isNegative()) {
            return pow(base, exponent.negate()).inverse();
        }

        if (exponent.equals(BigRationalConstants.ONE_OVER_TWO)) {
            return base.sqrt();
        }

        BigRational a = BigRationalMath.pow(base.abs(), exponent);
        BigRational b = angle(base).multiply(exponent);
        return of(a.multiply(BigRationalMath.cos(b)), a.multiply(BigRationalMath.sin(b)));
    }

    public static BigComplex pow(BigComplex base, BigComplex exponent) {
        if (base.isOne() || exponent.isZero()) {
            return ONE;
        } else if (base.isZero()) {
            return ZERO;
        } else if (exponent.isOne()) {
            return base;
        } else if (exponent.isReal()) {
            return pow(base, exponent.getReal());
        }

        return exp(exponent.multiply(ln(base)));
    }

    public static BigComplex exp(BigComplex z) {
        if (z.isZero()) {
            return ONE;
        }

        BigRational r = BigRationalMath.exp(z.getReal());
        return of(r.multiply(BigRationalMath.cos(z.getImag())),
                r.multiply(BigRationalMath.sin(z.getImag())));
    }

    public static BigComplex ln(BigComplex z) {
        if (z.isZero()) {
            throw new ArithmeticException();
        } else if (z.isOne()) {
            return ZERO;
        } else if (z.isReal()) {
            return real(BigRationalMath.ln(z.getReal()));
        }

        return of(BigRationalMath.ln(z.abs()), angle(z));
    }

    public static BigComplex log(BigComplex base, BigComplex z) {
        return ln(z).divide(ln(base));
    }

    public static BigComplex sin(BigComplex z) {
        BigComplex exp = exp(I.multiply(z));
        return imag(BigRationalConstants.ONE_OVER_TWO.negate()).multiply(exp.subtract(exp.inverse()));
    }

    public static BigComplex cos(BigComplex z) {
        BigComplex exp = exp(I.multiply(z));
        return ONE_OVER_TWO.multiply(exp.add(exp.inverse()));
    }

    public static BigComplex tan(BigComplex z) {
        BigComplex exp = exp(imag(BigRationalConstants.TWO).multiply(z));
        return MINUS_I.multiply(exp.subtract(ONE).divide(exp.add(ONE)));
    }

    public static BigComplex cot(BigComplex z) {
        return tan(z).inverse();
    }

    public static BigComplex sec(BigComplex z) {
        return cos(z).inverse();
    }

    public static BigComplex csc(BigComplex z) {
        return sin(z).inverse();
    }

    public static BigComplex asin(BigComplex z) {
        return I.multiply(ln(sqrt(ONE.subtract(z.squared())).subtract(I.multiply(z))));
    }

    public static BigComplex acos(BigComplex z) {
        return PI_OVER_TWO.subtract(asin(z));
    }

    public static BigComplex atan(BigComplex z) {
        return MINUS_I.divide(TWO).multiply(ln(I.subtract(z).divide(I.add(z))));
    }

    public static BigComplex acot(BigComplex z) {
        return PI_OVER_TWO.subtract(atan(z));
    }

    public static BigComplex asec(BigComplex z) {
        BigComplex zInv = z.inverse();
        return MINUS_I.multiply(ln(I.multiply(sqrt(ONE.subtract(zInv.squared()))).add(zInv)));
    }

    public static BigComplex acsc(BigComplex z) {
        return PI_OVER_TWO.subtract(asec(z));
    }

    public static BigComplex sinh(BigComplex z) {
        BigComplex exp = exp(z);
        return ONE_OVER_TWO.multiply(exp.subtract(exp.inverse()));
    }

    public static BigComplex cosh(BigComplex z) {
        BigComplex exp = exp(z);
        return ONE_OVER_TWO.multiply(exp.add(exp.inverse()));
    }

    public static BigComplex tanh(BigComplex z) {
        BigComplex exp = exp(TWO.multiply(z));
        return exp.subtract(ONE).divide(exp.add(ONE));
    }

    public static BigComplex coth(BigComplex z) {
        return tanh(z).inverse();
    }

    public static BigComplex sech(BigComplex z) {
        return cosh(z).inverse();
    }

    public static BigComplex csch(BigComplex z) {
        return sinh(z).inverse();
    }

    public static BigComplex asinh(BigComplex z) {
        return ln(z.add(sqrt(z.squared().add(ONE))));
    }

    public static BigComplex acosh(BigComplex z) {
        return ln(z.add(sqrt(z.add(ONE)).multiply(sqrt(z.subtract(ONE)))));
    }

    public static BigComplex atanh(BigComplex z) {
        return ONE_OVER_TWO.multiply(ln(ONE.add(z).divide(ONE.subtract(z))));
    }

    public static BigComplex acoth(BigComplex z) {
        return ONE_OVER_TWO.multiply(ln(z.add(ONE).divide(z.subtract(ONE))));
    }

    public static BigComplex asech(BigComplex z) {
        BigComplex zInv = z.inverse();
        return ln(zInv.add(sqrt(zInv.add(ONE)).multiply(sqrt(zInv.subtract(ONE)))));
    }

    public static BigComplex acsch(BigComplex z) {
        BigComplex zInv = z.inverse();
        return ln(zInv.add(sqrt(zInv.squared().add(ONE))));
    }
}

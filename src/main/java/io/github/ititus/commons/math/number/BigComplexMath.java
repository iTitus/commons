package io.github.ititus.commons.math.number;

import java.math.BigInteger;

import static io.github.ititus.commons.math.number.BigComplex.imag;
import static io.github.ititus.commons.math.number.BigComplex.of;

public final class BigComplexMath {

    private BigComplexMath() {
    }

    public static BigRational angle(BigComplex z) {
        return BigRationalMath.atan2(z.getImag(), z.getReal());
    }

    public static BigComplex sqrt(BigComplex z) {
        BigRational abs = z.abs();

        BigRational real = abs.add(z.getReal()).divide(BigRationalConstants.TWO).sqrt();
        BigRational imag = abs.subtract(z.getReal()).divide(BigRationalConstants.TWO).sqrt();
        if (z.getImag().isNegative()) {
            imag = imag.negate();
        }

        return of(real, imag);
    }

    @SuppressWarnings("Duplicates")
    public static BigComplex pow(BigComplex base, BigInteger exponent) {
        if (exponent.signum() < 0) {
            return pow(base, exponent.negate()).inverse();
        }

        BigComplex z = BigComplexConstants.ONE;
        while (exponent.signum() > 0) {
            if (BigIntegerMath.isOdd(exponent)) {
                z = z.multiply(base);
                exponent = exponent.subtract(BigIntegerConstants.ONE);
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
        } else if (exponent.equals(BigRationalConstants.ONE_OVER_TWO)) {
            return base.sqrt();
        }

        BigRational a = BigRationalMath.pow(base.abs(), exponent);
        BigRational b = angle(base).multiply(exponent);
        return of(a.multiply(BigRationalMath.cos(b)), a.multiply(BigRationalMath.sin(b)));
    }

    public static BigComplex pow(BigComplex base, BigComplex exponent) {
        if (base.isOne() || exponent.isZero()) {
            return BigComplexConstants.ONE;
        } else if (base.isZero()) {
            return BigComplexConstants.ZERO;
        } else if (exponent.isOne()) {
            return base;
        } else if (exponent.isReal()) {
            return pow(base, exponent.getReal());
        }

        return exp(exponent.multiply(ln(base)));
    }

    public static BigComplex exp(BigComplex z) {
        if (z.isZero()) {
            return BigComplexConstants.ONE;
        }

        BigRational r = BigRationalMath.exp(z.getReal());
        return of(r.multiply(BigRationalMath.cos(z.getImag())),
                r.multiply(BigRationalMath.sin(z.getImag())));
    }

    public static BigComplex ln(BigComplex z) {
        if (z.isZero()) {
            throw new ArithmeticException("natural logarithm of zero");
        } else if (z.isOne()) {
            return BigComplexConstants.ZERO;
        }

        return of(BigRationalMath.ln(z.abs()), angle(z));
    }

    public static BigComplex log(BigComplex base, BigComplex z) {
        return ln(z).divide(ln(base));
    }

    public static BigComplex sin(BigComplex z) {
        BigComplex exp = exp(BigComplexConstants.I.multiply(z));
        return imag(BigRationalConstants.ONE_OVER_TWO.negate()).multiply(exp.subtract(exp.inverse()));
    }

    public static BigComplex cos(BigComplex z) {
        BigComplex exp = exp(BigComplexConstants.I.multiply(z));
        return BigComplexConstants.ONE_OVER_TWO.multiply(exp.add(exp.inverse()));
    }

    public static BigComplex tan(BigComplex z) {
        BigComplex exp = exp(imag(BigRationalConstants.TWO).multiply(z));
        return BigComplexConstants.MINUS_I.multiply(exp.subtract(BigComplexConstants.ONE).divide(exp.add(BigComplexConstants.ONE)));
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
        return BigComplexConstants.I.multiply(ln(sqrt(BigComplexConstants.ONE.subtract(z.squared())).subtract(BigComplexConstants.I.multiply(z))));
    }

    public static BigComplex acos(BigComplex z) {
        return BigComplexConstants.PI_OVER_TWO.subtract(asin(z));
    }

    public static BigComplex atan(BigComplex z) {
        return BigComplexConstants.MINUS_I.divide(BigComplexConstants.TWO).multiply(ln(BigComplexConstants.I.subtract(z).divide(BigComplexConstants.I.add(z))));
    }

    public static BigComplex acot(BigComplex z) {
        return BigComplexConstants.PI_OVER_TWO.subtract(atan(z));
    }

    public static BigComplex asec(BigComplex z) {
        BigComplex zInv = z.inverse();
        return BigComplexConstants.MINUS_I.multiply(ln(BigComplexConstants.I.multiply(sqrt(BigComplexConstants.ONE.subtract(zInv.squared()))).add(zInv)));
    }

    public static BigComplex acsc(BigComplex z) {
        return BigComplexConstants.PI_OVER_TWO.subtract(asec(z));
    }

    public static BigComplex sinh(BigComplex z) {
        BigComplex exp = exp(z);
        return BigComplexConstants.ONE_OVER_TWO.multiply(exp.subtract(exp.inverse()));
    }

    public static BigComplex cosh(BigComplex z) {
        BigComplex exp = exp(z);
        return BigComplexConstants.ONE_OVER_TWO.multiply(exp.add(exp.inverse()));
    }

    public static BigComplex tanh(BigComplex z) {
        BigComplex exp = exp(BigComplexConstants.TWO.multiply(z));
        return exp.subtract(BigComplexConstants.ONE).divide(exp.add(BigComplexConstants.ONE));
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
        return ln(z.add(sqrt(z.squared().add(BigComplexConstants.ONE))));
    }

    public static BigComplex acosh(BigComplex z) {
        return ln(z.add(sqrt(z.add(BigComplexConstants.ONE)).multiply(sqrt(z.subtract(BigComplexConstants.ONE)))));
    }

    public static BigComplex atanh(BigComplex z) {
        return BigComplexConstants.ONE_OVER_TWO.multiply(ln(BigComplexConstants.ONE.add(z).divide(BigComplexConstants.ONE.subtract(z))));
    }

    public static BigComplex acoth(BigComplex z) {
        return BigComplexConstants.ONE_OVER_TWO.multiply(ln(z.add(BigComplexConstants.ONE).divide(z.subtract(BigComplexConstants.ONE))));
    }

    public static BigComplex asech(BigComplex z) {
        BigComplex zInv = z.inverse();
        return ln(zInv.add(sqrt(zInv.add(BigComplexConstants.ONE)).multiply(sqrt(zInv.subtract(BigComplexConstants.ONE)))));
    }

    public static BigComplex acsch(BigComplex z) {
        BigComplex zInv = z.inverse();
        return ln(zInv.add(sqrt(zInv.squared().add(BigComplexConstants.ONE))));
    }
}

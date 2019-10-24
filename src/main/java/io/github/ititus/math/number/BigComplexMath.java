package io.github.ititus.math.number;

import java.math.BigInteger;

public final class BigComplexMath {

    private BigComplexMath() {
    }

    public static BigRational angle(BigComplex z) {
        return BigRationalMath.atan2(z.getImag(), z.getReal());
    }

    public static BigComplex sqrt(BigComplex z) {
        BigRational abs = z.abs();
        return BigComplex.real(abs.sqrt()).multiply(z.add(BigComplex.real(abs)).unit());
    }

    @SuppressWarnings("Duplicates")
    public static BigComplex pow(BigComplex base, BigInteger exponent) {
        if (exponent.signum() < 0) {
            return pow(base, exponent.negate()).inverse();
        }

        BigComplex z = BigComplex.ONE;

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
        return BigComplex.of(a.multiply(BigRationalMath.cos(b)), a.multiply(BigRationalMath.sin(b)));
    }

    public static BigComplex pow(BigComplex base, BigComplex exponent) {
        if (base.isOne() || exponent.isZero()) {
            return BigComplex.ONE;
        } else if (base.isZero()) {
            return BigComplex.ZERO;
        } else if (exponent.isOne()) {
            return base;
        } else if (exponent.isReal()) {
            return pow(base, exponent.getReal());
        }

        return exp(exponent.multiply(ln(base)));
    }

    public static BigComplex exp(BigComplex z) {
        if (z.isZero()) {
            return BigComplex.ONE;
        }

        BigRational r = BigRationalMath.exp(z.getReal());
        return BigComplex.of(r.multiply(BigRationalMath.cos(z.getImag())), r.multiply(BigRationalMath.sin(z.getImag())));
    }

    public static BigComplex ln(BigComplex z) {
        if (z.isZero()) {
            throw new ArithmeticException();
        } else if (z.isOne()) {
            return BigComplex.ZERO;
        } else if (z.isReal()) {
            return BigComplex.real(BigRationalMath.ln(z.getReal()));
        }

        return BigComplex.of(BigRationalMath.ln(z.abs()), angle(z));
    }

    public static BigComplex log(BigComplex base, BigComplex z) {
        return ln(z).divide(ln(base));
    }

    public static BigComplex sin(BigComplex z) {
        BigComplex exp = exp(BigComplex.I.multiply(z));
        return BigComplex.imag(BigRationalConstants.ONE_OVER_TWO.negate()).multiply(exp.subtract(exp.inverse()));
    }

    public static BigComplex cos(BigComplex z) {
        BigComplex exp = exp(BigComplex.I.multiply(z));
        return BigComplex.real(BigRationalConstants.ONE_OVER_TWO).multiply(exp.add(exp.inverse()));
    }

    public static BigComplex tan(BigComplex z) {
        BigComplex exp = exp(BigComplex.imag(BigRationalConstants.TWO).multiply(z));
        return BigComplex.MINUS_I.multiply(exp.subtract(BigComplex.ONE).divide(exp.add(BigComplex.ONE)));
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
        return BigComplex.MINUS_I.multiply(ln(BigComplex.I.multiply(z).add(sqrt(BigComplex.ONE.subtract(z.squared())))));
    }

    public static BigComplex acos(BigComplex z) {
        return BigComplex.MINUS_I.multiply(ln(z.add(BigComplex.I.multiply(sqrt(BigComplex.ONE.subtract(z.squared()))))));
    }

    public static BigComplex atan(BigComplex z) {
        BigComplex iz = BigComplex.I.multiply(z);
        return BigComplex.imag(BigRationalConstants.ONE_OVER_TWO.negate()).multiply(ln(BigComplex.ONE.add(iz).divide(BigComplex.ONE.subtract(iz))));
    }

    public static BigComplex acot(BigComplex z) {
        return atan(z.inverse());
    }

    public static BigComplex asec(BigComplex z) {
        return acos(z.inverse());
    }

    public static BigComplex acsc(BigComplex z) {
        return asin(z.inverse());
    }

    public static BigComplex sinh(BigComplex z) {
        BigComplex exp = exp(z);
        return BigComplex.real(BigRationalConstants.ONE_OVER_TWO).multiply(exp.subtract(exp.inverse()));
    }

    public static BigComplex cosh(BigComplex z) {
        BigComplex exp = exp(z);
        return BigComplex.real(BigRationalConstants.ONE_OVER_TWO).multiply(exp.add(exp.inverse()));
    }

    public static BigComplex tanh(BigComplex z) {
        BigComplex exp = exp(BigComplex.real(BigRationalConstants.TWO).multiply(z));
        return exp.subtract(BigComplex.ONE).divide(exp.add(BigComplex.ONE));
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
        return ln(z.add(sqrt(z.squared().add(BigComplex.ONE))));
    }

    public static BigComplex acosh(BigComplex z) {
        return ln(z.add(sqrt(z.squared().subtract(BigComplex.ONE))));
    }

    public static BigComplex atanh(BigComplex z) {
        return BigComplex.real(BigRationalConstants.ONE_OVER_TWO).multiply(ln(BigComplex.ONE.add(z).divide(BigComplex.ONE.subtract(z))));
    }

    public static BigComplex acoth(BigComplex z) {
        return atanh(z.inverse());
    }

    public static BigComplex asech(BigComplex z) {
        return acosh(z.inverse());
    }

    public static BigComplex acsch(BigComplex z) {
        return asinh(z.inverse());
    }
}

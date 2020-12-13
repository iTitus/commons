package io.github.ititus.math.number;

import io.github.ititus.math.function.PowerSeriesCalculator;

import java.math.BigDecimal;
import java.math.BigInteger;

import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRationalConstants.*;

public final class BigRationalMath {

    private BigRationalMath() {
    }

    @SuppressWarnings("Duplicates")
    public static BigRational pow(BigRational base, BigInteger exponent) {
        if (base.isOne() || exponent.signum() == 0) {
            return ONE;
        } else if (base.isZero()) {
            return ZERO;
        } else if (exponent.signum() < 0) {
            return pow(base, exponent.negate()).inverse();
        }

        BigRational r = ONE;

        while (exponent.signum() > 0) {
            if (BigIntegerMath.isOdd(exponent)) {
                r = r.multiply(base);
                exponent = exponent.subtract(BigInteger.ONE);
            } else {
                base = base.squared();
                exponent = exponent.shiftRight(1);
            }
        }

        return r;
    }

    public static BigRational pow(BigRational base, BigDecimal exponent) {
        if (base.isOne() || exponent.signum() == 0) {
            return ONE;
        } else if (base.isZero()) {
            return ZERO;
        } else if (exponent.equals(BigDecimal.ONE)) {
            return base;
        } else if (exponent.signum() < 0) {
            return pow(base, exponent.negate()).inverse();
        }

        if (exponent.equals(BigDecimalConstants.ONE_OVER_TWO)) {
            return base.sqrt();
        }

        return exp(BigRational.of(exponent).multiply(ln(base)));
    }

    public static BigRational pow(BigRational base, BigRational exponent) {
        if (base.isOne() || exponent.isZero()) {
            return ONE;
        } else if (base.isZero()) {
            return ZERO;
        } else if (exponent.isOne()) {
            return base;
        } else if (exponent.isBigInteger()) {
            return pow(base, exponent.toBigInteger());
        } else if (exponent.isNegative()) {
            return pow(base, exponent.negate()).inverse();
        }

        if (exponent.equals(ONE_OVER_TWO)) {
            return base.sqrt();
        }

        return exp(exponent.multiply(ln(base)));
    }

    public static BigRational exp(BigRational x) {
        if (x.isZero()) {
            return ONE;
        } else if (x.isNegative()) {
            return exp(x.negate()).inverse();
        } else if (x.compareTo(ONE) >= 0) {
            BigInteger n = TWO.multiply(x).toBigInteger();
            BigRational exp = PowerSeriesCalculator.exp(x.divide(of(n)));
            return exp.pow(n);
        }

        return PowerSeriesCalculator.exp(x);
    }

    public static BigRational ln(BigRational x) {
        if (x.isZero() || x.isNegative()) {
            throw new ArithmeticException();
        } else if (x.isOne()) {
            return ZERO;
        }

        BigRational lnFast = PowerSeriesCalculator.lnFast(x);
        return lnFast.add(PowerSeriesCalculator.ln(x.divide(lnFast.exp())));
    }

    public static BigRational sin(BigRational x) {
        if (x.isZero()) {
            return ZERO;
        } else if (x.isNegative()) {
            return sin(x.negate()).negate();
        }

        return cos(x.subtract(PI_OVER_TWO));
    }

    public static BigRational cos(BigRational x) {
        if (x.isNegative()) {
            return cos(x.negate());
        } else if (x.compareTo(PI) > 0) {
            return cos(x.subtract(PI)).negate();
        }
        while (x.compareTo(TWO_PI) > 0) {
            x = x.subtract(TWO_PI);
        }
        if (x.isZero()) {
            return ONE;
        }

        return PowerSeriesCalculator.cos(x);
    }

    public static BigRational tan(BigRational x) {
        return sin(x).divide(cos(x));
    }

    public static BigRational cot(BigRational x) {
        return cos(x).divide(sin(x));
    }

    public static BigRational sec(BigRational x) {
        return cos(x).inverse();
    }

    public static BigRational csc(BigRational x) {
        return sin(x).inverse();
    }

    public static BigRational asin(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational acos(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational atan(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational atan2(BigRational y, BigRational x) {
        if (y.isZero()) {
            if (x.isZero()) {
                throw new ArithmeticException();
            }
            return x.isPositive() ? ZERO : PI;
        } else if (x.isZero()) {
            return y.isPositive() ? PI.divide(TWO) : PI.divide(TWO).negate();
        } else if (x.isPositive()) {
            return atan(y.divide(x));
        } else if (y.isPositive()) {
            return atan(y.divide(x)).add((PI));
        } else {
            return atan(y.divide(x)).subtract((PI));
        }
    }

    public static BigRational acot(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational asec(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational acsc(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational sinh(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational cosh(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational tanh(BigRational x) {
        return sinh(x).divide(cosh(x));
    }

    public static BigRational coth(BigRational x) {
        return cosh(x).divide(sinh(x));
    }

    public static BigRational sech(BigRational x) {
        return cosh(x).inverse();
    }

    public static BigRational csch(BigRational x) {
        return sinh(x).inverse();
    }

    public static BigRational asinh(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational acosh(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational atanh(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational acoth(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational asech(BigRational x) {
        throw new UnsupportedOperationException();
    }

    public static BigRational acsch(BigRational x) {
        throw new UnsupportedOperationException();
    }
}

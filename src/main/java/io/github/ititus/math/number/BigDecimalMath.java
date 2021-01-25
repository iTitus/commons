package io.github.ititus.math.number;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import static io.github.ititus.math.number.BigDecimalConstants.ONE;

public final class BigDecimalMath {

    public static MathContext MC = MathContext.DECIMAL128;

    private BigDecimalMath() {
    }

    public static BigDecimal of(int n) {
        return BigDecimal.valueOf(n);
    }

    public static BigDecimal of(long n) {
        return BigDecimal.valueOf(n);
    }

    public static BigDecimal of(BigInteger n) {
        if (BigIntegerMath.isInt(n)) {
            return of(n.intValueExact());
        } else if (BigIntegerMath.isLong(n)) {
            return of(n.longValueExact());
        }

        return new BigDecimal(n);
    }

    public static BigDecimal of(float f) {
        return BigDecimal.valueOf(f);
    }

    public static BigDecimal of(double d) {
        return BigDecimal.valueOf(d);
    }

    public static BigDecimal of(BigRational r) {
        if (r.isBigInteger()) {
            return of(r.toBigInteger());
        }

        return of(r.getNumerator()).divide(of(r.getDenominator()), MC);
    }

    public static BigDecimal of(String s) {
        return new BigDecimal(s);
    }

    public static boolean isBigInteger(BigDecimal x) {
        try {
            //noinspection ResultOfMethodCallIgnored
            x.toBigIntegerExact();
            return true;
        } catch (ArithmeticException ignored) {
            return false;
        }
    }

    public static BigDecimal inverseExact(BigDecimal d) {
        return ONE.divide(d, RoundingMode.UNNECESSARY);
    }

    public static BigDecimal inverse(BigDecimal d) {
        return inverse(d, MC);
    }

    public static BigDecimal inverse(BigDecimal d, RoundingMode rm) {
        return ONE.divide(d, rm);
    }

    public static BigDecimal inverse(BigDecimal d, MathContext mc) {
        return ONE.divide(d, mc);
    }
}

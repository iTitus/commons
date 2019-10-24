package io.github.ititus.math.number;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

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
}

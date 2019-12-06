package io.github.ititus.math.number;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.regex.Pattern;

import static io.github.ititus.math.number.BigRationalConstants.*;

public final class BigRational extends Number implements Comparable<BigRational> {

    private static final Pattern RATIONAL_SEPARATOR_PATTERN = Pattern.compile("( )*[:/]( )*");

    private final BigInteger numerator, denominator;

    private BigDecimal decimalCache;
    private BigInteger intCache;
    private BigRational negCache, invCache, sqCache, sqrtCache;

    BigRational(BigInteger nominator, BigInteger denominator) {
        Objects.requireNonNull(nominator);
        Objects.requireNonNull(denominator);
        if (denominator.signum() <= 0) {
            throw new ArithmeticException();
        }

        this.numerator = nominator;
        this.denominator = denominator;
    }

    public static BigRational of(Object o) {
        if (o instanceof String) {
            return of((String) o);
        } else if (o instanceof Integer) {
            return of(((Number) o).intValue());
        } else if (o instanceof Long) {
            return of(((Number) o).longValue());
        } else if (o instanceof Float) {
            return of(((Number) o).floatValue());
        } else if (o instanceof Double) {
            return of(((Number) o).doubleValue());
        } else if (o instanceof BigDecimal) {
            return of((BigDecimal) o);
        }

        throw new IllegalArgumentException();
    }

    public static BigRational of(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String[] split = RATIONAL_SEPARATOR_PATTERN.split(s, 2);
        if (split.length == 2) {
            return of(BigIntegerMath.of(split[0]), BigIntegerMath.of(split[1]));
        }

        return of(BigDecimalMath.of(s));
    }

    public static BigRational of(int n) {
        return of(BigIntegerMath.of(n));
    }

    public static BigRational of(long n) {
        return of(BigIntegerMath.of(n));
    }

    public static BigRational of(BigInteger n) {
        return of(n, BigInteger.ONE);
    }

    public static BigRational of(float f) {
        return of(BigDecimalMath.of(f));
    }

    public static BigRational of(double d) {
        return of(BigDecimalMath.of(d));
    }

    public static BigRational of(BigDecimal d) {
        BigInteger unscaled = d.unscaledValue();
        int scale = d.scale();
        BigInteger scalar = BigInteger.TEN.pow(Math.abs(scale));

        if (scale <= 0) {
            of(unscaled.multiply(scalar));
        }
        return of(unscaled, scalar);
    }

    @SuppressWarnings("Duplicates")
    public static BigRational of(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException();
        } else if (numerator.signum() == 0) {
            return ZERO;
        } else if (numerator.equals(denominator)) {
            return ONE;
        }

        if (denominator.signum() < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }

        BigInteger gcd = BigIntegerMath.gcd(numerator, denominator);
        if (!gcd.equals(BigInteger.ONE)) {
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);
        }

        BigRational r = new BigRational(numerator, denominator);

        if (r.equals(MINUS_ONE)) {
            return MINUS_ONE;
        } else if (r.equals(TWO)) {
            return TWO;
        } else if (r.equals(THREE)) {
            return THREE;
        } else if (r.equals(FOUR)) {
            return FOUR;
        } else if (r.equals(TEN)) {
            return TEN;
        } else if (r.equals(ONE_OVER_TWO)) {
            return ONE_OVER_TWO;
        } else if (r.equals(ONE_OVER_THREE)) {
            return ONE_OVER_THREE;
        } else if (r.equals(THREE_OVER_FOUR)) {
            return THREE_OVER_FOUR;
        } else if (r.equals(FIVE_OVER_FOUR)) {
            return FIVE_OVER_FOUR;
        }

        return r;
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public BigRational negate() {
        if (isZero()) {
            return ZERO;
        } else if (negCache == null) {
            negCache = of(numerator.negate(), denominator);
            negCache.negCache = this;
        }

        return negCache;
    }

    public BigRational inverse() {
        if (isZero()) {
            throw new ArithmeticException();
        } else if (isOne()) {
            return ONE;
        } else if (invCache == null) {
            invCache = of(denominator, numerator);
            invCache.invCache = this;
        }

        return invCache;
    }

    public BigRational add(BigRational r) {
        if (isZero()) {
            return r;
        } else if (r.isZero()) {
            return this;
        }

        BigInteger lcm = BigIntegerMath.lcm(denominator, r.denominator);
        return of(numerator.multiply(lcm.divide(denominator)).add(r.numerator.multiply(lcm.divide(r.denominator))), lcm);
    }

    public BigRational subtract(BigRational r) {
        if (isZero()) {
            return r.negate();
        } else if (r.isZero()) {
            return this;
        }

        return add(r.negate());
    }

    public BigRational multiply(BigInteger n) {
        if (isZero() || n.signum() == 0) {
            return ZERO;
        } else if (isOne()) {
            return of(n);
        } else if (n.equals(BigInteger.ONE)) {
            return this;
        } else if (isBigInteger() && numerator.equals(n)) {
            return squared();
        }

        return of(numerator.multiply(n), denominator);
    }

    public BigRational multiply(BigRational r) {
        if (isZero() || r.isZero()) {
            return ZERO;
        } else if (isOne()) {
            return r;
        } else if (r.isOne()) {
            return this;
        } else if (equals(r)) {
            return squared();
        }

        return of(numerator.multiply(r.numerator), denominator.multiply(r.denominator));
    }

    public BigRational divide(BigRational r) {
        if (r.isZero()) {
            throw new ArithmeticException();
        } else if (isZero()) {
            return ZERO;
        } else if (isOne()) {
            return r.inverse();
        } else if (r.isOne()) {
            return this;
        }

        return multiply(r.inverse());
    }

    public BigRational squared() {
        if (sqCache == null) {
            sqCache = of(numerator.multiply(numerator), denominator.multiply(denominator));
            sqCache.sqrtCache = this;
        }

        return sqCache;
    }

    public BigRational sqrt() {
        if (sqrtCache == null) {
            sqrtCache = of(toBigDecimal().sqrt(BigDecimalMath.MC));
        }

        return sqrtCache;
    }

    @SuppressWarnings("Duplicates")
    public BigRational pow(int exponent) {
        if (exponent == 0) {
            return ONE;
        } else if (isZero()) {
            return ZERO;
        } else if (exponent == 1) {
            return this;
        } else if (exponent == 2) {
            return squared();
        }

        return BigRationalMath.pow(this, BigIntegerMath.of(exponent));
    }

    @SuppressWarnings("Duplicates")
    public BigRational pow(long exponent) {
        if (exponent == 0) {
            return ONE;
        } else if (isZero()) {
            return ZERO;
        } else if (exponent == 1) {
            return this;
        } else if (exponent == 2) {
            return squared();
        }

        return BigRationalMath.pow(this, BigIntegerMath.of(exponent));
    }

    public BigRational pow(BigInteger exponent) {
        if (exponent.signum() == 0) {
            return ONE;
        } else if (isZero()) {
            return ZERO;
        } else if (exponent.equals(BigInteger.ONE)) {
            return this;
        } else if (exponent.equals(BigInteger.TWO)) {
            return squared();
        }

        return BigRationalMath.pow(this, exponent);
    }

    public BigRational pow(BigRational exponent) {
        if (exponent.isZero()) {
            return ONE;
        } else if (isZero()) {
            return ZERO;
        } else if (exponent.isBigInteger()) {
            return pow(exponent.toBigInteger());
        }

        return BigRationalMath.pow(this, exponent);
    }

    public BigRational exp() {
        if (isZero()) {
            return ONE;
        } else if (isNegative()) {
            return negate().exp().inverse();
        }

        return BigRationalMath.exp(this);
    }

    public BigRational max(BigRational r) {
        return compareTo(r) >= 0 ? this : r;
    }

    public BigRational min(BigRational r) {
        return compareTo(r) <= 0 ? this : r;
    }

    public BigRational abs() {
        return isNegative() ? negate() : this;
    }

    public BigRational round(MathContext mc) {
        return isBigInteger() ? this : of(toBigDecimal().round(mc));
    }

    public BigInteger roundToBigInt(RoundingMode mode) {
        return toBigDecimal().setScale(0, mode).toBigIntegerExact();
    }

    public BigRational sin() {
        return BigRationalMath.sin(this);
    }

    public BigRational cos() {
        return BigRationalMath.cos(this);
    }

    public BigRational tan() {
        return BigRationalMath.tan(this);
    }

    public BigRational cot() {
        return BigRationalMath.cot(this);
    }

    public BigRational sec() {
        return BigRationalMath.sec(this);
    }

    public BigRational csc() {
        return BigRationalMath.csc(this);
    }

    public BigRational asin() {
        return BigRationalMath.asin(this);
    }

    public BigRational acos() {
        return BigRationalMath.acos(this);
    }

    public BigRational atan() {
        return BigRationalMath.atan(this);
    }

    public BigRational acot() {
        return BigRationalMath.acot(this);
    }

    public BigRational asec() {
        return BigRationalMath.asec(this);
    }

    public BigRational acsc() {
        return BigRationalMath.acsc(this);
    }

    public BigRational sinh() {
        return BigRationalMath.sinh(this);
    }

    public BigRational cosh() {
        return BigRationalMath.cosh(this);
    }

    public BigRational tanh() {
        return BigRationalMath.tanh(this);
    }

    public BigRational coth() {
        return BigRationalMath.coth(this);
    }

    public BigRational sech() {
        return BigRationalMath.sech(this);
    }

    public BigRational csch() {
        return BigRationalMath.csch(this);
    }

    public BigRational asinh() {
        return BigRationalMath.asinh(this);
    }

    public BigRational acosh() {
        return BigRationalMath.acosh(this);
    }

    public BigRational atanh() {
        return BigRationalMath.atanh(this);
    }

    public BigRational acoth() {
        return BigRationalMath.acoth(this);
    }

    public BigRational asech() {
        return BigRationalMath.asech(this);
    }

    public BigRational acsch() {
        return BigRationalMath.acsch(this);
    }

    public boolean isPositive() {
        return numerator.signum() > 0;
    }

    public boolean isZero() {
        return numerator.signum() == 0;
    }

    public boolean isOne() {
        return equals(ONE);
    }

    public boolean isNegative() {
        return numerator.signum() < 0;
    }

    public boolean isBigInteger() {
        return denominator.equals(BigInteger.ONE);
    }

    public boolean isInt() {
        return isBigInteger() && BigIntegerMath.isInt(numerator);
    }

    public boolean isLong() {
        return isBigInteger() && BigIntegerMath.isLong(numerator);
    }

    @Override
    public int intValue() {
        return isInt() ? numerator.intValueExact() : toBigDecimal().intValue();
    }

    @Override
    public long longValue() {
        return isLong() ? numerator.longValueExact() : toBigDecimal().longValue();
    }

    public BigInteger toBigInteger() {
        if (isBigInteger()) {
            return numerator;
        } else if (intCache == null) {
            intCache = toBigDecimal().toBigInteger();
        }

        return intCache;
    }

    @Override
    public float floatValue() {
        return toBigDecimal().floatValue();
    }

    @Override
    public double doubleValue() {
        return toBigDecimal().doubleValue();
    }

    public BigDecimal toBigDecimal() {
        if (decimalCache == null) {
            decimalCache = BigDecimalMath.of(this);
        }

        return decimalCache;
    }

    public String toRatioString() {
        return numerator + ":" + denominator;
    }

    @Override
    public int compareTo(BigRational r) {
        BigInteger lcm = BigIntegerMath.lcm(denominator, r.denominator);
        return numerator.multiply(lcm.divide(denominator)).compareTo(r.numerator.multiply(lcm.divide(r.denominator)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BigRational r = (BigRational) o;
        return numerator.equals(r.numerator) && denominator.equals(r.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return denominator.equals(BigInteger.ONE) ? String.valueOf(numerator) : numerator + "/" + denominator + " (" + toBigDecimal() + ")";
    }
}

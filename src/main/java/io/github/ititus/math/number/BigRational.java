package io.github.ititus.math.number;

import io.github.ititus.data.ObjectUtil;
import io.github.ititus.si.quantity.value.QuantityValue;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.ititus.math.number.BigRationalConstants.*;

public final class BigRational extends Number implements Comparable<BigRational> {

    private static final Pattern RATIONAL_PATTERN = Pattern.compile(
            "^(?<sign>[+\\-]?)(?<num>[^:/]+?)(?:[:/](?<denom>[^:/]+?))?$"
    );

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
        Objects.requireNonNull(o);
        if (o instanceof BigRational) {
            return of((BigRational) o);
        } else if (o instanceof String) {
            return of((String) o);
        } else if (o instanceof Byte) {
            return of((byte) o);
        } else if (o instanceof Short) {
            return of((short) o);
        } else if (o instanceof Integer || o instanceof AtomicInteger) {
            return of(((Number) o).intValue());
        } else if (o instanceof Long || o instanceof AtomicLong || o instanceof LongAdder || o instanceof LongAccumulator) {
            return of(((Number) o).longValue());
        } else if (o instanceof BigInteger) {
            return of((BigInteger) o);
        } else if (o instanceof Float) {
            return of((float) o);
        } else if (o instanceof Double || o instanceof DoubleAdder || o instanceof DoubleAccumulator) {
            return of(((Number) o).doubleValue());
        } else if (o instanceof BigDecimal) {
            return of((BigDecimal) o);
        } else if (o instanceof QuantityValue) {
            return of(((QuantityValue) o).bigRationalValue());
        } else if (o instanceof Number) {
            return of(((Number) o).doubleValue());
        } else if (o instanceof BigComplex) {
            return of((BigComplex) o);
        } else if (o instanceof Iterable) {
            Iterator<?> it = ((Iterable<?>) o).iterator();
            if (it.hasNext()) {
                Object n = it.next();
                if (!it.hasNext()) {
                    try {
                        return of(n);
                    } catch (RuntimeException e) {
                        throw new IllegalArgumentException(
                                ObjectUtil.toString(o) + " cannot be converted to BigRational", e);
                    }
                }
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 1) {
                try {
                    return of(Array.get(o, 0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to BigRational", e);
                }
            }
        } else if (o instanceof AtomicIntegerArray) {
            AtomicIntegerArray arr = (AtomicIntegerArray) o;
            if (arr.length() == 1) {
                return of(arr.get(0));
            }
        } else if (o instanceof AtomicLongArray) {
            AtomicLongArray arr = (AtomicLongArray) o;
            if (arr.length() == 1) {
                return of(arr.get(0));
            }
        } else if (o instanceof AtomicReferenceArray) {
            AtomicReferenceArray<?> arr = (AtomicReferenceArray<?>) o;
            if (arr.length() == 1) {
                try {
                    return of(arr.get(0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to BigRational", e);
                }
            }
        }

        throw new IllegalArgumentException(ObjectUtil.toString(o) + " cannot be converted to BigRational");
    }

    public static BigRational of(BigRational r) {
        return Objects.requireNonNull(r);
    }

    public static BigRational of(BigComplex z) {
        if (!z.isReal()) {
            throw new ArithmeticException(z + " cannot be converted to BigRational because it has an imaginary part");
        }

        return z.getReal();
    }

    public static BigRational of(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        }

        s = s.replaceAll("[\\s_]", "");
        if (s.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Matcher m = RATIONAL_PATTERN.matcher(s);
        if (!m.matches()) {
            throw new IllegalArgumentException();
        }

        BigRational numerator = of(BigDecimalMath.of(m.group("num")));
        if (m.group("sign").equals("-")) {
            numerator = numerator.negate();
        }

        String denominatorString = m.group("denom");
        BigRational denominator;
        if (denominatorString == null) {
            denominator = ONE;
        } else {
            denominator = of(BigDecimalMath.of(denominatorString));
        }

        return numerator.divide(denominator);
    }

    public static BigRational of(byte n) {
        return of(BigIntegerMath.of(n));
    }

    public static BigRational of(short n) {
        return of(BigIntegerMath.of(n));
    }

    public static BigRational of(long n) {
        return of(BigIntegerMath.of(n));
    }

    public static BigRational of(BigInteger n) {
        return of(n, BigIntegerConstants.ONE);
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
        BigInteger scalar = BigIntegerConstants.TEN.pow(Math.abs(scale));

        if (scale < 0) {
            return of(unscaled.multiply(scalar));
        }
        return of(unscaled, scalar);
    }

    public static BigRational ofInv(byte n) {
        return of(n).inverse();
    }

    public static BigRational ofInv(short n) {
        return of(n).inverse();
    }

    public static BigRational ofInv(int n) {
        return of(n).inverse();
    }

    public static BigRational ofInv(long n) {
        return of(n).inverse();
    }

    public static BigRational ofInv(BigInteger n) {
        return of(n).inverse();
    }

    public static BigRational ofInv(float f) {
        return of(f).inverse();
    }

    public static BigRational ofInv(double d) {
        return of(d).inverse();
    }

    public static BigRational ofInv(BigDecimal d) {
        return of(d).inverse();
    }

    public static BigRational ofExp(int scale, int exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(int scale, long exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(int scale, BigInteger exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(long scale, int exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(long scale, long exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(long scale, BigInteger exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(BigInteger scale, int exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(BigInteger scale, long exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(BigInteger scale, BigInteger exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(float scale, int exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(float scale, long exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(float scale, BigInteger exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(double scale, int exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(double scale, long exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(double scale, BigInteger exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(BigDecimal scale, int exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(BigDecimal scale, long exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational ofExp(BigDecimal scale, BigInteger exponent) {
        return of(scale).multiply(BigRationalConstants.TEN.pow(exponent));
    }

    public static BigRational of(int numerator, int denominator) {
        return of(BigIntegerMath.of(numerator), BigIntegerMath.of(denominator));
    }

    public static BigRational of(int numerator, long denominator) {
        return of(BigIntegerMath.of(numerator), BigIntegerMath.of(denominator));
    }

    public static BigRational of(int numerator, BigInteger denominator) {
        return of(BigIntegerMath.of(numerator), denominator);
    }

    public static BigRational of(long numerator, int denominator) {
        return of(BigIntegerMath.of(numerator), BigIntegerMath.of(denominator));
    }

    public static BigRational of(long numerator, long denominator) {
        return of(BigIntegerMath.of(numerator), BigIntegerMath.of(denominator));
    }

    public static BigRational of(long numerator, BigInteger denominator) {
        return of(BigIntegerMath.of(numerator), denominator);
    }

    public static BigRational of(BigInteger numerator, int denominator) {
        return of(numerator, BigIntegerMath.of(denominator));
    }

    public static BigRational of(BigInteger numerator, long denominator) {
        return of(numerator, BigIntegerMath.of(denominator));
    }

    @SuppressWarnings("Duplicates")
    public static BigRational of(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigIntegerConstants.ZERO)) {
            throw new ArithmeticException("zero denominator");
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
        if (!gcd.equals(BigIntegerConstants.ONE)) {
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);
        }

        BigRational r = new BigRational(numerator, denominator);

        if (r.equals(MINUS_ONE)) {
            return MINUS_ONE;
        }

        return r;
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public BigRational withNumerator(byte numerator) {
        BigInteger n = BigIntegerMath.of(numerator);
        if (this.numerator.equals(n)) {
            return this;
        }

        return of(n, denominator);
    }

    public BigRational withNumerator(short numerator) {
        BigInteger n = BigIntegerMath.of(numerator);
        if (this.numerator.equals(n)) {
            return this;
        }

        return of(n, denominator);
    }

    public BigRational withNumerator(int numerator) {
        BigInteger n = BigIntegerMath.of(numerator);
        if (this.numerator.equals(n)) {
            return this;
        }

        return of(n, denominator);
    }

    public BigRational withNumerator(long numerator) {
        BigInteger n = BigIntegerMath.of(numerator);
        if (this.numerator.equals(n)) {
            return this;
        }

        return of(n, denominator);
    }

    public BigRational withNumerator(BigInteger numerator) {
        if (this.numerator.equals(numerator)) {
            return this;
        }

        return of(numerator, denominator);
    }

    public BigRational withDenominator(byte denominator) {
        BigInteger n = BigIntegerMath.of(denominator);
        if (this.denominator.equals(n)) {
            return this;
        }

        return of(numerator, n);
    }

    public BigRational withDenominator(short denominator) {
        BigInteger n = BigIntegerMath.of(denominator);
        if (this.denominator.equals(n)) {
            return this;
        }

        return of(numerator, n);
    }

    public BigRational withDenominator(int denominator) {
        BigInteger n = BigIntegerMath.of(denominator);
        if (this.denominator.equals(n)) {
            return this;
        }

        return of(numerator, n);
    }

    public BigRational withDenominator(long denominator) {
        BigInteger n = BigIntegerMath.of(denominator);
        if (this.denominator.equals(n)) {
            return this;
        }

        return of(numerator, n);
    }

    public BigRational withDenominator(BigInteger denominator) {
        if (this.denominator.equals(denominator)) {
            return this;
        }

        return of(numerator, denominator);
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
            throw new ArithmeticException("zero is not invertible");
        } else if (isOne()) {
            return ONE;
        } else if (invCache == null) {
            invCache = of(denominator, numerator);
            invCache.invCache = this;
        }

        return invCache;
    }

    public BigRational add(byte n) {
        if (isZero()) {
            return of(n);
        } else if (n == 0) {
            return this;
        }

        return add(of(n));
    }

    public BigRational add(short n) {
        if (isZero()) {
            return of(n);
        } else if (n == 0) {
            return this;
        }

        return add(of(n));
    }

    public BigRational add(int n) {
        if (isZero()) {
            return of(n);
        } else if (n == 0) {
            return this;
        }

        return add(of(n));
    }

    public BigRational add(long n) {
        if (isZero()) {
            return of(n);
        } else if (n == 0) {
            return this;
        }

        return add(of(n));
    }

    public BigRational add(BigInteger n) {
        if (isZero()) {
            return of(n);
        } else if (n.signum() == 0) {
            return this;
        }

        return add(of(n));
    }

    public BigRational add(float f) {
        if (isZero()) {
            return of(f);
        } else if (f == 0) {
            return this;
        }

        return add(of(f));
    }

    public BigRational add(double d) {
        if (isZero()) {
            return of(d);
        } else if (d == 0) {
            return this;
        }

        return add(of(d));
    }

    public BigRational add(BigDecimal d) {
        if (isZero()) {
            return of(d);
        } else if (d.signum() == 0) {
            return this;
        }

        return add(of(d));
    }

    public BigRational add(BigRational r) {
        if (isZero()) {
            return r;
        } else if (r.isZero()) {
            return this;
        }

        BigInteger lcm = BigIntegerMath.lcm(denominator, r.denominator);
        return of(
                numerator.multiply(lcm.divide(denominator))
                        .add(r.numerator.multiply(lcm.divide(r.denominator))),
                lcm
        );
    }

    public BigRational subtract(byte n) {
        if (isZero()) {
            return of(n).negate();
        } else if (n == 0) {
            return this;
        }

        return subtract(of(n));
    }

    public BigRational subtract(short n) {
        if (isZero()) {
            return of(n).negate();
        } else if (n == 0) {
            return this;
        }

        return subtract(of(n));
    }

    public BigRational subtract(int n) {
        if (isZero()) {
            return of(n).negate();
        } else if (n == 0) {
            return this;
        }

        return subtract(of(n));
    }

    public BigRational subtract(long n) {
        if (isZero()) {
            return of(n).negate();
        } else if (n == 0) {
            return this;
        }

        return subtract(of(n));
    }

    public BigRational subtract(BigInteger n) {
        if (isZero()) {
            return of(n).negate();
        } else if (n.signum() == 0) {
            return this;
        }

        return subtract(of(n));
    }

    public BigRational subtract(float f) {
        if (isZero()) {
            return of(f).negate();
        } else if (f == 0) {
            return this;
        }

        return subtract(of(f));
    }

    public BigRational subtract(double d) {
        if (isZero()) {
            return of(d).negate();
        } else if (d == 0) {
            return this;
        }

        return subtract(of(d));
    }

    public BigRational subtract(BigDecimal d) {
        if (isZero()) {
            return of(d).negate();
        } else if (d.signum() == 0) {
            return this;
        }

        return subtract(of(d));
    }

    public BigRational subtract(BigRational r) {
        if (isZero()) {
            return r.negate();
        } else if (r.isZero()) {
            return this;
        }

        return add(r.negate());
    }

    public BigRational multiply(byte n) {
        if (isZero() || n == 0) {
            return ZERO;
        } else if (isOne()) {
            return of(n);
        } else if (n == 1) {
            return this;
        }

        return multiply(of(n));
    }

    public BigRational multiply(short n) {
        if (isZero() || n == 0) {
            return ZERO;
        } else if (isOne()) {
            return of(n);
        } else if (n == 1) {
            return this;
        }

        return multiply(of(n));
    }

    public BigRational multiply(int n) {
        if (isZero() || n == 0) {
            return ZERO;
        } else if (isOne()) {
            return of(n);
        } else if (n == 1) {
            return this;
        }

        return multiply(of(n));
    }

    public BigRational multiply(long n) {
        if (isZero() || n == 0) {
            return ZERO;
        } else if (isOne()) {
            return of(n);
        } else if (n == 1) {
            return this;
        }

        return multiply(of(n));
    }

    public BigRational multiply(BigInteger n) {
        if (isZero() || n.signum() == 0) {
            return ZERO;
        } else if (isOne()) {
            return of(n);
        } else if (n.equals(BigIntegerConstants.ONE)) {
            return this;
        } else if (isBigInteger() && numerator.equals(n)) {
            return squared();
        }

        return multiply(of(n));
    }

    public BigRational multiply(float f) {
        if (isZero() || f == 0) {
            return ZERO;
        } else if (isOne()) {
            return of(f);
        } else if (f == 1) {
            return this;
        }

        return multiply(of(f));
    }

    public BigRational multiply(double d) {
        if (isZero() || d == 0) {
            return ZERO;
        } else if (isOne()) {
            return of(d);
        } else if (d == 1) {
            return this;
        }

        return multiply(of(d));
    }

    public BigRational multiply(BigDecimal d) {
        if (isZero() || d.signum() == 0) {
            return ZERO;
        } else if (isOne()) {
            return of(d);
        } else if (d.equals(BigDecimalConstants.ONE)) {
            return this;
        }

        return multiply(of(d));
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

    @SuppressWarnings("Duplicates")
    public BigRational divide(byte n) {
        if (n == 0) {
            throw new ArithmeticException("divide by zero");
        } else if (n == 1) {
            return this;
        } else if (isZero()) {
            return ZERO;
        } else if (isOne()) {
            return ofInv(n);
        }

        return multiply(ofInv(n));
    }

    @SuppressWarnings("Duplicates")
    public BigRational divide(short n) {
        if (n == 0) {
            throw new ArithmeticException("divide by zero");
        } else if (n == 1) {
            return this;
        } else if (isZero()) {
            return ZERO;
        } else if (isOne()) {
            return ofInv(n);
        }

        return multiply(ofInv(n));
    }

    @SuppressWarnings("Duplicates")
    public BigRational divide(int n) {
        if (n == 0) {
            throw new ArithmeticException("divide by zero");
        } else if (n == 1) {
            return this;
        } else if (isZero()) {
            return ZERO;
        } else if (isOne()) {
            return ofInv(n);
        }

        return multiply(ofInv(n));
    }

    @SuppressWarnings("Duplicates")
    public BigRational divide(long n) {
        if (n == 0) {
            throw new ArithmeticException("divide by zero");
        } else if (n == 1) {
            return this;
        } else if (isZero()) {
            return ZERO;
        } else if (isOne()) {
            return ofInv(n);
        }

        return multiply(ofInv(n));
    }

    public BigRational divide(BigInteger n) {
        if (n.signum() == 0) {
            throw new ArithmeticException("divide by zero");
        } else if (n.equals(BigIntegerConstants.ONE)) {
            return this;
        } else if (isZero()) {
            return ZERO;
        } else if (isOne()) {
            return ofInv(n);
        }

        return multiply(ofInv(n));
    }

    @SuppressWarnings("Duplicates")
    public BigRational divide(float f) {
        if (f == 0) {
            throw new ArithmeticException("divide by zero");
        } else if (f == 1) {
            return this;
        } else if (isZero()) {
            return ZERO;
        } else if (isOne()) {
            return ofInv(f);
        }

        return multiply(ofInv(f));
    }

    @SuppressWarnings("Duplicates")
    public BigRational divide(double d) {
        if (d == 0) {
            throw new ArithmeticException("divide by zero");
        } else if (d == 1) {
            return this;
        } else if (isZero()) {
            return ZERO;
        } else if (isOne()) {
            return ofInv(d);
        }

        return multiply(ofInv(d));
    }

    public BigRational divide(BigDecimal d) {
        if (d.signum() == 0) {
            throw new ArithmeticException("divide by zero");
        } else if (d.equals(BigDecimalConstants.ONE)) {
            return this;
        } else if (isZero()) {
            return ZERO;
        } else if (isOne()) {
            return ofInv(d);
        }

        return multiply(ofInv(d));
    }

    public BigRational divide(BigRational r) {
        if (r.isZero()) {
            throw new ArithmeticException("divide by zero");
        } else if (r.isOne()) {
            return this;
        } else if (isZero()) {
            return ZERO;
        } else if (isOne()) {
            return r.inverse();
        }

        return multiply(r.inverse());
    }

    public BigRational squared() {
        if (sqCache == null) {
            sqCache = of(numerator.multiply(numerator), denominator.multiply(denominator));
            sqCache.sqrtCache = abs();

            BigRational neg = negate();
            neg.sqCache = sqCache;
        }

        return sqCache;
    }

    public BigRational sqrt() {
        if (sqrtCache == null) {
            sqrtCache = of(toBigDecimal().sqrt(BigDecimalMath.MC));
            sqrtCache.sqCache = this;
            sqrtCache.negate().sqCache = this;
        }

        return sqrtCache;
    }

    public BigRational pow(byte exponent) {
        return BigRationalMath.pow(this, exponent);
    }

    public BigRational pow(short exponent) {
        return BigRationalMath.pow(this, exponent);
    }

    public BigRational pow(int exponent) {
        return BigRationalMath.pow(this, exponent);
    }

    public BigRational pow(long exponent) {
        return BigRationalMath.pow(this, exponent);
    }

    public BigRational pow(BigInteger exponent) {
        return BigRationalMath.pow(this, exponent);
    }

    public BigRational pow(BigDecimal exponent) {
        return BigRationalMath.pow(this, exponent);
    }

    public BigRational pow(BigRational exponent) {
        return BigRationalMath.pow(this, exponent);
    }

    public BigRational exp() {
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

    public boolean isZero() {
        return numerator.signum() == 0;
    }

    public boolean isOne() {
        return equals(ONE);
    }

    public boolean isPositive() {
        return numerator.signum() > 0;
    }

    public boolean isNegative() {
        return numerator.signum() < 0;
    }

    public int sgn() {
        return numerator.signum();
    }

    public boolean isBigInteger() {
        return denominator.equals(BigIntegerConstants.ONE);
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

    public int intValueExact() {
        if (!isInt()) {
            throw new ArithmeticException();
        }

        return numerator.intValueExact();
    }

    @Override
    public long longValue() {
        return isLong() ? numerator.longValueExact() : toBigDecimal().longValue();
    }

    public long longValueExact() {
        if (!isLong()) {
            throw new ArithmeticException();
        }

        return numerator.longValueExact();
    }

    public BigInteger toBigInteger() {
        if (intCache == null) {
            if (isBigInteger()) {
                intCache = numerator;
            } else {
                intCache = toBigDecimal().toBigInteger();
            }
        }

        return intCache;
    }

    public BigInteger toBigIntegerExact() {
        if (!isBigInteger()) {
            throw new ArithmeticException();
        }

        return toBigInteger();
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
            if (isBigInteger()) {
                decimalCache = BigDecimalMath.of(toBigInteger());
            } else {
                decimalCache = BigDecimalMath.of(numerator).divide(BigDecimalMath.of(denominator), BigDecimalMath.MC);
            }
        }

        return decimalCache;
    }

    public String toRatioString() {
        return numerator + ":" + denominator;
    }

    @Override
    public int compareTo(BigRational r) {
        BigInteger lcm = BigIntegerMath.lcm(denominator, r.denominator);
        return numerator.multiply(lcm.divide(denominator))
                .compareTo(r.numerator.multiply(lcm.divide(r.denominator)));
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
        return isBigInteger() ? toBigInteger().toString() :
                numerator + "/" + denominator + " (" + toBigDecimal() + ")";
    }
}

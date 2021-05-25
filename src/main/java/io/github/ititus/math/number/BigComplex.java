package io.github.ititus.math.number;

import io.github.ititus.data.ObjectUtil;
import io.github.ititus.data.pair.Pair;
import io.github.ititus.math.vector.Vec2i;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.ititus.math.number.BigComplexConstants.*;

public final class BigComplex {

    private static final Pattern FULL_COMPLEX_PATTERN = Pattern.compile(
            "^(?<realsign>[+\\-]?)(?<real>[^+\\-]+)(?<imagsign>[+\\-])(?<imag>[^+\\-]*)[iIjJ]$"
    );

    private final BigRational real, imag;

    BigComplex(BigRational real, BigRational imag) {
        this.real = Objects.requireNonNull(real);
        this.imag = Objects.requireNonNull(imag);
    }

    public static BigComplex real(byte real) {
        return real(BigRational.of(real));
    }

    public static BigComplex real(short real) {
        return real(BigRational.of(real));
    }

    public static BigComplex real(int real) {
        return real(BigRational.of(real));
    }

    public static BigComplex real(long real) {
        return real(BigRational.of(real));
    }

    public static BigComplex real(BigInteger real) {
        return real(BigRational.of(real));
    }

    public static BigComplex real(float real) {
        return real(BigRational.of(real));
    }

    public static BigComplex real(double real) {
        return real(BigRational.of(real));
    }

    public static BigComplex real(BigDecimal real) {
        return real(BigRational.of(real));
    }

    public static BigComplex real(BigRational real) {
        return of(real, BigRationalConstants.ZERO);
    }

    public static BigComplex imag(byte imag) {
        return imag(BigRational.of(imag));
    }

    public static BigComplex imag(short imag) {
        return imag(BigRational.of(imag));
    }

    public static BigComplex imag(int imag) {
        return imag(BigRational.of(imag));
    }

    public static BigComplex imag(long imag) {
        return imag(BigRational.of(imag));
    }

    public static BigComplex imag(BigInteger imag) {
        return imag(BigRational.of(imag));
    }

    public static BigComplex imag(float imag) {
        return imag(BigRational.of(imag));
    }

    public static BigComplex imag(double imag) {
        return imag(BigRational.of(imag));
    }

    public static BigComplex imag(BigDecimal imag) {
        return imag(BigRational.of(imag));
    }

    public static BigComplex imag(BigRational imag) {
        return of(BigRationalConstants.ZERO, imag);
    }

    public static BigComplex of(Object o) {
        Objects.requireNonNull(o);
        if (o instanceof BigComplex) {
            return of((BigComplex) o);
        } else if (o instanceof String) {
            return of((String) o);
        } else if (o instanceof Vec2i) {
            return of((Vec2i) o);
        } else if (o instanceof Pair) {
            Pair<?, ?> p = (Pair<?, ?>) o;
            try {
                return of(BigRational.of(p.a()), BigRational.of(p.b()));
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(ObjectUtil.toString(o) + " cannot be converted to BigComplex", e);
            }
        } else if (o instanceof Iterable) {
            Iterator<?> it = ((Iterable<?>) o).iterator();
            if (it.hasNext()) {
                Object real = it.next();
                if (it.hasNext()) {
                    Object imag = it.next();
                    if (!it.hasNext()) {
                        try {
                            return of(BigRational.of(real), BigRational.of(imag));
                        } catch (RuntimeException e) {
                            throw new IllegalArgumentException(
                                    ObjectUtil.toString(o) + " cannot be converted to BigComplex", e);
                        }
                    }
                }
            }
        } else if (o.getClass().isArray()) {
            int length = Array.getLength(o);
            if (length == 2) {
                try {
                    BigRational real = BigRational.of(Array.get(o, 0));
                    BigRational imag = BigRational.of(Array.get(o, 1));
                    return of(real, imag);
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to BigComplex", e);
                }
            }
        } else if (o instanceof AtomicIntegerArray) {
            AtomicIntegerArray arr = (AtomicIntegerArray) o;
            if (arr.length() == 2) {
                return of(arr.get(0), arr.get(1));
            }
        } else if (o instanceof AtomicLongArray) {
            AtomicLongArray arr = (AtomicLongArray) o;
            if (arr.length() == 2) {
                return of(arr.get(0), arr.get(1));
            }
        } else if (o instanceof AtomicReferenceArray) {
            AtomicReferenceArray<?> arr = (AtomicReferenceArray<?>) o;
            if (arr.length() == 2) {
                Object real = arr.get(0);
                Object imag = arr.get(1);
                try {
                    return of(BigRational.of(real), BigRational.of(imag));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to BigRational", e);
                }
            }
        }

        try {
            BigRational real = BigRational.of(o);
            return real(real);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(ObjectUtil.toString(o) + " cannot be converted to BigComplex", e);
        }
    }

    public static BigComplex of(BigComplex z) {
        return Objects.requireNonNull(z);
    }

    public static BigComplex of(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        }

        s = s.replaceAll("[\\s_*]", "");
        if (s.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int length = s.length();
        char last = s.charAt(length - 1);
        if (last != 'i' && last != 'I' && last != 'j' && last != 'J') {
            return real(BigRational.of(s));
        }

        Matcher m = FULL_COMPLEX_PATTERN.matcher(s);
        if (!m.matches()) {
            String withoutI = s.substring(0, length - 1);
            if (withoutI.isEmpty() || withoutI.equals("+")) {
                return I;
            } else if (withoutI.equals("-")) {
                return MINUS_I;
            }

            return imag(BigRational.of(withoutI));
        }

        BigRational real = BigRational.of(m.group("real"));
        if (m.group("realsign").equals("-")) {
            real = real.negate();
        }

        String imagString = m.group("imag");
        BigRational imag;
        if (imagString.isEmpty()) {
            imag = BigRationalConstants.ONE;
        } else {
            imag = BigRational.of(imagString);
        }
        if (m.group("imagsign").equals("-")) {
            imag = imag.negate();
        }

        return of(real, imag);
    }

    public static BigComplex of(Vec2i v) {
        return of(v.x(), v.y());
    }

    public static BigComplex of(byte real, byte imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(short real, byte imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(int real, byte imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(long real, byte imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigInteger real, byte imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(float real, byte imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(double real, byte imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigDecimal real, byte imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigRational real, byte imag) {
        return of(real, BigRational.of(imag));
    }

    public static BigComplex of(int real, short imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(long real, short imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigInteger real, short imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(float real, short imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(double real, short imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigDecimal real, short imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigRational real, short imag) {
        return of(real, BigRational.of(imag));
    }

    public static BigComplex of(byte real, int imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(short real, int imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(int real, int imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(long real, int imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigInteger real, int imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(float real, int imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(double real, int imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigDecimal real, int imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigRational real, int imag) {
        return of(real, BigRational.of(imag));
    }

    public static BigComplex of(byte real, long imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(short real, long imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(int real, long imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(long real, long imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigInteger real, long imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(float real, long imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(double real, long imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigDecimal real, long imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigRational real, long imag) {
        return of(real, BigRational.of(imag));
    }

    public static BigComplex of(byte real, BigInteger imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(short real, BigInteger imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(int real, BigInteger imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(long real, BigInteger imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigInteger real, BigInteger imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(float real, BigInteger imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(double real, BigInteger imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigDecimal real, BigInteger imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigRational real, BigInteger imag) {
        return of(real, BigRational.of(imag));
    }

    public static BigComplex of(byte real, float imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(short real, float imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(int real, float imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(long real, float imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigInteger real, float imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(float real, float imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(double real, float imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigDecimal real, float imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigRational real, float imag) {
        return of(real, BigRational.of(imag));
    }

    public static BigComplex of(byte real, double imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(short real, double imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(int real, double imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(long real, double imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigInteger real, double imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(float real, double imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(double real, double imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigDecimal real, double imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigRational real, double imag) {
        return of(real, BigRational.of(imag));
    }

    public static BigComplex of(byte real, BigDecimal imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(short real, BigDecimal imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(int real, BigDecimal imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(long real, BigDecimal imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigInteger real, BigDecimal imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(float real, BigDecimal imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(double real, BigDecimal imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigDecimal real, BigDecimal imag) {
        return of(BigRational.of(real), BigRational.of(imag));
    }

    public static BigComplex of(BigRational real, BigDecimal imag) {
        return of(real, BigRational.of(imag));
    }

    public static BigComplex of(byte real, BigRational imag) {
        return of(BigRational.of(real), imag);
    }

    public static BigComplex of(short real, BigRational imag) {
        return of(BigRational.of(real), imag);
    }

    public static BigComplex of(int real, BigRational imag) {
        return of(BigRational.of(real), imag);
    }

    public static BigComplex of(long real, BigRational imag) {
        return of(BigRational.of(real), imag);
    }

    public static BigComplex of(BigInteger real, BigRational imag) {
        return of(BigRational.of(real), imag);
    }

    public static BigComplex of(float real, BigRational imag) {
        return of(BigRational.of(real), imag);
    }

    public static BigComplex of(double real, BigRational imag) {
        return of(BigRational.of(real), imag);
    }

    public static BigComplex of(BigDecimal real, BigRational imag) {
        return of(BigRational.of(real), imag);
    }

    public static BigComplex of(BigRational real, BigRational imag) {
        BigComplex z = new BigComplex(real, imag);
        if (z.equals(ZERO)) {
            return ZERO;
        } else if (z.equals(ONE)) {
            return ONE;
        } else if (z.equals(I)) {
            return I;
        } else if (z.equals(MINUS_ONE)) {
            return MINUS_ONE;
        } else if (z.equals(MINUS_I)) {
            return MINUS_I;
        }

        return z;
    }

    public BigRational getReal() {
        return real;
    }

    public BigRational getImag() {
        return imag;
    }

    public BigComplex withReal(byte real) {
        BigRational r = BigRational.of(real);
        if (this.real.equals(r)) {
            return this;
        }

        return of(r, imag);
    }

    public BigComplex withReal(short real) {
        BigRational r = BigRational.of(real);
        if (this.real.equals(r)) {
            return this;
        }

        return of(r, imag);
    }

    public BigComplex withReal(int real) {
        BigRational r = BigRational.of(real);
        if (this.real.equals(r)) {
            return this;
        }

        return of(r, imag);
    }

    public BigComplex withReal(long real) {
        BigRational r = BigRational.of(real);
        if (this.real.equals(r)) {
            return this;
        }

        return of(r, imag);
    }

    public BigComplex withReal(BigInteger real) {
        BigRational r = BigRational.of(real);
        if (this.real.equals(r)) {
            return this;
        }

        return of(r, imag);
    }

    public BigComplex withReal(float real) {
        BigRational r = BigRational.of(real);
        if (this.real.equals(r)) {
            return this;
        }

        return of(r, imag);
    }

    public BigComplex withReal(double real) {
        BigRational r = BigRational.of(real);
        if (this.real.equals(r)) {
            return this;
        }

        return of(r, imag);
    }

    public BigComplex withReal(BigDecimal real) {
        BigRational r = BigRational.of(real);
        if (this.real.equals(r)) {
            return this;
        }

        return of(r, imag);
    }

    public BigComplex withReal(BigRational real) {
        if (this.real.equals(real)) {
            return this;
        }

        return of(real, imag);
    }

    public BigComplex withImag(byte imag) {
        BigRational r = BigRational.of(imag);
        if (this.imag.equals(r)) {
            return this;
        }

        return of(real, r);
    }

    public BigComplex withImag(short imag) {
        BigRational r = BigRational.of(imag);
        if (this.imag.equals(r)) {
            return this;
        }

        return of(real, r);
    }

    public BigComplex withImag(int imag) {
        BigRational r = BigRational.of(imag);
        if (this.imag.equals(r)) {
            return this;
        }

        return of(real, r);
    }

    public BigComplex withImag(long imag) {
        BigRational r = BigRational.of(imag);
        if (this.imag.equals(r)) {
            return this;
        }

        return of(real, r);
    }

    public BigComplex withImag(BigInteger imag) {
        BigRational r = BigRational.of(imag);
        if (this.imag.equals(r)) {
            return this;
        }

        return of(real, r);
    }

    public BigComplex withImag(float imag) {
        BigRational r = BigRational.of(imag);
        if (this.imag.equals(r)) {
            return this;
        }

        return of(real, r);
    }

    public BigComplex withImag(double imag) {
        BigRational r = BigRational.of(imag);
        if (this.imag.equals(r)) {
            return this;
        }

        return of(real, r);
    }

    public BigComplex withImag(BigDecimal imag) {
        BigRational r = BigRational.of(imag);
        if (this.imag.equals(r)) {
            return this;
        }

        return of(real, r);
    }

    public BigComplex withImag(BigRational imag) {
        if (this.imag.equals(imag)) {
            return this;
        }

        return of(real, imag);
    }

    public BigRational absSquared() {
        return real.squared().add(imag.squared());
    }

    public BigRational abs() {
        if (imag.isZero()) {
            return real.abs();
        } else if (real.isZero()) {
            return imag.abs();
        }

        return absSquared().sqrt();
    }

    public BigRational angle() {
        return BigComplexMath.angle(this);
    }

    public BigComplex unit() {
        if (isZero()) {
            throw new ArithmeticException("zero has no complex number with unit length");
        }

        BigRational z = abs();
        return of(real.divide(z), imag.divide(z));
    }

    public BigComplex negate() {
        return of(real.negate(), imag.negate());
    }

    public BigComplex conjugate() {
        return of(real, imag.negate());
    }

    public BigComplex inverse() {
        if (isZero()) {
            throw new ArithmeticException("zero is not invertible");
        } else if (isOne()) {
            return ONE;
        }

        BigRational z = absSquared();
        return of(real.divide(z), imag.divide(z).negate());
    }

    public BigComplex add(byte n) {
        if (isZero()) {
            return of(n);
        } else if (n == 0) {
            return this;
        }

        return add(real(n));
    }

    public BigComplex add(short n) {
        if (isZero()) {
            return of(n);
        } else if (n == 0) {
            return this;
        }

        return add(real(n));
    }

    public BigComplex add(int n) {
        if (isZero()) {
            return of(n);
        } else if (n == 0) {
            return this;
        }

        return add(real(n));
    }

    public BigComplex add(long n) {
        if (isZero()) {
            return of(n);
        } else if (n == 0) {
            return this;
        }

        return add(real(n));
    }

    public BigComplex add(BigInteger n) {
        if (isZero()) {
            return of(n);
        } else if (n.signum() == 0) {
            return this;
        }

        return add(real(n));
    }

    public BigComplex add(float f) {
        if (isZero()) {
            return of(f);
        } else if (f == 0) {
            return this;
        }

        return add(real(f));
    }

    public BigComplex add(double d) {
        if (isZero()) {
            return of(d);
        } else if (d == 0) {
            return this;
        }

        return add(real(d));
    }

    public BigComplex add(BigDecimal d) {
        if (isZero()) {
            return of(d);
        } else if (d.signum() == 0) {
            return this;
        }

        return add(real(d));
    }

    public BigComplex add(BigRational r) {
        if (isZero()) {
            return of(r);
        } else if (r.isZero()) {
            return this;
        }

        return add(real(r));
    }

    public BigComplex add(BigComplex z) {
        if (isZero()) {
            return z;
        } else if (z.isZero()) {
            return this;
        }

        return of(real.add(z.real), imag.add(z.imag));
    }

    public BigComplex subtract(int n) {
        if (isZero()) {
            return of(n).negate();
        } else if (n == 0) {
            return this;
        }

        return subtract(real(n));
    }

    public BigComplex subtract(long n) {
        if (isZero()) {
            return of(n).negate();
        } else if (n == 0) {
            return this;
        }

        return subtract(real(n));
    }

    public BigComplex subtract(BigInteger n) {
        if (isZero()) {
            return of(n).negate();
        } else if (n.signum() == 0) {
            return this;
        }

        return subtract(real(n));
    }

    public BigComplex subtract(float f) {
        if (isZero()) {
            return of(f).negate();
        } else if (f == 0) {
            return this;
        }

        return subtract(real(f));
    }

    public BigComplex subtract(double d) {
        if (isZero()) {
            return of(d).negate();
        } else if (d == 0) {
            return this;
        }

        return subtract(real(d));
    }

    public BigComplex subtract(BigDecimal d) {
        if (isZero()) {
            return of(d).negate();
        } else if (d.signum() == 0) {
            return this;
        }

        return subtract(real(d));
    }

    public BigComplex subtract(BigRational r) {
        if (isZero()) {
            return of(r).negate();
        } else if (r.isZero()) {
            return this;
        }

        return subtract(real(r));
    }

    public BigComplex subtract(BigComplex z) {
        if (isZero()) {
            return z.negate();
        } else if (z.isZero()) {
            return this;
        }

        return add(z.negate());
    }

    public BigComplex multiply(int n) {
        return multiply(real(n));
    }

    public BigComplex multiply(long n) {
        return multiply(real(n));
    }

    public BigComplex multiply(BigInteger n) {
        return multiply(real(n));
    }

    public BigComplex multiply(float f) {
        return multiply(real(f));
    }

    public BigComplex multiply(double d) {
        return multiply(real(d));
    }

    public BigComplex multiply(BigDecimal d) {
        return multiply(real(d));
    }

    public BigComplex multiply(BigRational r) {
        return multiply(real(r));
    }

    public BigComplex multiply(BigComplex z) {
        return of(real.multiply(z.real).subtract(imag.multiply(z.imag)),
                real.multiply(z.imag).add(imag.multiply(z.real)));
    }

    public BigComplex divide(int n) {
        return divide(real(n));
    }

    public BigComplex divide(long n) {
        return divide(real(n));
    }

    public BigComplex divide(BigInteger n) {
        return divide(real(n));
    }

    public BigComplex divide(float f) {
        return divide(real(f));
    }

    public BigComplex divide(double d) {
        return divide(real(d));
    }

    public BigComplex divide(BigDecimal d) {
        return divide(real(d));
    }

    public BigComplex divide(BigRational r) {
        return divide(real(r));
    }

    public BigComplex divide(BigComplex z) {
        return multiply(z.inverse());
    }

    public BigComplex squared() {
        return multiply(this);
    }

    public BigComplex sqrt() {
        return BigComplexMath.sqrt(this);
    }

    public BigComplex pow(int exponent) {
        return pow(BigIntegerMath.of(exponent));
    }

    public BigComplex pow(long exponent) {
        return pow(BigIntegerMath.of(exponent));
    }

    public BigComplex pow(BigInteger exponent) {
        return BigComplexMath.pow(this, exponent);
    }

    public BigComplex pow(float exponent) {
        return pow(BigRational.of(exponent));
    }

    public BigComplex pow(double exponent) {
        return pow(BigRational.of(exponent));
    }

    public BigComplex pow(BigDecimal exponent) {
        return pow(BigRational.of(exponent));
    }

    public BigComplex pow(BigRational exponent) {
        return BigComplexMath.pow(this, exponent);
    }

    public BigComplex pow(BigComplex exponent) {
        return BigComplexMath.pow(this, exponent);
    }

    public BigComplex exp() {
        return BigComplexMath.exp(this);
    }

    public BigComplex ln() {
        return BigComplexMath.ln(this);
    }

    public BigComplex log(BigComplex base) {
        return BigComplexMath.log(base, this);
    }

    public BigComplex sin() {
        return BigComplexMath.sin(this);
    }

    public BigComplex cos() {
        return BigComplexMath.cos(this);
    }

    public BigComplex tan() {
        return BigComplexMath.tan(this);
    }

    public BigComplex cot() {
        return BigComplexMath.cot(this);
    }

    public BigComplex sec() {
        return BigComplexMath.sec(this);
    }

    public BigComplex csc() {
        return BigComplexMath.csc(this);
    }

    public BigComplex asin() {
        return BigComplexMath.asin(this);
    }

    public BigComplex acos() {
        return BigComplexMath.acos(this);
    }

    public BigComplex atan() {
        return BigComplexMath.atan(this);
    }

    public BigComplex acot() {
        return BigComplexMath.acot(this);
    }

    public BigComplex asec() {
        return BigComplexMath.asec(this);
    }

    public BigComplex acsc() {
        return BigComplexMath.acsc(this);
    }

    public BigComplex sinh() {
        return BigComplexMath.sinh(this);
    }

    public BigComplex cosh() {
        return BigComplexMath.cosh(this);
    }

    public BigComplex tanh() {
        return BigComplexMath.tanh(this);
    }

    public BigComplex coth() {
        return BigComplexMath.coth(this);
    }

    public BigComplex sech() {
        return BigComplexMath.sech(this);
    }

    public BigComplex csch() {
        return BigComplexMath.csch(this);
    }

    public BigComplex asinh() {
        return BigComplexMath.asinh(this);
    }

    public BigComplex acosh() {
        return BigComplexMath.acosh(this);
    }

    public BigComplex atanh() {
        return BigComplexMath.atanh(this);
    }

    public BigComplex acoth() {
        return BigComplexMath.acoth(this);
    }

    public BigComplex asech() {
        return BigComplexMath.asech(this);
    }

    public BigComplex acsch() {
        return BigComplexMath.acsch(this);
    }

    public boolean isZero() {
        return real.isZero() && imag.isZero();
    }

    public boolean isOne() {
        return real.isOne() && imag.isZero();
    }

    public boolean isReal() {
        return imag.isZero();
    }

    public boolean isImaginary() {
        return real.isZero() && !imag.isZero();
    }

    public boolean isComplex() {
        return !real.isZero() && !imag.isZero();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BigComplex z = (BigComplex) o;
        return real.equals(z.real) && imag.equals(z.imag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imag);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();

        if (!real.isZero()) {
            b.append(real);
        }
        if (!imag.isZero()) {
            if (!real.isZero()) {
                b.append(' ');
                b.append(imag.isNegative() ? '-' : '+');
                b.append(' ');
            } else if (imag.isNegative()) {
                b.append('-');
            }
            b.append('i');
            BigRational imag = this.imag.abs();
            if (!imag.isOne()) {
                b.append('*').append(imag);
            }
        }

        return b.length() == 0 ? "0" : b.toString();
    }
}

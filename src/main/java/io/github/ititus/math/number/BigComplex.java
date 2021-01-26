package io.github.ititus.math.number;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.ititus.math.number.BigComplexConstants.*;

public final class BigComplex {

    private static final Pattern FULL_COMPLEX_PATTERN = Pattern.compile(
            "^(?<realsign>[+\\-]?)(?<real>[^+\\-]+)(?<imagsign>[+\\-])(?<imag>[^+\\-]*)[ij]$"
    );

    private final BigRational real, imag;

    BigComplex(BigRational real, BigRational imag) {
        this.real = Objects.requireNonNull(real);
        this.imag = Objects.requireNonNull(imag);
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

    public static BigComplex real(BigRational real) {
        return of(real, BigRationalConstants.ZERO);
    }

    public static BigComplex real(BigDecimal real) {
        return of(BigRational.of(real), BigRationalConstants.ZERO);
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

    public static BigComplex imag(BigRational imag) {
        return of(BigRationalConstants.ZERO, imag);
    }

    public static BigComplex imag(BigDecimal imag) {
        return of(BigRationalConstants.ZERO, BigRational.of(imag));
    }

    public static BigComplex of(Object o) {
        if (o instanceof BigComplex) {
            return (BigComplex) o;
        } else if (o instanceof String) {
            return of((String) o);
        }

        throw new IllegalArgumentException();
    }

    public static BigComplex of(BigComplex z) {
        return z;
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
        if (last != 'i' && last != 'j') {
            return real(BigRational.of(s));
        }

        Matcher m = FULL_COMPLEX_PATTERN.matcher(s);
        if (!m.matches()) {
            return imag(BigRational.of(s.substring(0, length - 1)));
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
        return absSquared().sqrt();
    }

    public BigComplex unit() {
        BigRational z = abs();
        return of(real.divide(z), imag.divide(z));
    }

    public BigRational angle() {
        return BigComplexMath.angle(this);
    }

    public BigComplex negate() {
        return of(real.negate(), imag.negate());
    }

    public BigComplex conjugate() {
        return of(real, imag.negate());
    }

    public BigComplex inverse() {
        if (isZero()) {
            throw new ArithmeticException();
        }

        BigRational z = absSquared();
        return of(real.divide(z), imag.divide(z).negate());
    }

    public BigComplex add(BigComplex z) {
        return of(real.add(z.real), imag.add(z.imag));
    }

    public BigComplex subtract(BigComplex z) {
        return add(z.negate());
    }

    public BigComplex multiply(BigComplex z) {
        return of(real.multiply(z.real).subtract(imag.multiply(z.imag)),
                real.multiply(z.imag).add(imag.multiply(z.real)));
    }

    public BigComplex sqrt() {
        return BigComplexMath.sqrt(this);
    }

    public BigComplex divide(BigComplex z) {
        return multiply(z.inverse());
    }

    public BigComplex squared() {
        return multiply(this);
    }

    public BigComplex exp() {
        return BigComplexMath.exp(this);
    }

    public BigComplex ln() {
        return BigComplexMath.ln(this);
    }

    public BigComplex pow(int exponent) {
        return pow(BigComplex.real(exponent));
    }

    public BigComplex pow(BigComplex exponent) {
        return BigComplexMath.pow(this, exponent);
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

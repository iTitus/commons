package io.github.ititus.math.number;

import java.math.BigInteger;
import java.util.Objects;

public final class BigComplex {

    public static final BigComplex ZERO = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.ZERO);
    public static final BigComplex ONE = new BigComplex(BigRationalConstants.ONE, BigRationalConstants.ZERO);
    public static final BigComplex MINUS_ONE = new BigComplex(BigRationalConstants.MINUS_ONE,
            BigRationalConstants.ZERO);
    public static final BigComplex I = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.ONE);
    public static final BigComplex MINUS_I = new BigComplex(BigRationalConstants.ZERO, BigRationalConstants.MINUS_ONE);

    private final BigRational real, imag;

    private BigComplex(BigRational real, BigRational imag) {
        this.real = Objects.requireNonNull(real);
        this.imag = Objects.requireNonNull(imag);
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

    public BigRational getReal() {
        return real;
    }

    public BigRational getImag() {
        return imag;
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
        return of(real.divide(z), imag.divide(z));
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

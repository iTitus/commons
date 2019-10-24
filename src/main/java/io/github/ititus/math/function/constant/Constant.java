package io.github.ititus.math.function.constant;

import io.github.ititus.math.function.ComplexFunction;
import io.github.ititus.math.number.BigComplex;
import io.github.ititus.math.number.BigIntegerMath;
import io.github.ititus.math.number.BigRational;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public final class Constant extends ComplexFunction {

    private static final Constant ZERO = new Constant(BigComplex.ZERO);
    private static final Constant ONE = new Constant(BigComplex.ONE);
    private static final Constant MINUS_ONE = new Constant(BigComplex.MINUS_ONE);

    private final BigComplex complex;

    private Constant(BigComplex complex) {
        this.complex = complex;
    }

    public static Constant zero() {
        return ZERO;
    }

    public static Constant one() {
        return ONE;
    }

    public static Constant minusOne() {
        return MINUS_ONE;
    }

    public static Constant of(int n) {
        return of(BigRational.of(n));
    }

    public static Constant of(BigInteger n) {
        return of(BigRational.of(n));
    }

    public static Constant of(BigRational r) {
        return of(BigComplex.real(r));
    }

    public static Constant of(BigComplex complex) {
        Objects.requireNonNull(complex);

        if (complex.equals(BigComplex.ZERO)) {
            return ZERO;
        } else if (complex.equals(BigComplex.ONE)) {
            return ONE;
        } else if (complex.equals(BigComplex.MINUS_ONE)) {
            return MINUS_ONE;
        }

        return new Constant(complex);
    }

    public static ComplexFunction binomial(int n, int k) {
        return of(BigIntegerMath.binomial(BigIntegerMath.of(n), BigIntegerMath.of(k)));
    }

    public static ComplexFunction binomial(BigInteger n, BigInteger k) {
        return of(BigIntegerMath.binomial(n, k));
    }

    public static ComplexFunction multinomial(int... n) {
        return of(BigIntegerMath.multinomial(Arrays.stream(n).mapToObj(BigIntegerMath::of).toArray(BigInteger[]::new)));
    }

    public static ComplexFunction multinomial(long... n) {
        return of(BigIntegerMath.multinomial(Arrays.stream(n).mapToObj(BigIntegerMath::of).toArray(BigInteger[]::new)));
    }

    public static ComplexFunction multinomial(BigInteger... n) {
        return of(BigIntegerMath.multinomial(n));
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return complex;
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        return ZERO;
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public BigComplex getConstant() {
        return complex;
    }


    @Override
    protected String toString(boolean inner) {
        boolean b = inner && (complex.isComplex() || (complex.isReal() && !complex.getReal().isBigInteger()) || (complex.isImaginary() && !complex.getImag().isBigInteger()));
        return (b ? "[" : "") + complex + (b ? "]" : "");
    }

    @Override
    protected boolean equals0(ComplexFunction f) {
        if (f.getClass() != Constant.class) {
            return false;
        }
        Constant c = (Constant) f;
        return complex.equals(c.complex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Constant.class, complex);
    }
}

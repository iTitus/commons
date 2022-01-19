package io.github.ititus.commons.math.function.constant;

import io.github.ititus.commons.math.function.ComplexFunction;
import io.github.ititus.commons.math.number.BigComplex;
import io.github.ititus.commons.math.number.BigComplexConstants;
import io.github.ititus.commons.math.number.BigIntegerMath;
import io.github.ititus.commons.math.number.BigRational;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public final class Constant extends ComplexFunction {

    private static final Constant ZERO = new Constant(BigComplexConstants.ZERO);
    private static final Constant ONE = new Constant(BigComplexConstants.ONE);
    private static final Constant MINUS_ONE = new Constant(BigComplexConstants.MINUS_ONE);

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

    public static Constant of(long n) {
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

        if (complex.equals(BigComplexConstants.ZERO)) {
            return ZERO;
        } else if (complex.equals(BigComplexConstants.ONE)) {
            return ONE;
        } else if (complex.equals(BigComplexConstants.MINUS_ONE)) {
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
    public String toString(boolean inner) {
        boolean brackets = inner && (
                complex.isComplex()
                        || (complex.isReal() && !complex.getReal().isBigInteger())
                        || (complex.isImaginary() && !complex.getImag().isBigInteger())
        );
        return (brackets ? "[" : "") + complex + (brackets ? "]" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Constant)) {
            return false;
        }
        Constant c = (Constant) o;
        return complex.equals(c.complex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Constant.class, complex);
    }
}

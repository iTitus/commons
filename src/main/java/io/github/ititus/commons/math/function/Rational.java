package io.github.ititus.commons.math.function;

import io.github.ititus.commons.math.function.constant.Constant;
import io.github.ititus.commons.math.number.BigComplex;

import java.util.Objects;

public final class Rational extends ComplexFunction {

    private final ComplexFunction numerator, denominator;

    private Rational(ComplexFunction numerator, ComplexFunction denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static ComplexFunction of(ComplexFunction denominator) {
        if (denominator instanceof Rational) {
            Rational r = (Rational) denominator;
            return of(r.denominator, r.numerator);
        }

        return of(Constant.one(), denominator);
    }

    public static ComplexFunction of(ComplexFunction numerator, ComplexFunction denominator) {
        if (denominator.isZero()) {
            throw new ArithmeticException();
        } else if (denominator.isOne()) {
            return numerator;
        } else if (numerator.isConstant() && denominator.isConstant()) {
            return Constant.of(numerator.getConstant().divide(denominator.getConstant()));
        }

        return new Rational(numerator, denominator);
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return numerator.evaluate(z).divide(denominator.evaluate(z));
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        ComplexFunction num = Sum.of(Product.of(numerator.derivative(), denominator), Product.of(Constant.minusOne(),
                numerator, denominator.derivative()));
        ComplexFunction denom = Power.of(denominator, 2);
        return Rational.of(num, denom);
    }

    @Override
    public String toString(boolean inner) {
        String s = inner ? "(" : "";
        s += numerator.toString(true) + " / " + denominator.toString(true);
        return s + (inner ? ")" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Rational)) {
            return false;
        }
        Rational r = (Rational) o;
        return numerator.equals(r.numerator) && denominator.equals(r.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Rational.class, numerator, denominator);
    }
}

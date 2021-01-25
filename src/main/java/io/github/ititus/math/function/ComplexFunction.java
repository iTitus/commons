package io.github.ititus.math.function;

import io.github.ititus.math.number.BigComplex;

import static io.github.ititus.math.number.BigComplexConstants.ONE;
import static io.github.ititus.math.number.BigComplexConstants.ZERO;

public abstract class ComplexFunction {

    protected static final String VAR = "z";

    protected ComplexFunction() {
    }

    public abstract BigComplex evaluate(BigComplex z);

    public ComplexFunction derivative() {
        return derivative0(1);
    }

    public ComplexFunction derivative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n == 0) {
            return this;
        }
        return derivative0(n);
    }

    protected abstract ComplexFunction derivative0(int n);

    public boolean isConstant() {
        return false;
    }

    public BigComplex getConstant() {
        throw new UnsupportedOperationException();
    }

    public boolean isZero() {
        return isConstant() && getConstant().equals(ZERO);
    }

    public boolean isOne() {
        return isConstant() && getConstant().equals(ONE);
    }

    public boolean isIdentity() {
        return false;
    }

    protected abstract String toString(boolean inner);

    @Override
    public String toString() {
        return toString(false);
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}

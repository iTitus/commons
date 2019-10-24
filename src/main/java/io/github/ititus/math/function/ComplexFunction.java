package io.github.ititus.math.function;

import io.github.ititus.math.number.BigComplex;

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
        return isConstant() && getConstant().equals(BigComplex.ZERO);
    }

    public boolean isOne() {
        return isConstant() && getConstant().equals(BigComplex.ONE);
    }

    public boolean isIdentity() {
        return false;
    }

    protected abstract String toString(boolean inner);

    @Override
    public String toString() {
        return toString(false);
    }

    protected abstract boolean equals0(ComplexFunction f);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComplexFunction)) {
            return false;
        }
        return equals0((ComplexFunction) o);
    }

    @Override
    public abstract int hashCode();
}

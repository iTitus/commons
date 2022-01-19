package io.github.ititus.commons.math.function;

import io.github.ititus.commons.math.expression.Variable;
import io.github.ititus.commons.math.number.BigComplex;

import static io.github.ititus.commons.math.number.BigComplexConstants.ONE;
import static io.github.ititus.commons.math.number.BigComplexConstants.ZERO;

public abstract class ComplexFunction {

    protected static final Variable VAR = Variable.z();
    protected static final String VAR_NAME = VAR.getName();

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

    /**
     * Calculate the n-th derivative with n&gt;=1.
     *
     * @param n degree of derivative, &gt;= 1
     * @return n-th derivative
     */
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

    public abstract String toString(boolean inner);

    @Override
    public final String toString() {
        return toString(false);
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

}

package io.github.ititus.commons.math.function;

import io.github.ititus.commons.math.function.constant.Constant;
import io.github.ititus.commons.math.number.BigComplex;

public final class Identity extends ComplexFunction {

    private static final Identity IDENTITY = new Identity();

    private Identity() {
    }

    public static ComplexFunction get() {
        return IDENTITY;
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return z;
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        return n == 1 ? Constant.one() : Constant.zero();
    }

    @Override
    public boolean isIdentity() {
        return true;
    }

    @Override
    public String toString(boolean inner) {
        return VAR_NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o instanceof Identity;
    }

    @Override
    public int hashCode() {
        return Identity.class.hashCode();
    }
}

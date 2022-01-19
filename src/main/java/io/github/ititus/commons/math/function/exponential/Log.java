package io.github.ititus.commons.math.function.exponential;

import io.github.ititus.commons.math.function.ComplexFunction;
import io.github.ititus.commons.math.function.Identity;
import io.github.ititus.commons.math.function.Rational;
import io.github.ititus.commons.math.function.constant.Constant;
import io.github.ititus.commons.math.number.BigComplex;

import java.util.Objects;

public final class Log extends ComplexFunction {

    private static final Log IDENTITY_LOG = new Log(Identity.get());

    private final ComplexFunction f;

    private Log(ComplexFunction f) {
        this.f = f;
    }

    public static ComplexFunction of() {
        return of(Identity.get());
    }

    public static ComplexFunction of(ComplexFunction f) {
        if (f.isConstant()) {
            return Constant.of(f.getConstant().ln());
        } else if (f.isIdentity()) {
            return IDENTITY_LOG;
        }

        return new Log(f);
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return f.evaluate(z).ln();
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        return Rational.of(Identity.get()).derivative(n - 1);
    }

    @Override
    public String toString(boolean inner) {
        return "ln(" + f + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Log)) {
            return false;
        }
        Log l = (Log) o;
        return f.equals(l.f);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Log.class, f);
    }
}

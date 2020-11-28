package io.github.ititus.math.function.exponential;

import io.github.ititus.math.function.ComplexFunction;
import io.github.ititus.math.function.Identity;
import io.github.ititus.math.function.Product;
import io.github.ititus.math.function.constant.Constant;
import io.github.ititus.math.number.BigComplex;
import io.github.ititus.math.number.BigComplexMath;

import java.util.Objects;

public final class Exp extends ComplexFunction {

    private static final Exp IDENTITY_EXP = new Exp(Identity.get());

    private final ComplexFunction f;

    private Exp(ComplexFunction f) {
        this.f = f;
    }

    public static ComplexFunction of() {
        return of(Identity.get());
    }

    public static ComplexFunction of(ComplexFunction f) {
        if (f.isConstant()) {
            return Constant.of(BigComplexMath.exp(f.getConstant()));
        } else if (f.isIdentity()) {
            return IDENTITY_EXP;
        }

        return new Exp(f);
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return BigComplexMath.exp(f.evaluate(z));
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        if (f.isIdentity()) {
            return this;
        }

        return Product.of(f.derivative(), this).derivative(n - 1);
    }

    @Override
    protected String toString(boolean inner) {
        return "exp(" + f + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Exp)) {
            return false;
        }
        Exp e = (Exp) o;
        return f.equals(e.f);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Exp.class, f);
    }
}

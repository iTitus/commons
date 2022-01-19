package io.github.ititus.commons.math.function.hyperbolic;

import io.github.ititus.commons.math.function.ComplexFunction;
import io.github.ititus.commons.math.function.Identity;
import io.github.ititus.commons.math.function.Product;
import io.github.ititus.commons.math.function.constant.Constant;
import io.github.ititus.commons.math.number.BigComplex;
import io.github.ititus.commons.math.number.BigComplexMath;

import java.util.Objects;

public final class Cosh extends ComplexFunction {

    private static final Cosh IDENTITY_COSH = new Cosh(Identity.get());

    private final ComplexFunction f;

    private Cosh(ComplexFunction f) {
        this.f = f;
    }

    public static ComplexFunction of() {
        return of(Identity.get());
    }

    public static ComplexFunction of(ComplexFunction f) {
        if (f.isConstant()) {
            return Constant.of(BigComplexMath.cosh(f.getConstant()));
        } else if (f.isIdentity()) {
            return IDENTITY_COSH;
        }

        return new Cosh(f);
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return BigComplexMath.cosh(f.evaluate(z));
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        ComplexFunction d;
        switch (n % 2) {
            case 0:
                d = this;
                break;
            case 1:
                d = Sinh.of(f);
                break;
            default:
                throw new RuntimeException();
        }

        return f.isIdentity() ? d : Product.of(f.derivative(), d).derivative(n - 1);
    }

    @Override
    public String toString(boolean inner) {
        return "cosh(" + f + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Cosh)) {
            return false;
        }
        Cosh s = (Cosh) o;
        return f.equals(s.f);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Cosh.class, f);
    }
}

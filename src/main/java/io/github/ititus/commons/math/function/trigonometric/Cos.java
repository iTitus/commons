package io.github.ititus.commons.math.function.trigonometric;

import io.github.ititus.commons.math.function.ComplexFunction;
import io.github.ititus.commons.math.function.Identity;
import io.github.ititus.commons.math.function.Product;
import io.github.ititus.commons.math.function.constant.Constant;
import io.github.ititus.commons.math.number.BigComplex;
import io.github.ititus.commons.math.number.BigComplexMath;

import java.util.Objects;

public final class Cos extends ComplexFunction {

    private static final Cos IDENTITY_COS = new Cos(Identity.get());

    private final ComplexFunction f;

    private Cos(ComplexFunction f) {
        this.f = f;
    }

    public static ComplexFunction of() {
        return of(Identity.get());
    }

    public static ComplexFunction of(ComplexFunction f) {
        if (f.isConstant()) {
            return Constant.of(BigComplexMath.cos(f.getConstant()));
        } else if (f.isIdentity()) {
            return IDENTITY_COS;
        }

        return new Cos(f);
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return BigComplexMath.cos(f.evaluate(z));
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        ComplexFunction d;
        switch (n % 4) {
            case 0:
                d = this;
                break;
            case 1:
                d = Product.of(Constant.minusOne(), Sin.of(f));
                break;
            case 2:
                d = Product.of(Constant.minusOne(), this);
                break;
            case 3:
                d = Sin.of(f);
                break;
            default:
                throw new RuntimeException();
        }

        return f.isIdentity() ? d : Product.of(f.derivative(), d).derivative(n - 1);
    }

    @Override
    public String toString(boolean inner) {
        return "cos(" + f + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Cos)) {
            return false;
        }
        Cos s = (Cos) o;
        return f.equals(s.f);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Cos.class, f);
    }
}

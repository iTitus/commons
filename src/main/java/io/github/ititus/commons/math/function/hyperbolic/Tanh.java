package io.github.ititus.commons.math.function.hyperbolic;

import io.github.ititus.commons.math.function.*;
import io.github.ititus.commons.math.function.constant.Constant;
import io.github.ititus.commons.math.number.BigComplex;
import io.github.ititus.commons.math.number.BigComplexMath;

import java.util.Objects;

public final class Tanh extends ComplexFunction {

    private static final Tanh IDENTITY_TANH = new Tanh(Identity.get());

    private final ComplexFunction f;

    private Tanh(ComplexFunction f) {
        this.f = f;
    }

    public static ComplexFunction of() {
        return of(Identity.get());
    }

    public static ComplexFunction of(ComplexFunction f) {
        if (f.isConstant()) {
            return Constant.of(BigComplexMath.tanh(f.getConstant()));
        } else if (f.isIdentity()) {
            return IDENTITY_TANH;
        }

        return new Tanh(f);
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return BigComplexMath.tanh(f.evaluate(z));
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        return Product.of(f.derivative(), Sum.of(Constant.one(), Product.of(Constant.minusOne(), Power.of(this, 2)))).derivative(n - 1);
    }

    @Override
    public String toString(boolean inner) {
        return "tanh(" + f + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Tanh)) {
            return false;
        }
        Tanh s = (Tanh) o;
        return f.equals(s.f);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Tanh.class, f);
    }
}

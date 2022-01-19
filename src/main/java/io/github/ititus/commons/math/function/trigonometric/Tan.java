package io.github.ititus.commons.math.function.trigonometric;

import io.github.ititus.commons.math.function.*;
import io.github.ititus.commons.math.function.constant.Constant;
import io.github.ititus.commons.math.number.BigComplex;
import io.github.ititus.commons.math.number.BigComplexMath;

import java.util.Objects;

public final class Tan extends ComplexFunction {

    private static final Tan IDENTITY_TAN = new Tan(Identity.get());

    private final ComplexFunction f;

    private Tan(ComplexFunction f) {
        this.f = f;
    }

    public static ComplexFunction of() {
        return of(Identity.get());
    }

    public static ComplexFunction of(ComplexFunction f) {
        if (f.isConstant()) {
            return Constant.of(BigComplexMath.tan(f.getConstant()));
        } else if (f.isIdentity()) {
            return IDENTITY_TAN;
        }

        return new Tan(f);
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return BigComplexMath.tan(f.evaluate(z));
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        return Product.of(f.derivative(), Sum.of(Constant.one(), Power.of(this, 2))).derivative(n - 1);
    }

    @Override
    public String toString(boolean inner) {
        return "tan(" + f + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Tan)) {
            return false;
        }
        Tan s = (Tan) o;
        return f.equals(s.f);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Tan.class, f);
    }
}

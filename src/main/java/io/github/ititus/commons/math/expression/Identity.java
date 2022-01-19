package io.github.ititus.commons.math.expression;

import io.github.ititus.commons.math.number.BigComplex;

import java.util.Objects;

public final class Identity extends Expression {

    private final Variable var;

    private Identity(Variable var) {
        this.var = var;
    }

    public static Expression of(Variable var) {
        return new Identity(var);
    }

    @Override
    public BigComplex evaluate(ComplexEvaluationContext ctx) {
        return ctx.getValue(var);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Identity)) {
            return false;
        }
        Identity identity = (Identity) o;
        return var.equals(identity.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Identity.class, var);
    }

    @Override
    public String toString(boolean inner) {
        return var.getName();
    }
}

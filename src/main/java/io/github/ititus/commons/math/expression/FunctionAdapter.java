package io.github.ititus.commons.math.expression;

import io.github.ititus.commons.math.function.ComplexFunction;
import io.github.ititus.commons.math.number.BigComplex;

import java.util.Objects;

public final class FunctionAdapter extends Expression {

    private final ComplexFunction f;
    private final Variable var;

    public FunctionAdapter(ComplexFunction f, Variable var) {
        this.f = f;
        this.var = var;
    }

    @Override
    public BigComplex evaluate(ComplexEvaluationContext ctx) {
        return f.evaluate(ctx.getValue(var));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof FunctionAdapter)) {
            return false;
        }
        FunctionAdapter that = (FunctionAdapter) o;
        return f.equals(that.f) && var.equals(that.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FunctionAdapter.class, f, var);
    }

    @Override
    public String toString(boolean inner) {
        return f.toString(inner);
    }
}

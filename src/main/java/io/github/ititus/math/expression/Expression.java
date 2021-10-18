package io.github.ititus.math.expression;

import io.github.ititus.math.number.BigComplex;

public abstract class Expression {

    public abstract BigComplex evaluate(ComplexEvaluationContext ctx);

    protected abstract String toString(boolean inner);

    @Override
    public final String toString() {
        return toString(false);
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

}

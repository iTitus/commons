package io.github.ititus.commons.math.expression;

import io.github.ititus.commons.math.number.BigComplex;

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

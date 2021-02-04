package io.github.ititus.math.expression;

import io.github.ititus.math.function.ComplexFunction;
import io.github.ititus.math.number.BigComplex;

public final class FunctionAdapter implements Expression {

    private final ComplexFunction f;
    private final Variable var;

    public FunctionAdapter(ComplexFunction f, Variable var) {
        this.f = f;
        this.var = var;
    }

    @Override
    public BigComplex evaluate(EvaluationContext ctx) {
        return f.evaluate(ctx.getValue(var));
    }
}

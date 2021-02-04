package io.github.ititus.math.expression;

import io.github.ititus.math.number.BigComplex;

public interface Expression {

    BigComplex evaluate(EvaluationContext ctx);

}

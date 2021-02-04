package io.github.ititus.math.expression;

import io.github.ititus.math.number.BigComplex;

import java.util.*;

public final class EvaluationContext {

    private static final EvaluationContext EMPTY = new EvaluationContext(Map.of());

    private final Map<Variable, BigComplex> variables;

    private EvaluationContext(Map<Variable, BigComplex> variables) {
        this.variables = Map.copyOf(variables);
    }

    public static EvaluationContext empty() {
        return EMPTY;
    }

    public static EvaluationContext of(Variable var, BigComplex val) {
        return empty().withValue(var, val);
    }

    public Optional<BigComplex> getOptValue(Variable var) {
        return Optional.ofNullable(variables.get(var));
    }

    public BigComplex getValue(Variable var) {
        return getOptValue(var).orElseThrow(() -> new NoSuchElementException("no value for variable " + var));
    }

    public EvaluationContext withValue(Variable var, BigComplex val) {
        return withValue(var, val, ValueMode.CREATE);
    }

    public EvaluationContext withValue(Variable var, BigComplex val, ValueMode mode) {
        Objects.requireNonNull(var);
        Objects.requireNonNull(val);
        Objects.requireNonNull(mode);
        if (mode == ValueMode.CREATE && variables.containsKey(var)) {
            throw new IllegalArgumentException("cannot overwrite existing variable " + var);
        }

        Map<Variable, BigComplex> variables = new HashMap<>(this.variables);
        variables.put(var, val);
        return new EvaluationContext(variables);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof EvaluationContext)) {
            return false;
        }

        EvaluationContext that = (EvaluationContext) o;
        return variables.equals(that.variables);
    }

    @Override
    public int hashCode() {
        return variables.hashCode();
    }

    public enum ValueMode {
        CREATE,
        OVERWRITE,
        CREATE_OR_OVERWRITE
    }
}

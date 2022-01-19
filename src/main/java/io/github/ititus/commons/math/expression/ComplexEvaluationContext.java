package io.github.ititus.commons.math.expression;

import io.github.ititus.commons.math.number.BigComplex;

import java.util.*;

public final class ComplexEvaluationContext {

    private static final ComplexEvaluationContext EMPTY = new ComplexEvaluationContext(Map.of());

    private final Map<Variable, BigComplex> variables;

    private ComplexEvaluationContext(Map<Variable, BigComplex> variables) {
        this.variables = variables.isEmpty() ? Map.of() : Map.copyOf(variables);
    }

    public static ComplexEvaluationContext empty() {
        return EMPTY;
    }

    public static ComplexEvaluationContext of(Variable var, BigComplex val) {
        return empty().withValue(var, val);
    }

    public Optional<BigComplex> getOptValue(Variable var) {
        return Optional.ofNullable(variables.get(var));
    }

    public BigComplex getValue(Variable var) {
        return getOptValue(var).orElseThrow(() -> new NoSuchElementException("no value for variable " + var));
    }

    public ComplexEvaluationContext withValue(Variable var, BigComplex val) {
        return withValue(var, val, ValueMode.CREATE);
    }

    public ComplexEvaluationContext withValue(Variable var, BigComplex val, ValueMode mode) {
        Objects.requireNonNull(var);
        Objects.requireNonNull(val);
        Objects.requireNonNull(mode);
        if (mode == ValueMode.CREATE && variables.containsKey(var)) {
            throw new IllegalArgumentException("cannot overwrite existing variable " + var);
        }

        Map<Variable, BigComplex> variables;
        if (this.variables.isEmpty()) {
            variables = Map.of(var, val);
        } else {
            variables = new HashMap<>(this.variables);
            variables.put(var, val);
        }

        return new ComplexEvaluationContext(variables);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof ComplexEvaluationContext)) {
            return false;
        }

        ComplexEvaluationContext that = (ComplexEvaluationContext) o;
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

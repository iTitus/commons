package io.github.ititus.commons.math.calculation;

import io.github.ititus.commons.math.function.ComplexFunction;
import io.github.ititus.commons.math.number.BigComplex;
import io.github.ititus.commons.math.number.BigComplexConstants;
import io.github.ititus.commons.math.number.BigRational;
import io.github.ititus.commons.math.number.BigRationalConstants;

import java.util.function.UnaryOperator;

public class ComplexNewton {

    private static final BigComplex DEFAULT_INITIAL_VALUE = BigComplexConstants.ZERO;
    private static final int DEFAULT_MAX_ITERATIONS = 50;
    private static final BigRational DEFAULT_ABS_TOL_SQUARED = BigRational.ofExp(1.48, -8).squared();
    private static final BigRational DEFAULT_REL_TOL_SQUARED = BigRationalConstants.MINUS_ONE;

    private final UnaryOperator<BigComplex> function;
    private final UnaryOperator<BigComplex> derivative;

    private BigComplex initialValue;
    private int maxIterations;
    private BigRational absTolSquared;
    private final BigRational relTolSquared;

    private ComplexNewton(UnaryOperator<BigComplex> function, UnaryOperator<BigComplex> derivative) {
        this.function = function;
        this.derivative = derivative;

        this.initialValue = DEFAULT_INITIAL_VALUE;
        this.maxIterations = DEFAULT_MAX_ITERATIONS;
        this.absTolSquared = DEFAULT_ABS_TOL_SQUARED;
        this.relTolSquared = DEFAULT_REL_TOL_SQUARED;
    }

    public static ComplexNewton of(ComplexFunction f) {
        return new ComplexNewton(f::evaluate, f.derivative()::evaluate);
    }

    public ComplexNewton setInitialValue(BigComplex initialValue) {
        this.initialValue = initialValue;
        return this;
    }

    public ComplexNewton setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public ComplexNewton setAbsTolSquared(BigRational absTolSquared) {
        this.absTolSquared = absTolSquared;
        return this;
    }

    public DoubleNewton setRelTolSquared(double relTolSquared) {
        throw new UnsupportedOperationException();
    }

    public Result iterate() {
        BigComplex currentValue = initialValue;
        int iterations;
        boolean converged = false;
        for (iterations = 0; iterations < maxIterations; iterations++) {
            BigComplex delta = function.apply(currentValue).divide(derivative.apply(currentValue));
            BigRational diffSquared = delta.absSquared();
            if (diffSquared.compareTo(absTolSquared) < 0) {
                converged = true;
                break;
            }

            currentValue = currentValue.subtract(delta);
        }

        return new Result(currentValue, iterations, converged);
    }

    public static final class Result {

        private final BigComplex root;
        private final int iterations;
        private final boolean converged;

        private Result(BigComplex root, int iterations, boolean converged) {
            this.root = root;
            this.iterations = iterations;
            this.converged = converged;
        }

        public BigComplex root() {
            return root;
        }

        public int iterations() {
            return iterations;
        }

        public boolean converged() {
            return converged;
        }
    }
}

package io.github.ititus.math.calculation;

import io.github.ititus.math.function.ComplexFunction;
import io.github.ititus.math.number.BigComplex;
import io.github.ititus.math.number.BigComplexConstants;
import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;

public class ComplexNewton {

    private static final BigComplex DEFAULT_INITIAL_VALUE = BigComplexConstants.ZERO;
    private static final int DEFAULT_MAX_ITERATIONS = 50;
    private static final BigRational DEFAULT_ABS_TOL_SQUARED = BigRational.ofExp(1.48, -8).squared();
    private static final BigRational DEFAULT_REL_TOL_SQUARED = BigRationalConstants.MINUS_ONE;

    private final ComplexFunction function;
    private final ComplexFunction derivative;

    private BigComplex initialValue;
    private int maxIterations;
    private BigRational absTolSquared;
    private BigRational relTolSquared;

    private ComplexNewton(ComplexFunction function, ComplexFunction derivative) {
        this.function = function;
        this.derivative = derivative;

        this.initialValue = DEFAULT_INITIAL_VALUE;
        this.maxIterations = DEFAULT_MAX_ITERATIONS;
        this.absTolSquared = DEFAULT_ABS_TOL_SQUARED;
        this.relTolSquared = DEFAULT_REL_TOL_SQUARED;
    }

    public static ComplexNewton of(ComplexFunction f) {
        return new ComplexNewton(f, f.derivative());
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
            BigComplex delta = function.evaluate(currentValue).divide(derivative.evaluate(currentValue));
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

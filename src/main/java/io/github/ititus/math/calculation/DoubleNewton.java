package io.github.ititus.math.calculation;

import java.util.function.DoubleUnaryOperator;

public class DoubleNewton {

    private static final double DEFAULT_INITIAL_VALUE = 0;
    private static final int DEFAULT_MAX_ITERATIONS = 50;
    private static final double DEFAULT_ABS_TOL = 1.48e-8;
    private static final double DEFAULT_REL_TOL = -1;

    private final DoubleUnaryOperator function;
    private final DoubleUnaryOperator derivative;

    private double initialValue;
    private int maxIterations;
    private double absTol;
    private double relTol;

    private DoubleNewton(DoubleUnaryOperator function, DoubleUnaryOperator derivative) {
        this.function = function;
        this.derivative = derivative;

        this.initialValue = DEFAULT_INITIAL_VALUE;
        this.maxIterations = DEFAULT_MAX_ITERATIONS;
        this.absTol = DEFAULT_ABS_TOL;
        this.relTol = DEFAULT_REL_TOL;
    }

    public static DoubleNewton of(DoubleUnaryOperator f, DoubleUnaryOperator derivative) {
        return new DoubleNewton(f, derivative);
    }

    public DoubleNewton setInitialValue(double initialValue) {
        this.initialValue = initialValue;
        return this;
    }

    public DoubleNewton setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public DoubleNewton setAbsTol(double absTol) {
        this.absTol = absTol;
        return this;
    }

    public DoubleNewton setRelTol(double relTol) {
        throw new UnsupportedOperationException();
    }

    public Result iterate() {
        double currentValue = initialValue;
        int iterations;
        boolean converged = false;
        for (iterations = 0; iterations < maxIterations; iterations++) {
            double delta = function.applyAsDouble(currentValue) / derivative.applyAsDouble(currentValue);
            double diff = Math.abs(delta);
            if (diff < absTol) {
                converged = true;
                break;
            }

            currentValue -= delta;
        }

        return new Result(currentValue, iterations, converged);
    }

    public static final class Result {

        private final double root;
        private final int iterations;
        private final boolean converged;

        private Result(double root, int iterations, boolean converged) {
            this.root = root;
            this.iterations = iterations;
            this.converged = converged;
        }

        public double root() {
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

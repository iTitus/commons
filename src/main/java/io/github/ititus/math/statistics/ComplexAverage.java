package io.github.ititus.math.statistics;

import io.github.ititus.math.number.BigComplex;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ComplexAverage {

    private final AveragingMode averagingMode;
    private final List<BigComplex> numbers;

    private ComplexAverage(AveragingMode averagingMode) {
        this.averagingMode = averagingMode;
        this.numbers = new ArrayList<>();
    }

    public static ComplexAverage arithmetic() {
        return new ComplexAverage(AveragingMode.ARITHMETIC);
    }

    public BigComplex average() {
        return averagingMode.average(numbers);
    }

    public ComplexAverage add(BigComplex z) {
        numbers.add(z);
        return this;
    }

    public enum AveragingMode {

        ARITHMETIC(numbers -> numbers.stream().reduce(BigComplex.ZERO, BigComplex::add).divide(BigComplex.real(numbers.size())));

        private final Function<List<BigComplex>, BigComplex> averagingFunction;

        AveragingMode(Function<List<BigComplex>, BigComplex> averagingFunction) {
            this.averagingFunction = averagingFunction;
        }

        public BigComplex average(List<BigComplex> numbers) {
            return averagingFunction.apply(numbers);
        }
    }
}

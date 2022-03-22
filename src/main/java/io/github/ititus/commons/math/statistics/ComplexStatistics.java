package io.github.ititus.commons.math.statistics;

import io.github.ititus.commons.math.number.BigComplex;
import io.github.ititus.commons.math.number.BigRational;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;

import static io.github.ititus.commons.math.number.BigComplexConstants.ONE;
import static io.github.ititus.commons.math.number.BigComplexConstants.ZERO;

public final class ComplexStatistics {

    private final AveragingMode averagingMode;
    private final List<BigComplex> numbers;

    private ComplexStatistics(AveragingMode averagingMode) {
        this.averagingMode = averagingMode;
        this.numbers = new ArrayList<>();
    }

    public static ComplexStatistics arithmetic() {
        return new ComplexStatistics(AveragingMode.ARITHMETIC);
    }

    public static ComplexStatistics geometric() {
        return new ComplexStatistics(AveragingMode.GEOMETRIC);
    }

    public static ComplexStatistics harmonic() {
        return new ComplexStatistics(AveragingMode.HARMONIC);
    }

    public static Collector<BigComplex, ComplexStatistics, ComplexStatistics> arithmeticCollector() {
        return Collector.of(
                ComplexStatistics::arithmetic,
                ComplexStatistics::add,
                (a, b) -> a.addAll(b.numbers)
        );
    }

    public static Collector<BigComplex, ComplexStatistics, ComplexStatistics> geometricCollector() {
        return Collector.of(
                ComplexStatistics::geometric,
                ComplexStatistics::add,
                (a, b) -> a.addAll(b.numbers)
        );
    }

    public static Collector<BigComplex, ComplexStatistics, ComplexStatistics> harmonicCollector() {
        return Collector.of(
                ComplexStatistics::harmonic,
                ComplexStatistics::add,
                (a, b) -> a.addAll(b.numbers)
        );
    }

    public BigComplex sum() {
        return numbers.stream()
                .reduce(ZERO, BigComplex::add);
    }

    public BigComplex product() {
        return numbers.stream()
                .reduce(ONE, BigComplex::multiply);
    }

    public BigComplex average() {
        return averagingMode.average(numbers);
    }

    public int count() {
        return numbers.size();
    }

    public ComplexStatistics add(BigComplex z) {
        numbers.add(z);
        return this;
    }

    public ComplexStatistics addAll(Collection<BigComplex> zs) {
        numbers.addAll(zs);
        return this;
    }

    public enum AveragingMode {

        ARITHMETIC(
                numbers -> numbers.stream()
                        .reduce(ZERO, BigComplex::add)
                        .divide(numbers.size())
        ),
        GEOMETRIC(
                numbers -> numbers.stream()
                        .reduce(ONE, BigComplex::multiply)
                        .pow(BigRational.ofInv(numbers.size()))
        ),
        HARMONIC(
                numbers -> numbers.stream()
                        .map(BigComplex::inverse)
                        .reduce(ZERO, BigComplex::add)
                        .inverse()
                        .multiply(numbers.size())
        );

        private final Function<Collection<BigComplex>, BigComplex> averagingFunction;

        AveragingMode(Function<Collection<BigComplex>, BigComplex> averagingFunction) {
            this.averagingFunction = averagingFunction;
        }

        public BigComplex average(Collection<BigComplex> numbers) {
            return averagingFunction.apply(numbers);
        }
    }
}

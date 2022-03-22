package io.github.ititus.commons.math.statistics;

import io.github.ititus.commons.math.number.BigRational;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;

import static io.github.ititus.commons.math.number.BigRationalConstants.ONE;
import static io.github.ititus.commons.math.number.BigRationalConstants.ZERO;

public final class IntegerStatistics {

    private final AveragingMode averagingMode;
    private final List<BigInteger> numbers;

    private IntegerStatistics(AveragingMode averagingMode) {
        this.averagingMode = averagingMode;
        this.numbers = new ArrayList<>();
    }

    public static IntegerStatistics arithmetic() {
        return new IntegerStatistics(AveragingMode.ARITHMETIC);
    }

    public static IntegerStatistics geometric() {
        return new IntegerStatistics(AveragingMode.GEOMETRIC);
    }

    public static IntegerStatistics harmonic() {
        return new IntegerStatistics(AveragingMode.HARMONIC);
    }

    public static Collector<BigInteger, IntegerStatistics, IntegerStatistics> arithmeticCollector() {
        return Collector.of(
                IntegerStatistics::arithmetic,
                IntegerStatistics::add,
                (a, b) -> a.addAll(b.numbers)
        );
    }

    public static Collector<BigInteger, IntegerStatistics, IntegerStatistics> geometricCollector() {
        return Collector.of(
                IntegerStatistics::geometric,
                IntegerStatistics::add,
                (a, b) -> a.addAll(b.numbers)
        );
    }

    public static Collector<BigInteger, IntegerStatistics, IntegerStatistics> harmonicCollector() {
        return Collector.of(
                IntegerStatistics::harmonic,
                IntegerStatistics::add,
                (a, b) -> a.addAll(b.numbers)
        );
    }

    public BigInteger sum() {
        return numbers.stream()
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    public BigInteger product() {
        return numbers.stream()
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    public BigRational average() {
        return averagingMode.average(numbers);
    }

    public long count() {
        return numbers.size();
    }


    public BigInteger min() {
        return Collections.min(numbers);
    }

    public BigInteger max() {
        return Collections.max(numbers);
    }

    public IntegerStatistics add(BigInteger n) {
        numbers.add(n);
        return this;
    }

    public IntegerStatistics addAll(Collection<BigInteger> ns) {
        numbers.addAll(ns);
        return this;
    }

    public enum AveragingMode {

        ARITHMETIC(
                numbers -> numbers.stream()
                        .map(BigRational::of)
                        .reduce(ZERO, BigRational::add)
                        .divide(numbers.size())
        ),
        GEOMETRIC(
                numbers -> numbers.stream()
                        .map(BigRational::of)
                        .reduce(ONE, BigRational::multiply)
                        .pow(BigRational.ofInv(numbers.size()))
        ),
        HARMONIC(
                numbers -> numbers.stream()
                        .map(BigRational::ofInv)
                        .reduce(ZERO, BigRational::add)
                        .inverse()
                        .multiply(numbers.size())
        );

        private final Function<Collection<BigInteger>, BigRational> averagingFunction;

        AveragingMode(Function<Collection<BigInteger>, BigRational> averagingFunction) {
            this.averagingFunction = averagingFunction;
        }

        public BigRational average(Collection<BigInteger> numbers) {
            return averagingFunction.apply(numbers);
        }
    }
}

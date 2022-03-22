package io.github.ititus.commons.math.statistics;

import io.github.ititus.commons.math.number.BigRational;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;

import static io.github.ititus.commons.math.number.BigRationalConstants.ONE;
import static io.github.ititus.commons.math.number.BigRationalConstants.ZERO;

public final class RationalStatistics {

    private final AveragingMode averagingMode;
    private final List<BigRational> numbers;

    private RationalStatistics(AveragingMode averagingMode) {
        this.averagingMode = averagingMode;
        this.numbers = new ArrayList<>();
    }

    public static RationalStatistics arithmetic() {
        return new RationalStatistics(AveragingMode.ARITHMETIC);
    }

    public static RationalStatistics geometric() {
        return new RationalStatistics(AveragingMode.GEOMETRIC);
    }

    public static RationalStatistics harmonic() {
        return new RationalStatistics(AveragingMode.HARMONIC);
    }

    public static Collector<BigRational, RationalStatistics, RationalStatistics> arithmeticCollector() {
        return Collector.of(
                RationalStatistics::arithmetic,
                RationalStatistics::add,
                (a, b) -> a.addAll(b.numbers)
        );
    }

    public static Collector<BigRational, RationalStatistics, RationalStatistics> geometricCollector() {
        return Collector.of(
                RationalStatistics::geometric,
                RationalStatistics::add,
                (a, b) -> a.addAll(b.numbers)
        );
    }

    public static Collector<BigRational, RationalStatistics, RationalStatistics> harmonicCollector() {
        return Collector.of(
                RationalStatistics::harmonic,
                RationalStatistics::add,
                (a, b) -> a.addAll(b.numbers)
        );
    }

    public BigRational sum() {
        return numbers.stream()
                .reduce(ZERO, BigRational::add);
    }

    public BigRational product() {
        return numbers.stream()
                .reduce(ONE, BigRational::multiply);
    }

    public BigRational average() {
        return averagingMode.average(numbers);
    }

    public long count() {
        return numbers.size();
    }


    public BigRational min() {
        return Collections.min(numbers);
    }

    public BigRational max() {
        return Collections.max(numbers);
    }

    public RationalStatistics add(BigRational r) {
        numbers.add(r);
        return this;
    }

    public RationalStatistics addAll(Collection<BigRational> rs) {
        numbers.addAll(rs);
        return this;
    }

    public enum AveragingMode {

        ARITHMETIC(
                numbers -> numbers.stream()
                        .reduce(ZERO, BigRational::add)
                        .divide(numbers.size())
        ),
        GEOMETRIC(
                numbers -> numbers.stream()
                        .reduce(ONE, BigRational::multiply)
                        .pow(BigRational.ofInv(numbers.size()))
        ),
        HARMONIC(
                numbers -> numbers.stream()
                        .map(BigRational::inverse)
                        .reduce(ZERO, BigRational::add)
                        .inverse()
                        .multiply(numbers.size())
        );

        private final Function<Collection<BigRational>, BigRational> averagingFunction;

        AveragingMode(Function<Collection<BigRational>, BigRational> averagingFunction) {
            this.averagingFunction = averagingFunction;
        }

        public BigRational average(Collection<BigRational> numbers) {
            return averagingFunction.apply(numbers);
        }
    }
}

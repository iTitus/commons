package io.github.ititus.math.statistics;

import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class RationalStatistics {

    private final AveragingMode averagingMode;
    private final List<BigRational> numbers;

    private RationalStatistics(AveragingMode averagingMode) {
        this.averagingMode = averagingMode;
        this.numbers = new ArrayList<>();
    }

    public static RationalStatistics arithmetic() {
        return new RationalStatistics(AveragingMode.ARITHMETIC);
    }

    public BigRational average() {
        return averagingMode.average(numbers);
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

    public enum AveragingMode {

        ARITHMETIC(numbers -> numbers.stream().reduce(BigRationalConstants.ZERO, BigRational::add).divide(BigRational.of(numbers.size())));

        private final Function<List<BigRational>, BigRational> averagingFunction;

        AveragingMode(Function<List<BigRational>, BigRational> averagingFunction) {
            this.averagingFunction = averagingFunction;
        }

        public BigRational average(List<BigRational> numbers) {
            return averagingFunction.apply(numbers);
        }
    }
}

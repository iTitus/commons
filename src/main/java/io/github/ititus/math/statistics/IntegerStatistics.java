package io.github.ititus.math.statistics;

import io.github.ititus.math.number.BigRational;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class IntegerStatistics {

    private final AveragingMode averagingMode;
    private final List<BigInteger> numbers;

    private IntegerStatistics(AveragingMode averagingMode) {
        this.averagingMode = averagingMode;
        this.numbers = new ArrayList<>();
    }

    public static IntegerStatistics arithmetic() {
        return new IntegerStatistics(AveragingMode.ARITHMETIC);
    }

    public BigRational average() {
        return averagingMode.average(numbers);
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

    public enum AveragingMode {

        ARITHMETIC(numbers -> BigRational.of(numbers.stream().reduce(BigInteger.ZERO, BigInteger::add)).divide(BigRational.of(numbers.size())));

        private final Function<List<BigInteger>, BigRational> averagingFunction;

        AveragingMode(Function<List<BigInteger>, BigRational> averagingFunction) {
            this.averagingFunction = averagingFunction;
        }

        public BigRational average(List<BigInteger> numbers) {
            return averagingFunction.apply(numbers);
        }
    }
}

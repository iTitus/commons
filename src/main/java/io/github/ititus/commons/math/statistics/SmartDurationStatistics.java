package io.github.ititus.commons.math.statistics;

import io.github.ititus.commons.math.number.BigIntegerConstants;

import java.math.BigInteger;
import java.time.Duration;
import java.util.NoSuchElementException;

import static java.time.Duration.ZERO;

public final class SmartDurationStatistics {

    private BigInteger count;
    private Duration sum;
    private Duration min;
    private Duration max;

    private SmartDurationStatistics() {
        this.count = BigIntegerConstants.ZERO;
        this.sum = ZERO;
        this.min = null;
        this.max = null;
    }

    public static SmartDurationStatistics arithmetic() {
        return new SmartDurationStatistics();
    }

    public Duration sum() {
        return sum;
    }

    public Duration average() {
        return sum.dividedBy(count.longValueExact());
    }

    public BigInteger count() {
        return count;
    }

    public Duration min() {
        if (min == null) {
            throw new NoSuchElementException();
        }

        return min;
    }

    public Duration max() {
        if (max == null) {
            throw new NoSuchElementException();
        }

        return max;
    }

    public SmartDurationStatistics add(Duration d) {
        count = count.add(BigIntegerConstants.ONE);
        sum = sum.plus(d);
        if (min == null || d.compareTo(min) < 0) {
            min = d;
        }

        if (max == null || d.compareTo(max) > 0) {
            max = d;
        }

        return this;
    }

    public SmartDurationStatistics addAll(Iterable<Duration> ds) {
        for (Duration d : ds) {
            add(d);
        }

        return this;
    }
}

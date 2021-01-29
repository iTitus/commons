package io.github.ititus.math.statistics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.time.Duration.ZERO;


public final class DurationStatistics {

    private final AveragingMode averagingMode;
    private final List<Duration> durations;

    private DurationStatistics(AveragingMode averagingMode) {
        this.averagingMode = averagingMode;
        this.durations = new ArrayList<>();
    }

    public static DurationStatistics arithmetic() {
        return new DurationStatistics(AveragingMode.ARITHMETIC);
    }

    public Duration sum() {
        return durations.stream()
                .reduce(ZERO, Duration::plus);
    }

    public Duration average() {
        return averagingMode.average(durations);
    }

    public Duration min() {
        return Collections.min(durations);
    }

    public Duration max() {
        return Collections.max(durations);
    }

    public DurationStatistics add(Duration d) {
        durations.add(d);
        return this;
    }

    public DurationStatistics addAll(Collection<Duration> ds) {
        durations.addAll(ds);
        return this;
    }

    public enum AveragingMode {

        ARITHMETIC(durations -> durations.stream()
                .reduce(ZERO, Duration::plus)
                .dividedBy(durations.size())
        );

        private final Function<Collection<Duration>, Duration> averagingFunction;

        AveragingMode(Function<Collection<Duration>, Duration> averagingFunction) {
            this.averagingFunction = averagingFunction;
        }

        public Duration average(Collection<Duration> durations) {
            return averagingFunction.apply(durations);
        }
    }
}

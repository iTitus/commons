package io.github.ititus.math.statistics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class DurationStatistics {

    private final AveragingMode averagingMode;
    private final List<Duration> durations;

    private DurationStatistics(AveragingMode averagingMode) {
        this.averagingMode = averagingMode;
        this.durations = new ArrayList<>();
    }

    public static DurationStatistics arithmetic() {
        return new DurationStatistics(AveragingMode.ARITHMETIC);
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

    public enum AveragingMode {

        ARITHMETIC(durations -> durations.stream().reduce(Duration.ZERO, Duration::plus).dividedBy(durations.size()));

        private final Function<List<Duration>, Duration> averagingFunction;

        AveragingMode(Function<List<Duration>, Duration> averagingFunction) {
            this.averagingFunction = averagingFunction;
        }

        public Duration average(List<Duration> durations) {
            return averagingFunction.apply(durations);
        }
    }
}

package io.github.ititus.commons.math.time;

import io.github.ititus.commons.math.statistics.DurationStatistics;

import java.time.Duration;

public class StopWatchStatistics {

    private final DurationStatistics durationStatistics;
    private final StopWatch stopWatch;

    private StopWatchStatistics(DurationStatistics durationStatistics) {
        this.durationStatistics = durationStatistics;
        this.stopWatch = StopWatch.create();
    }

    public static StopWatchStatistics arithmetic() {
        return new StopWatchStatistics(DurationStatistics.arithmetic());
    }

    public StopWatchStatistics start() {
        stopWatch.start();
        return this;
    }

    public StopWatchStatistics stop() {
        durationStatistics.add(stopWatch.stop());
        return this;
    }

    public Duration average() {
        return durationStatistics.average();
    }

    public Duration min() {
        return durationStatistics.min();
    }

    public Duration max() {
        return durationStatistics.max();
    }

    public String averageF() {
        return DurationFormatter.formatMillis(average());
    }

    public String minF() {
        return DurationFormatter.formatMillis(min());
    }

    public String maxF() {
        return DurationFormatter.formatMillis(max());
    }

    @Override
    public String toString() {
        return String.format("min=%s | max=%s | avg=%s", minF(), maxF(), averageF());
    }
}

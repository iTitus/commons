package io.github.ititus.math.time;

import java.time.Duration;
import java.time.Instant;

public class StopWatch {

    private Instant time;
    private boolean running;

    private StopWatch() {
        this.time = null;
        this.running = false;
    }

    public static StopWatch create() {
        return new StopWatch();
    }

    public static StopWatch createRunning() {
        return new StopWatch().start();
    }

    public StopWatch start() {
        if (running) {
            throw new IllegalStateException();
        }

        time = Instant.now();
        running = true;
        return this;
    }

    public Duration stop() {
        if (!running) {
            throw new IllegalStateException();
        }

        running = false;
        return Duration.between(time, Instant.now());
    }

    public boolean isRunning() {
        return running;
    }

    public Instant getStartTime() {
        if (!running) {
            throw new IllegalStateException();
        }

        return time;
    }
}

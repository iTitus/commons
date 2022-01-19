package io.github.ititus.commons.math.time;

import java.time.Duration;

public class StopWatch {

    private long time;
    private boolean running;

    private StopWatch() {
        this.time = 0;
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

        running = true;
        time = System.nanoTime();
        return this;
    }

    public Duration stop() {
        if (!running) {
            throw new IllegalStateException();
        }

        long duration = System.nanoTime() - time;
        running = false;
        time = 0;
        return Duration.ofNanos(duration);
    }

    public boolean isRunning() {
        return running;
    }
}

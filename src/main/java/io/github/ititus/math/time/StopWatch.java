package io.github.ititus.math.time;

import java.time.Duration;

public class StopWatch {

    private long time;
    private boolean running;

    private StopWatch() {
        this.time = -1;
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

        time = System.nanoTime();
        running = true;
        return this;
    }

    public Duration stop() {
        if (!running) {
            throw new IllegalStateException();
        }

        running = false;
        return Duration.ofNanos(System.nanoTime() - time);
    }

    public boolean isRunning() {
        return running;
    }

    public long getStartTime() {
        if (!running) {
            throw new IllegalStateException();
        }

        return time;
    }
}

package io.github.ititus.math.time;

import java.time.Duration;

public class DurationFormatter {

    public static String formatNanos(Duration d) {
        return String.format("%d ns", d.toNanos());
    }

    public static String formatMillis(Duration d) {
        return String.format("%d.%06d ms", d.toMillis(), toNanosPartFixed(d));
    }

    public static String formatSeconds(Duration d) {
        return String.format("%d.%03d,%06d s", d.toSeconds(), d.toMillisPart(), toNanosPartFixed(d));
    }

    public static String formatMinutes(Duration d) {
        return String.format("%d:%02d.%03d,%06d min", d.toMinutes(), d.toSecondsPart(), d.toMillisPart(), toNanosPartFixed(d));
    }

    public static String formatHours(Duration d) {
        return String.format("%d:%02d:%02d.%03d,%06d h", d.toHours(), d.toMinutesPart(), d.toSecondsPart(), d.toMillisPart(), toNanosPartFixed(d));
    }

    public static String formatDays(Duration d) {
        return String.format("%d:%02d:%02d:%02d.%03d,%06d d", d.toDays(), d.toHoursPart(), d.toMinutesPart(), d.toSecondsPart(), d.toMillisPart(), toNanosPartFixed(d));
    }

    private static int toNanosPartFixed(Duration d) {
        return d.toNanosPart() % 1_000_000;
    }
}

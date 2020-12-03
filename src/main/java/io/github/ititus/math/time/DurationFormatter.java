package io.github.ititus.math.time;

import java.time.Duration;

import static io.github.ititus.si.unit.Units.*;

public final class DurationFormatter {

    private DurationFormatter() {
    }

    public static String formatNanos(Duration d) {
        return String.format("%d %s", d.toNanos(), NANOSECOND.getSymbol());
    }

    public static String formatMicros(Duration d) {
        return String.format("%d.%03d %s", toMicros(d), toNanosPartFixed(d), MICROSECOND.getSymbol());
    }

    public static String formatMillis(Duration d) {
        return String.format("%d.%03d%03d %s", d.toMillis(), toMicrosPart(d), toNanosPartFixed(d),
                MILLISECOND.getSymbol());
    }

    public static String formatSeconds(Duration d) {
        return String.format("%d.%03d%03d%03d %s", d.toSeconds(), d.toMillisPart(), toMicrosPart(d),
                toNanosPartFixed(d), SECOND.getSymbol());
    }

    public static String formatMinutes(Duration d) {
        return String.format("%d:%02d.%03d%03d%03d %s", d.toMinutes(), d.toSecondsPart(), d.toMillisPart(),
                toMicrosPart(d), toNanosPartFixed(d), MINUTE.getSymbol());
    }

    public static String formatHours(Duration d) {
        return String.format("%d:%02d:%02d.%03d%03d%03d %s", d.toHours(), d.toMinutesPart(), d.toSecondsPart(),
                d.toMillisPart(), toMicrosPart(d), toNanosPartFixed(d), HOUR.getSymbol());
    }

    public static String formatDays(Duration d) {
        return String.format("%d:%02d:%02d:%02d.%03d%03d%03d %s", d.toDays(), d.toHoursPart(), d.toMinutesPart(),
                d.toSecondsPart(), d.toMillisPart(), toMicrosPart(d), toNanosPartFixed(d), DAY.getSymbol());
    }

    public static String format(Duration d) {
        if (d.toDays() > 0) {
            return formatDays(d);
        } else if (d.toHours() > 0) {
            return formatHours(d);
        } else if (d.toMinutes() > 0) {
            return formatMinutes(d);
        } else if (d.toSeconds() > 0) {
            return formatSeconds(d);
        } else if (d.toMillis() > 0) {
            return formatMillis(d);
        } else if (toMicros(d) > 0) {
            return formatMicros(d);
        }

        return formatNanos(d);
    }

    private static long toMicros(Duration d) {
        long tempSeconds = d.getSeconds();
        long tempNanos = d.getNano();
        if (tempSeconds < 0) {
            // change the seconds and nano value to
            // handle Long.MIN_VALUE case
            tempSeconds = tempSeconds + 1;
            tempNanos = tempNanos - 1_000_000_000;
        }
        long micros = Math.multiplyExact(tempSeconds, 1_000_000);
        micros = Math.addExact(micros, tempNanos / 1_000);
        return micros;
    }

    private static int toMicrosPart(Duration d) {
        return (d.toNanosPart() / 1_000) % 1_000;
    }

    private static int toNanosPartFixed(Duration d) {
        return d.toNanosPart() % 1_000;
    }
}

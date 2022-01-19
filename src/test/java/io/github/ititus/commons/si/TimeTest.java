package io.github.ititus.commons.si;

import io.github.ititus.commons.si.quantity.Quantity;
import io.github.ititus.commons.si.quantity.type.Time;
import org.junit.jupiter.api.Test;

import static io.github.ititus.commons.assertions.Assertions.assertThat;
import static io.github.ititus.commons.si.quantity.value.QuantityValue.of;
import static io.github.ititus.commons.si.unit.Units.MINUTE;
import static io.github.ititus.commons.si.unit.Units.SECOND;
import static org.assertj.core.data.Percentage.withPercentage;

class TimeTest {

    @Test
    void secondsToMinutes() {
        Quantity<Time> seconds = SECOND.get(42);
        Quantity<Time> minutes = seconds.convertTo(MINUTE);

        assertThat(minutes.getValue()).isCloseTo(of(0.7), withPercentage(1));
    }

    @Test
    void minutesToSeconds() {
        Quantity<Time> minutes = MINUTE.get(42);
        Quantity<Time> seconds = minutes.convertTo(SECOND);

        assertThat(seconds.getValue()).isCloseTo(of(2520), withPercentage(1));
    }
}

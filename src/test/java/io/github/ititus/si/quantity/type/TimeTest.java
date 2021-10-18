package io.github.ititus.si.quantity.type;

import io.github.ititus.si.quantity.Quantity;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigRational.ofExp;
import static io.github.ititus.si.prefix.MetricPrefix.NANO;
import static io.github.ititus.si.quantity.value.QuantityValue.of;
import static io.github.ititus.si.unit.Units.*;
import static org.assertj.core.api.Assertions.assertThat;
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

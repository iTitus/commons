package io.github.ititus.si.quantity.type;

import io.github.ititus.si.quantity.Quantity;
import org.junit.jupiter.api.Test;

import static io.github.ititus.si.prefix.MetricPrefix.NANO;
import static io.github.ititus.si.unit.Units.GRAM;
import static io.github.ititus.si.unit.Units.KILOGRAM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;

public class MassTest {
    @Test
    public void kilogramToNanoGrams() {
        Quantity<Mass> kiloGrams = KILOGRAM.get(1.35);
        Quantity<Mass> nanoGrams = kiloGrams.convertTo(GRAM.prefix(NANO));

        assertThat(nanoGrams.getValue()).isCloseTo(1.35E12, withPercentage(1));
        assertThat(nanoGrams.getUnit().getSymbol()).isEqualTo("ng");
    }
}
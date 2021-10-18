package io.github.ititus.si.quantity.type;

import io.github.ititus.si.quantity.Quantity;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigRational.ofExp;
import static io.github.ititus.si.prefix.MetricPrefix.NANO;
import static io.github.ititus.si.quantity.value.QuantityValue.of;
import static io.github.ititus.si.unit.Units.GRAM;
import static io.github.ititus.si.unit.Units.KILOGRAM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;

class MassTest {

    @Test
    void kilogramToNanoGrams() {
        Quantity<Mass> kiloGrams = KILOGRAM.get(1.35);
        Quantity<Mass> nanoGrams = kiloGrams.convertTo(GRAM.prefix(NANO));

        assertThat(nanoGrams.getValue()).isCloseTo(of(ofExp(135, 10)), withPercentage(1));
        assertThat(nanoGrams.getUnit().getSymbol()).isEqualTo("ng");
    }
}

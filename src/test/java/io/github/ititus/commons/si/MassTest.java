package io.github.ititus.commons.si;

import io.github.ititus.commons.si.quantity.Quantity;
import io.github.ititus.commons.si.quantity.type.Mass;
import org.junit.jupiter.api.Test;

import static io.github.ititus.commons.assertions.Assertions.assertThat;
import static io.github.ititus.commons.math.number.BigRational.ofExp;
import static io.github.ititus.commons.si.prefix.MetricPrefix.NANO;
import static io.github.ititus.commons.si.quantity.value.QuantityValue.of;
import static io.github.ititus.commons.si.unit.Units.GRAM;
import static io.github.ititus.commons.si.unit.Units.KILOGRAM;
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

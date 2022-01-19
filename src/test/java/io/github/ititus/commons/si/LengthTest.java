package io.github.ititus.commons.si;

import io.github.ititus.commons.si.quantity.Quantity;
import io.github.ititus.commons.si.quantity.type.Length;
import org.junit.jupiter.api.Test;

import static io.github.ititus.commons.assertions.Assertions.assertThat;
import static io.github.ititus.commons.math.number.BigRational.ofExp;
import static io.github.ititus.commons.si.prefix.MetricPrefix.CENTI;
import static io.github.ititus.commons.si.prefix.MetricPrefix.MILLI;
import static io.github.ititus.commons.si.quantity.value.QuantityValue.of;
import static io.github.ititus.commons.si.unit.Units.METRE;
import static io.github.ititus.commons.si.unit.Units.MILE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;

class LengthTest {

    @Test
    void metreToCentiMetre() {
        Quantity<Length> metres = METRE.get(1.35);
        Quantity<Length> centiMetres = metres.convertTo(METRE.prefix(CENTI));

        assertThat(centiMetres.getValue()).isCloseTo(of(135), withPercentage(1.0));
        assertThat(centiMetres.getUnit().getSymbol()).isEqualTo("cm");
    }

    @Test
    void milliMetreToMiles() {
        Quantity<Length> milliMetre = METRE.prefix(MILLI).get(23);
        Quantity<Length> miles = milliMetre.convertTo(MILE);

        assertThat(miles.getValue()).isCloseTo(of(ofExp(1429, -8)), withPercentage(1.0));
        assertThat(miles.getUnit().getSymbol()).isEqualTo("mi");
    }
}

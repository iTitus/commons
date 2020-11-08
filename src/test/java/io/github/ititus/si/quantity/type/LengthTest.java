package io.github.ititus.si.quantity.type;

import io.github.ititus.si.quantity.Quantity;
import org.junit.jupiter.api.Test;

import static io.github.ititus.si.prefix.MetricPrefix.CENTI;
import static io.github.ititus.si.prefix.MetricPrefix.MILLI;
import static io.github.ititus.si.quantity.type.Length.METRE;
import static io.github.ititus.si.quantity.type.Length.MILE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;

public class LengthTest {
    @Test
    public void metreToCentiMetre() {
        Quantity<Length> metres = METRE.get(1.35);
        Quantity<Length> centiMetres = metres.convertTo(METRE.prefix(CENTI));

        assertThat(centiMetres.getValue()).isEqualTo(135);
        assertThat(centiMetres.getUnit().getSymbol()).isEqualTo("cm");
    }

    @Test
    public void milliMetreToMiles() {
        Quantity<Length> milliMetre = METRE.prefix(MILLI).get(23);
        Quantity<Length> miles = milliMetre.convertTo(MILE);

        assertThat(miles.getValue()).isCloseTo(1.429E-5, withPercentage(1));
        assertThat(miles.getUnit().getSymbol()).isEqualTo("mi");
    }
}
package io.github.ititus.commons.si;

import io.github.ititus.commons.si.quantity.Quantity;
import io.github.ititus.commons.si.quantity.type.Temperature;
import org.junit.jupiter.api.Test;

import static io.github.ititus.commons.assertions.Assertions.assertThat;
import static io.github.ititus.commons.si.quantity.value.QuantityValue.of;
import static io.github.ititus.commons.si.unit.Units.*;
import static org.assertj.core.data.Percentage.withPercentage;

class TemperatureTest {

    @Test
    void fromKelvin() {
        Quantity<Temperature> kelvin = KELVIN.get(42);
        Quantity<Temperature> celsius = kelvin.convertTo(CELSIUS);
        Quantity<Temperature> rankine = kelvin.convertTo(RANKINE);
        Quantity<Temperature> fahrenheit = kelvin.convertTo(FAHRENHEIT);

        assertThat(celsius.getValue()).isCloseTo(of(-231.15), withPercentage(1.0));
        assertThat(rankine.getValue()).isCloseTo(of(75.6), withPercentage(1.0));
        assertThat(fahrenheit.getValue()).isCloseTo(of(-384.07), withPercentage(1.0));
    }

    @Test
    void fromCelsius() {
        Quantity<Temperature> celsius = CELSIUS.get(42);
        Quantity<Temperature> kelvin = celsius.convertTo(KELVIN);
        Quantity<Temperature> rankine = celsius.convertTo(RANKINE);
        Quantity<Temperature> fahrenheit = celsius.convertTo(FAHRENHEIT);

        assertThat(kelvin.getValue()).isCloseTo(of(315.15), withPercentage(1.0));
        assertThat(rankine.getValue()).isCloseTo(of(567.27), withPercentage(1.0));
        assertThat(fahrenheit.getValue()).isCloseTo(of(107.6), withPercentage(1.0));
    }

    @Test
    void fromRankine() {
        Quantity<Temperature> rankine = RANKINE.get(42);
        Quantity<Temperature> kelvin = rankine.convertTo(KELVIN);
        Quantity<Temperature> celsius = rankine.convertTo(CELSIUS);
        Quantity<Temperature> fahrenheit = rankine.convertTo(FAHRENHEIT);

        assertThat(kelvin.getValue()).isCloseTo(of(23.33), withPercentage(1.0));
        assertThat(celsius.getValue()).isCloseTo(of(-249.82), withPercentage(1.0));
        assertThat(fahrenheit.getValue()).isCloseTo(of(-417.67), withPercentage(1.0));
    }

    @Test
    void fromFahrenheit() {
        Quantity<Temperature> fahrenheit = FAHRENHEIT.get(42);
        Quantity<Temperature> kelvin = fahrenheit.convertTo(KELVIN);
        Quantity<Temperature> celsius = fahrenheit.convertTo(CELSIUS);
        Quantity<Temperature> rankine = fahrenheit.convertTo(RANKINE);

        assertThat(kelvin.getValue()).isCloseTo(of(278.71), withPercentage(1.0));
        assertThat(celsius.getValue()).isCloseTo(of(5.56), withPercentage(1.0));
        assertThat(rankine.getValue()).isCloseTo(of(501.67), withPercentage(1.0));
    }
}

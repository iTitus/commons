package io.github.ititus.si.quantity.type;

import io.github.ititus.si.quantity.Quantity;
import org.junit.jupiter.api.Test;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.si.quantity.value.QuantityValue.of;
import static io.github.ititus.si.unit.Units.CELSIUS;
import static io.github.ititus.si.unit.Units.KELVIN;
import static org.assertj.core.data.Percentage.withPercentage;

class TemperatureTest {

    @Test
    void kelvinToCelsius() {
        Quantity<ThermodynamicTemperature> kelvin = KELVIN.get(0);
        Quantity<ThermodynamicTemperature> celsius = kelvin.convertTo(CELSIUS);

        assertThat(celsius.getValue()).isCloseTo(of(-273.15), withPercentage(1.0));
    }

    @Test
    void celsiusToKelvin() {
        Quantity<ThermodynamicTemperature> celsius = CELSIUS.get(0);
        Quantity<ThermodynamicTemperature> kelvin = celsius.convertTo(KELVIN);

        assertThat(kelvin.getValue()).isCloseTo(of(273.15), withPercentage(1.0));
    }
}

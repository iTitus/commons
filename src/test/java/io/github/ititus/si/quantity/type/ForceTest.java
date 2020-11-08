package io.github.ititus.si.quantity.type;

import io.github.ititus.si.quantity.Quantity;
import org.junit.jupiter.api.Test;

import static io.github.ititus.si.quantity.type.Acceleration.METRES_PER_SECOND_SQUARED;
import static io.github.ititus.si.quantity.type.Force.NEWTON;
import static io.github.ititus.si.quantity.type.Mass.KILOGRAM;
import static org.assertj.core.api.Assertions.assertThat;

public class ForceTest {
    @Test
    public void compoundTest() {
        Quantity<Mass> twoKilos = KILOGRAM.get(2);
        Quantity<Acceleration> acceleration = METRES_PER_SECOND_SQUARED.get(5);

        Quantity<Force> expectedForce = NEWTON.get(10);

        Quantity<?> force = twoKilos.multiply(acceleration);
        assertThat(force).isEqualTo(expectedForce);
    }

    @Test
    public void compoundSymbolTest() {
        Quantity<Mass> twoKilos = KILOGRAM.get(2);
        Quantity<Acceleration> acceleration = METRES_PER_SECOND_SQUARED.get(5);

        Quantity<?> force = twoKilos.multiply(acceleration);
        assertThat(force.getUnit().getSymbol()).isEqualTo("kgms^-2");
    }
}
package io.github.ititus.si.quantity.type;

import io.github.ititus.si.quantity.Quantity;
import org.junit.jupiter.api.Test;

import static io.github.ititus.si.quantity.type.Force.FORCE;
import static io.github.ititus.si.unit.Units.*;
import static org.assertj.core.api.Assertions.assertThat;

class ForceTest {

    @Test
    void compoundTest() {
        Quantity<Mass> twoKilos = KILOGRAM.get(2);
        Quantity<Acceleration> acceleration = METRES_PER_SECOND_SQUARED.get(5);

        Quantity<Force> expectedForce = NEWTON.get(10);

        Quantity<Force> force = twoKilos.multiply(acceleration).as(NEWTON);
        assertThat(force).isEqualTo(expectedForce);
    }

    @Test
    void compoundWithStandardTest() {
        Quantity<Mass> twoKilos = KILOGRAM.get(2);
        Quantity<Acceleration> acceleration = METRES_PER_SECOND_SQUARED.get(5);

        Quantity<Force> expectedForce = NEWTON.get(10);

        Quantity<Force> force = twoKilos.multiply(acceleration).asStandard(FORCE);
        assertThat(force).isEqualTo(expectedForce);
    }

    @Test
    void compoundSymbolTest() {
        Quantity<Mass> twoKilos = KILOGRAM.get(2);
        Quantity<Acceleration> acceleration = METRES_PER_SECOND_SQUARED.get(5);

        Quantity<Force> force = twoKilos.multiply(acceleration).as(FORCE);
        assertThat(force.getUnit().getSymbol()).isEqualTo("kgms^-2");
    }

    @Test
    void compoundFromMomentum() {
        Quantity<Mass> kilogram = KILOGRAM.get(5);
        Quantity<Speed> metresPerSecond = METRES_PER_SECOND.get(3);
        Quantity<?> momentumDelta = kilogram.multiply(metresPerSecond);
        Quantity<Time> timeDelta = SECOND.get(2);
        Quantity<Force> expectedForce = NEWTON.get(7.5);

        Quantity<Force> force = momentumDelta.divide(timeDelta).asStandard(FORCE);
        assertThat(force).isEqualTo(expectedForce);
    }
}

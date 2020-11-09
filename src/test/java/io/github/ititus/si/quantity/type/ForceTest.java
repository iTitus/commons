package io.github.ititus.si.quantity.type;

import io.github.ititus.si.quantity.Quantity;
import org.junit.jupiter.api.Test;

import static io.github.ititus.si.quantity.type.Acceleration.METRES_PER_SECOND_SQUARED;
import static io.github.ititus.si.quantity.type.Force.FORCE;
import static io.github.ititus.si.quantity.type.Force.NEWTON;
import static io.github.ititus.si.quantity.type.Mass.KILOGRAM;
import static io.github.ititus.si.quantity.type.Speed.METRES_PER_SECOND;
import static org.assertj.core.api.Assertions.assertThat;

public class ForceTest {
    @Test
    public void compoundTest() {
        Quantity<Mass> twoKilos = KILOGRAM.get(2);
        Quantity<Acceleration> acceleration = METRES_PER_SECOND_SQUARED.get(5);

        Quantity<Force> expectedForce = NEWTON.get(10);

        Quantity<Force> force = twoKilos.multiply(acceleration).as(FORCE).convertTo(NEWTON);
        assertThat(force).isEqualTo(expectedForce);
    }

    @Test
    public void compoundWithStandardTest() {
        Quantity<Mass> twoKilos = KILOGRAM.get(2);
        Quantity<Acceleration> acceleration = METRES_PER_SECOND_SQUARED.get(5);

        Quantity<Force> expectedForce = NEWTON.get(10);

        Quantity<Force> force = twoKilos.multiply(acceleration).as(FORCE).convertToStandard();
        assertThat(force).isEqualTo(expectedForce);
    }

    @Test
    public void compoundSymbolTest() {
        Quantity<Mass> twoKilos = KILOGRAM.get(2);
        Quantity<Acceleration> acceleration = METRES_PER_SECOND_SQUARED.get(5);

        Quantity<Force> force = twoKilos.multiply(acceleration).as(FORCE);
        assertThat(force.getUnit().getSymbol()).isEqualTo("kgms^-2");
    }

    @Test
    public void compoundFromMomentum() {
        Quantity<Mass> kilogram = KILOGRAM.get(5);
        Quantity<Speed> metresPerSecond = METRES_PER_SECOND.get(3);
        Quantity<?> momentumDelta = kilogram.multiply(metresPerSecond);
        Quantity<Time> timeDelta = Time.SECOND.get(2);
        Quantity<Force> expectedForce = NEWTON.get(7.5);

        Quantity<Force> force = momentumDelta.divide(timeDelta).as(FORCE).convertToStandard();
        assertThat(force).isEqualTo(expectedForce);
    }
}
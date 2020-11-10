package io.github.ititus.si.quantity;

import io.github.ititus.si.quantity.type.*;

import static io.github.ititus.si.unit.Units.*;

public final class Constants {

    /* Defined (exact) constants */

    // Length
    public static final Quantity<Speed> SPEED_OF_LIGHT = METRES_PER_SECOND.get(299_792_458.0);

    // Mass
    public static final Quantity<?> PLANCK_CONSTANT = JOULE.multiply(SECOND).get(6.626_070_15e-34);

    // Time
    public static final Quantity<Frequency> HYPERFINE_TRANSITION_FREQUENCY_OF_CAESIUM = HERTZ.get(9_192_631_770.0);

    // Electric Current
    public static final Quantity<ElectricCharge> ELEMENTARY_CHARGE = COULOMB.get(1.602_176_634e-19);

    // Thermodynamic Temperature
    public static final Quantity<?> BOLTZMANN_CONSTANT = JOULE.divide(KELVIN).get(1.380_649_76e-23);

    // Amount of Substance
    public static final Quantity<?> AVOGADRO_CONSTANT = MOLE.inverse().get(6.022_140_76e23);

    // Luminous Intensity
    public static final Quantity<LuminousEfficacy> LUMINOUS_EFFICACY_OF_540_THZ_RADIATION = LUMEN_PER_WATT.get(683);


    /* Measured constants */

    public static final Quantity<?> GRAVITATIONAL_CONSTANT =
            METRE.pow(3).divide(KILOGRAM.multiply(SECOND.pow(2))).get(6.674_30e-11);

    public static final Quantity<ElectricPermittivity> VACUUM_ELECTRIC_PERMITTIVITY =
            FARAD_PER_METRE.get(8.854_187_812_8e-12);

    public static final Quantity<MagneticPermeability> VACUUM_MAGNETIC_PERMEABILITY =
            HENRY_PER_METRE.get(1.256_637_062_12e-6);

    public static final Quantity<Mass> ELECTRON_MASS = KILOGRAM.get(9.109_383_701_5e-31);

    private Constants() {
    }
}

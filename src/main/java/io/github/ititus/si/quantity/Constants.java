package io.github.ititus.si.quantity;

import io.github.ititus.si.quantity.type.*;

import static io.github.ititus.math.number.BigRational.ofExp;
import static io.github.ititus.si.unit.Units.*;

public final class Constants {

    /* Defined (exact) constants */

    // Length
    public static final Quantity<Speed> SPEED_OF_LIGHT = METRES_PER_SECOND.get(299_792_458);

    // Mass
    public static final Quantity<?> PLANCK_CONSTANT = JOULE.multiply(SECOND).get(ofExp(662_607_015, -42));

    // Time
    public static final Quantity<Frequency> HYPERFINE_TRANSITION_FREQUENCY_OF_CAESIUM = HERTZ.get(9_192_631_770L);

    // Electric Current
    public static final Quantity<ElectricCharge> ELEMENTARY_CHARGE = COULOMB.get(ofExp(1_602_176_634, -28));

    // Thermodynamic Temperature
    public static final Quantity<?> BOLTZMANN_CONSTANT = JOULE.divide(KELVIN).get(ofExp(1_380_649_76, -31));

    // Amount of Substance
    public static final Quantity<?> AVOGADRO_CONSTANT = MOLE.inverse().get(ofExp(6_022_140_76, 15));

    // Luminous Intensity
    public static final Quantity<LuminousEfficacy> LUMINOUS_EFFICACY_OF_540_THZ_RADIATION = LUMEN_PER_WATT.get(683);


    /* Measured constants */

    public static final Quantity<?> GRAVITATIONAL_CONSTANT =
            METRE.pow(3).divide(KILOGRAM.multiply(SECOND.pow(2))).get(ofExp(667_430, -16));

    public static final Quantity<ElectricPermittivity> VACUUM_ELECTRIC_PERMITTIVITY =
            FARAD_PER_METRE.get(ofExp(88_541_878_128L, -22));

    public static final Quantity<MagneticPermeability> VACUUM_MAGNETIC_PERMEABILITY =
            HENRY_PER_METRE.get(ofExp(125_663_706_212L, -17));

    public static final Quantity<Mass> ELECTRON_MASS = KILOGRAM.get(ofExp(91_093_837_015L, -41));

    private Constants() {
    }
}

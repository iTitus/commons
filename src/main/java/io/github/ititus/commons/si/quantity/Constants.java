package io.github.ititus.commons.si.quantity;

import io.github.ititus.commons.si.quantity.type.*;
import io.github.ititus.commons.si.unit.Units;

import static io.github.ititus.commons.math.number.BigRational.ofExp;
import static io.github.ititus.commons.math.number.BigRationalConstants.PI;
import static io.github.ititus.commons.math.number.BigRationalConstants.TWO_PI;
import static io.github.ititus.commons.si.unit.Units.*;

public final class Constants {

    // @formatter:off

    /* Defined (exact) constants used for the definition of the base units */

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


    /* Other defined constants */

    public static final Quantity<Acceleration> STANDARD_ACCELERATION_OF_GRAVITY = METRES_PER_SECOND_SQUARED.get(ofExp(980_665, -5));

    public static final Quantity<Pressure> STANDARD_ATMOSPHERE = Units.STANDARD_ATMOSPHERE.get(1).asStandard();

    public static final Quantity<Pressure> STANDARD_STATE_PRESSURE = BAR.get(1).asStandard();


    /* Constants derived from defined constants */

    public static final Quantity<?> REDUCED_PLANCK_CONSTANT = PLANCK_CONSTANT.divide(TWO_PI);

    public static final Quantity<?> CONDUCTANCE_QUANTUM = ELEMENTARY_CHARGE.pow(2).multiply(2).divide(PLANCK_CONSTANT);

    public static final Quantity<ElectricConductance> JOSEPHSON_CONSTANT = ELEMENTARY_CHARGE.multiply(2).divide(PLANCK_CONSTANT).as(SIEMENS);

    public static final Quantity<ElectricResistance> VON_KLITZING_CONSTANT = PLANCK_CONSTANT.divide(ELEMENTARY_CHARGE.pow(2)).as(OHM);

    public static final Quantity<MagneticFlux> MAGNETIC_FLUX_QUANTUM = PLANCK_CONSTANT.divide(ELEMENTARY_CHARGE.multiply(2)).as(WEBER);

    public static final Quantity<?> INVERSE_CONDUCTANCE_QUANTUM = PLANCK_CONSTANT.divide(ELEMENTARY_CHARGE.pow(2).multiply(2)).as(OHM);

    public static final Quantity<?> FARADAY_CONSTANT = AVOGADRO_CONSTANT.multiply(ELEMENTARY_CHARGE);

    /**
     * Also known as the universal gas constant
     */
    public static final Quantity<?> MOLAR_GAS_CONSTANT = AVOGADRO_CONSTANT.multiply(BOLTZMANN_CONSTANT);

    public static final Quantity<?> FIRST_RADIATION_CONSTANT = PLANCK_CONSTANT.multiply(SPEED_OF_LIGHT.pow(2)).multiply(TWO_PI);

    public static final Quantity<?> FIRST_RADIATION_CONSTANT_FOR_SPECTRAL_RADIANCE = FIRST_RADIATION_CONSTANT.divide(STERADIAN.get(1));

    public static final Quantity<?> MOLAR_PLANCK_CONSTANT = AVOGADRO_CONSTANT.multiply(PLANCK_CONSTANT);

    public static final Quantity<?> SECOND_RADIATION_CONSTANT = PLANCK_CONSTANT.multiply(SPEED_OF_LIGHT).divide(BOLTZMANN_CONSTANT);


    /* Measured constants */

    public static final Quantity<?> GRAVITATIONAL_CONSTANT = METRE.pow(3).divide(KILOGRAM.multiply(SECOND.pow(2))).get(ofExp(667_430, -16));

    public static final Quantity<ElectricPermittivity> VACUUM_ELECTRIC_PERMITTIVITY = FARAD_PER_METRE.get(ofExp(88_541_878_128L, -22));

    public static final Quantity<MagneticPermeability> VACUUM_MAGNETIC_PERMEABILITY = HENRY_PER_METRE.get(ofExp(125_663_706_212L, -17));

    public static final Quantity<Mass> ELECTRON_MASS = KILOGRAM.get(ofExp(91_093_837_015L, -41));

    public static final Quantity<Mass> PROTON_MASS = KILOGRAM.get(ofExp(167_262_192_369L, -38));

    public static final Quantity<Mass> NEUTRON_MASS = KILOGRAM.get(ofExp(167_492_749_804L, -38));

    /* Constants derived from measured constants */

    /**
     * Proportionality constant in Coulomb's law
     */
    public static final Quantity<?> COULOMB_CONSTANT = VACUUM_ELECTRIC_PERMITTIVITY.multiply(PI).multiply(4).inverse();

    public static final Quantity<Dimensionless> FINE_STRUCTURE_CONSTANT = ELEMENTARY_CHARGE.pow(2).divide(VACUUM_ELECTRIC_PERMITTIVITY.multiply(REDUCED_PLANCK_CONSTANT).multiply(SPEED_OF_LIGHT).multiply(PI).multiply(4)).asStandard(Dimensionless.DIMENSIONLESS);

    // @formatter:on

    private Constants() {
    }
}

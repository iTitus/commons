package io.github.ititus.commons.si.unit;

import io.github.ititus.commons.internal.Config;
import io.github.ititus.commons.si.quantity.type.*;

import static io.github.ititus.commons.math.number.BigRational.of;
import static io.github.ititus.commons.math.number.BigRational.ofExp;
import static io.github.ititus.commons.si.prefix.MetricPrefix.*;
import static io.github.ititus.commons.si.quantity.type.Acceleration.ACCELERATION;
import static io.github.ititus.commons.si.quantity.type.Angle.ANGLE;
import static io.github.ititus.commons.si.quantity.type.Area.AREA;
import static io.github.ititus.commons.si.quantity.type.CatalyticActivity.CATALYTIC_ACTIVITY;
import static io.github.ititus.commons.si.quantity.type.Density.DENSITY;
import static io.github.ititus.commons.si.quantity.type.Dimensionless.DIMENSIONLESS;
import static io.github.ititus.commons.si.quantity.type.ElectricCapacitance.ELECTRIC_CAPACITANCE;
import static io.github.ititus.commons.si.quantity.type.ElectricCharge.ELECTRIC_CHARGE;
import static io.github.ititus.commons.si.quantity.type.ElectricConductance.ELECTRIC_CONDUCTANCE;
import static io.github.ititus.commons.si.quantity.type.ElectricCurrent.ELECTRIC_CURRENT;
import static io.github.ititus.commons.si.quantity.type.ElectricCurrentDensity.ELECTRIC_CURRENT_DENSITY;
import static io.github.ititus.commons.si.quantity.type.ElectricInductance.ELECTRIC_INDUCTANCE;
import static io.github.ititus.commons.si.quantity.type.ElectricPermittivity.ELECTRIC_PERMITTIVITY;
import static io.github.ititus.commons.si.quantity.type.ElectricPotential.ELECTRIC_POTENTIAL;
import static io.github.ititus.commons.si.quantity.type.ElectricResistance.ELECTRIC_RESISTANCE;
import static io.github.ititus.commons.si.quantity.type.Energy.ENERGY;
import static io.github.ititus.commons.si.quantity.type.EquivalentRadiationDose.EQUIVALENT_RADIATION_DOSE;
import static io.github.ititus.commons.si.quantity.type.Force.FORCE;
import static io.github.ititus.commons.si.quantity.type.Frequency.FREQUENCY;
import static io.github.ititus.commons.si.quantity.type.Illuminance.ILLUMINANCE;
import static io.github.ititus.commons.si.quantity.type.Length.LENGTH;
import static io.github.ititus.commons.si.quantity.type.LuminousEfficacy.LUMINOUS_EFFICACY;
import static io.github.ititus.commons.si.quantity.type.LuminousFlux.LUMINOUS_FLUX;
import static io.github.ititus.commons.si.quantity.type.LuminousIntensity.LUMINOUS_INTENSITY;
import static io.github.ititus.commons.si.quantity.type.MagneticFlux.MAGNETIC_FLUX;
import static io.github.ititus.commons.si.quantity.type.MagneticFluxDensity.MAGNETIC_FLUX_DENSITY;
import static io.github.ititus.commons.si.quantity.type.MagneticPermeability.MAGNETIC_PERMEABILITY;
import static io.github.ititus.commons.si.quantity.type.Mass.MASS;
import static io.github.ititus.commons.si.quantity.type.Momentum.MOMENTUM;
import static io.github.ititus.commons.si.quantity.type.Pace.PACE;
import static io.github.ititus.commons.si.quantity.type.Power.POWER;
import static io.github.ititus.commons.si.quantity.type.Pressure.PRESSURE;
import static io.github.ititus.commons.si.quantity.type.RadiationDoseAbsorbed.RADIATION_DOSE_ABSORBED;
import static io.github.ititus.commons.si.quantity.type.Radioactivity.RADIOACTIVITY;
import static io.github.ititus.commons.si.quantity.type.SolidAngle.SOLID_ANGLE;
import static io.github.ititus.commons.si.quantity.type.Speed.SPEED;
import static io.github.ititus.commons.si.quantity.type.SubstanceAmount.SUBSTANCE_AMOUNT;
import static io.github.ititus.commons.si.quantity.type.Temperature.TEMPERATURE;
import static io.github.ititus.commons.si.quantity.type.Time.TIME;
import static io.github.ititus.commons.si.quantity.type.Torque.TORQUE;
import static io.github.ititus.commons.si.quantity.type.Volume.VOLUME;

public final class Units {

    // @formatter:off

    /* Dimensionless Base Unit */

    public static final Unit<Dimensionless> ONE = new BaseUnit<>(DIMENSIONLESS, "");


    /* SI Base Units */

    public static final Unit<Length> METRE = new BaseUnit<>(LENGTH, "m");
    public static final Unit<Mass> GRAM = new BaseUnit<>(MASS, "g");
    public static final Unit<Time> SECOND = new BaseUnit<>(TIME, "s");
    public static final Unit<ElectricCurrent> AMPERE = new BaseUnit<>(ELECTRIC_CURRENT, "A");
    public static final Unit<Temperature> KELVIN = new BaseUnit<>(TEMPERATURE, "K");
    public static final Unit<SubstanceAmount> MOLE = new BaseUnit<>(SUBSTANCE_AMOUNT, "mol");
    public static final Unit<LuminousIntensity> CANDELA = new BaseUnit<>(LUMINOUS_INTENSITY, "cd");


    /* Derived/Converted Units */

    // Length
    public static final Unit<Length> KILOMETRE = METRE.prefix(KILO);
    public static final Unit<Length> DECIMETRE = METRE.prefix(DECI);
    public static final Unit<Length> CENTIMETRE = METRE.prefix(CENTI);
    public static final Unit<Length> MILLIMETRE = METRE.prefix(MILLI);
    public static final Unit<Length> INCH = MILLIMETRE.multiply(ofExp(254, -1)).alternate("in");
    public static final Unit<Length> FOOT = INCH.multiply(12).alternate("ft");
    public static final Unit<Length> YARD = FOOT.multiply(3).alternate("yd");
    public static final Unit<Length> MILE = YARD.multiply(1760).alternate("mi");
    public static final Unit<Length> PICA = INCH.divide(6).alternate("pica");
    public static final Unit<Length> POINT = PICA.divide(12).alternate("pt");
    public static final Unit<Length> FATHOM = YARD.multiply(2).alternate("ftm");
    public static final Unit<Length> NAUTICAL_MILE = METRE.multiply(1_852).alternate("nmi");
    public static final Unit<Length> CABLE = NAUTICAL_MILE.divide(10).alternate("cb");

    // Mass
    public static final Unit<Mass> KILOGRAM = GRAM.prefix(KILO);
    public static final Unit<Mass> TONNE = KILOGRAM.multiply(1_000).alternate("t");
    public static final Unit<Mass> CARAT = GRAM.divide(5).alternate("ct");
    public static final Unit<Mass> POUND = KILOGRAM.multiply(ofExp(45_359_237, -8)).alternate("lb");
    public static final Unit<Mass> OUNCE = POUND.divide(16).alternate("oz");
    public static final Unit<Mass> DRAM = OUNCE.divide(16).alternate("dr");
    public static final Unit<Mass> GRAIN = POUND.divide(7_000).alternate("gr");
    public static final Unit<Mass> PENNYWEIGHT = GRAIN.multiply(24).alternate("dwt");
    public static final Unit<Mass> TROY_OUNCE = PENNYWEIGHT.multiply(20).alternate("oz t");
    public static final Unit<Mass> TROY_POUND = TROY_OUNCE.multiply(12).alternate("lb t");

    // Time
    public static final Unit<Time> MINUTE = SECOND.multiply(60).alternate("min");
    public static final Unit<Time> HOUR = MINUTE.multiply(60).alternate("h");
    public static final Unit<Time> DAY = HOUR.multiply(24).alternate("d");
    public static final Unit<Time> NANOSECOND = SECOND.prefix(NANO);
    public static final Unit<Time> MICROSECOND = SECOND.prefix(MICRO);
    public static final Unit<Time> MILLISECOND = SECOND.prefix(MILLI);

    // Electric Current

    // Thermodynamic Temperature
    public static final Unit<Temperature> CELSIUS = KELVIN.shift(ofExp(27315, -2)).alternate("°C");
    public static final Unit<Temperature> RANKINE = KELVIN.multiply(of(5, 9)).alternate("°R");
    public static final Unit<Temperature> FAHRENHEIT = RANKINE.shift(ofExp(45967, -2)).alternate("°F");

    // Amount of Substance

    // Luminous Intensity

    // (Plane) Angle
    public static final Unit<Angle> RADIAN = ONE.alternate("rad").as(ANGLE);

    // Solid Angle
    public static final Unit<SolidAngle> STERADIAN = ONE.alternate("sr").as(SOLID_ANGLE);

    // Frequency
    public static final Unit<Frequency> HERTZ = SECOND.inverse().alternate("Hz").as(FREQUENCY);

    // Speed
    public static final Unit<Speed> METRES_PER_SECOND = METRE.divide(SECOND).as(SPEED);
    public static final Unit<Speed> KILOMETRES_PER_HOUR = KILOMETRE.divide(HOUR).as(SPEED);
    public static final Unit<Speed> MILES_PER_HOUR = MILE.divide(HOUR).alternate("mph").as(SPEED);
    public static final Unit<Speed> KNOT = NAUTICAL_MILE.divide(HOUR).alternate("kn").as(SPEED);

    // Pace
    public static final Unit<Pace> SECONDS_PER_METRE = SECOND.divide(METRE).as(PACE);
    public static final Unit<Pace> MINUTES_PER_KILOMETRE = MINUTE.divide(KILOMETRE).as(PACE);

    // Acceleration
    public static final Unit<Acceleration> METRES_PER_SECOND_SQUARED = METRE.divide(SECOND.pow(2)).as(ACCELERATION);

    // Momentum
    public static final Unit<Momentum> KILOGRAM_METRES_PER_SECOND = KILOGRAM.multiply(METRE.divide(SECOND)).as(MOMENTUM);

    // Force
    public static final Unit<Force> NEWTON = KILOGRAM.multiply(METRES_PER_SECOND_SQUARED).alternate("N").as(FORCE);

    // Energy & Power
    public static final Unit<Energy> JOULE = NEWTON.multiply(METRE).alternate("J").as(ENERGY);
    public static final Unit<Energy> ELECTRON_VOLT = JOULE.multiply(ofExp(1.602_176_634, -19));
    public static final Unit<Power> WATT = JOULE.divide(SECOND).alternate("W").as(POWER);
    public static final Unit<Power> KILOWATT = WATT.prefix(KILO);
    public static final Unit<Energy> KILOWATT_HOUR = KILOWATT.multiply(HOUR).as(ENERGY);

    // Torque
    public static final Unit<Torque> NEWTON_METER = NEWTON.multiply(METRE).as(TORQUE);

    // Area
    public static final Unit<Area> SQUARE_METRE = METRE.pow(2).as(AREA);

    // Volume
    public static final Unit<Volume> CUBIC_METRE = METRE.pow(3).as(VOLUME);
    public static final Unit<Volume> CUBIC_DECIMETRE = DECIMETRE.pow(3).as(VOLUME);
    public static final Unit<Volume> LITRE = CUBIC_DECIMETRE.alternate("l");

    // Density
    public static final Unit<Density> KILOGRAM_PER_CUBIC_METRE = KILOGRAM.divide(CUBIC_METRE).as(DENSITY);

    // Pressure
    public static final Unit<Pressure> PASCAL = NEWTON.divide(SQUARE_METRE).alternate("Pa").as(PRESSURE);
    public static final Unit<Pressure> HECTOPASCAL = PASCAL.prefix(HECTO);
    public static final Unit<Pressure> BAR = PASCAL.multiply(100_000).alternate("bar");
    public static final Unit<Pressure> STANDARD_ATMOSPHERE = PASCAL.multiply(101_325).alternate("atm");

    // Electric Charge
    public static final Unit<ElectricCharge> COULOMB = AMPERE.multiply(SECOND).alternate("C").as(ELECTRIC_CHARGE);

    // Electric Potential
    public static final Unit<ElectricPotential> VOLT = JOULE.divide(COULOMB).alternate("V").as(ELECTRIC_POTENTIAL);

    // Electric Capacitance
    public static final Unit<ElectricCapacitance> FARAD = COULOMB.divide(VOLT).alternate("F").as(ELECTRIC_CAPACITANCE);

    // Electric Resistance
    public static final Unit<ElectricResistance> OHM = VOLT.divide(AMPERE).alternate(Config.FORCE_ASCII ? "Ohm" : "\u03A9").as(ELECTRIC_RESISTANCE);

    // Electric Conductance
    public static final Unit<ElectricConductance> SIEMENS = OHM.inverse().alternate("S").as(ELECTRIC_CONDUCTANCE);

    // Magnetic Flux
    public static final Unit<MagneticFlux> WEBER = VOLT.multiply(SECOND).alternate("Wb").as(MAGNETIC_FLUX);

    // Magnetic Flux Density
    public static final Unit<MagneticFluxDensity> TESLA = WEBER.divide(SQUARE_METRE).alternate("T").as(MAGNETIC_FLUX_DENSITY);

    // Electric Inductance
    public static final Unit<ElectricInductance> HENRY = WEBER.divide(AMPERE).alternate("H").as(ELECTRIC_INDUCTANCE);

    // Electric Current Density
    public static final Unit<ElectricCurrentDensity> AMPERE_PER_SQUARE_METRE = AMPERE.divide(SQUARE_METRE).as(ELECTRIC_CURRENT_DENSITY);

    // Electric Permittivity
    public static final Unit<ElectricPermittivity> FARAD_PER_METRE = FARAD.divide(METRE).as(ELECTRIC_PERMITTIVITY);

    // Magnetic Permeability
    public static final Unit<MagneticPermeability> HENRY_PER_METRE = HENRY.divide(METRE).as(MAGNETIC_PERMEABILITY);

    // Radioactivity
    public static final Unit<Radioactivity> BECQUEREL = SECOND.inverse().alternate("Bq").as(RADIOACTIVITY);

    // Radiation Dose Absorbed
    public static final Unit<RadiationDoseAbsorbed> GRAY = JOULE.divide(KILOGRAM).alternate("Gy").as(RADIATION_DOSE_ABSORBED);

    // Equivalent Radiation Dose
    public static final Unit<EquivalentRadiationDose> SIEVERT = JOULE.divide(KILOGRAM).alternate("Sv").as(EQUIVALENT_RADIATION_DOSE);

    // Luminous Flux
    public static final Unit<LuminousFlux> LUMEN = CANDELA.multiply(STERADIAN).alternate("lm").as(LUMINOUS_FLUX);

    // Luminous Efficacy
    public static final Unit<LuminousEfficacy> LUMEN_PER_WATT = LUMEN.divide(WATT).as(LUMINOUS_EFFICACY);

    // Illuminance
    public static final Unit<Illuminance> LUX = LUMEN.divide(SQUARE_METRE).alternate("lx").as(ILLUMINANCE);

    // Catalytic Activity
    public static final Unit<CatalyticActivity> KATAL = MOLE.divide(SECOND).alternate("kat").as(CATALYTIC_ACTIVITY);

    // @formatter:on

    private Units() {
    }
}

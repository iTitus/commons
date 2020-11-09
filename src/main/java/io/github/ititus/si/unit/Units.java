package io.github.ititus.si.unit;

import io.github.ititus.si.quantity.type.*;

import static io.github.ititus.si.prefix.MetricPrefix.KILO;
import static io.github.ititus.si.quantity.type.Acceleration.ACCELERATION;
import static io.github.ititus.si.quantity.type.Angle.ANGLE;
import static io.github.ititus.si.quantity.type.Area.AREA;
import static io.github.ititus.si.quantity.type.Dimensionless.DIMENSIONLESS;
import static io.github.ititus.si.quantity.type.ElectricCurrent.ELECTRIC_CURRENT;
import static io.github.ititus.si.quantity.type.Energy.ENERGY;
import static io.github.ititus.si.quantity.type.Force.FORCE;
import static io.github.ititus.si.quantity.type.Length.LENGTH;
import static io.github.ititus.si.quantity.type.LuminousIntensity.LUMINOUS_INTENSITY;
import static io.github.ititus.si.quantity.type.Mass.MASS;
import static io.github.ititus.si.quantity.type.Momentum.MOMENTUM;
import static io.github.ititus.si.quantity.type.Power.POWER;
import static io.github.ititus.si.quantity.type.Speed.SPEED;
import static io.github.ititus.si.quantity.type.SubstanceAmount.SUBSTANCE_AMOUNT;
import static io.github.ititus.si.quantity.type.ThermodynamicTemperature.THERMODYNAMIC_TEMPERATURE;
import static io.github.ititus.si.quantity.type.Time.TIME;
import static io.github.ititus.si.quantity.type.Volume.VOLUME;

public final class Units {

    /* Dimensionless Base Unit */

    public static final Unit<Dimensionless> ONE = new BaseUnit<>(DIMENSIONLESS, "");


    /* SI Base Units */

    public static final Unit<Length> METRE = new BaseUnit<>(LENGTH, "m");
    public static final Unit<Mass> GRAM = new BaseUnit<>(MASS, "g");
    public static final Unit<Time> SECOND = new BaseUnit<>(TIME, "s");
    public static final Unit<ElectricCurrent> AMPERE = new BaseUnit<>(ELECTRIC_CURRENT, "A");
    public static final Unit<ThermodynamicTemperature> KELVIN = new BaseUnit<>(THERMODYNAMIC_TEMPERATURE, "K");
    public static final Unit<SubstanceAmount> MOLE = new BaseUnit<>(SUBSTANCE_AMOUNT, "mol");
    public static final Unit<LuminousIntensity> CANDELA = new BaseUnit<>(LUMINOUS_INTENSITY, "cd");


    /* Derived/Converted Units */

    // Length
    public static final Unit<Length> KILOMETRE = METRE.prefix(KILO);
    public static final Unit<Length> MILE = METRE.multiply(1_609.344).alternate("mi");
    public static final Unit<Length> NAUTICAL_MILE = METRE.multiply(1_852).alternate("nmi");

    // Mass
    public static final Unit<Mass> KILOGRAM = GRAM.prefix(KILO);

    // Time
    public static final Unit<Time> MINUTE = SECOND.multiply(60).alternate("min");
    public static final Unit<Time> HOUR = MINUTE.multiply(60).alternate("h");

    // Electric Current

    // Thermodynamic Temperature

    // Amount of Substance

    // Luminous Intensity

    // Angle
    public static final Unit<Angle> RADIANS = ONE.alternate("rad").as(ANGLE);

    // Speed
    public static final Unit<Speed> METRES_PER_SECOND = METRE.divide(SECOND).as(SPEED);
    public static final Unit<Speed> KILOMETRES_PER_HOUR = KILOMETRE.divide(HOUR).as(SPEED);
    public static final Unit<Speed> MILES_PER_HOUR = MILE.divide(HOUR).alternate("mph").as(SPEED);
    public static final Unit<Speed> KNOT = NAUTICAL_MILE.divide(HOUR).alternate("kn").as(SPEED);

    // Acceleration
    public static final Unit<Acceleration> METRES_PER_SECOND_SQUARED = METRE.divide(SECOND.pow(2)).as(ACCELERATION);

    // Momentum
    public static final Unit<Momentum> KILOGRAM_METRES_PER_SECOND =
            KILOGRAM.multiply(METRE.divide(SECOND)).as(MOMENTUM);

    // Force
    public static final Unit<Force> NEWTON = KILOGRAM.multiply(METRES_PER_SECOND_SQUARED).alternate("N").as(FORCE);

    // Energy & Power
    public static final Unit<Energy> JOULE = NEWTON.multiply(METRE).alternate("J").as(ENERGY);
    public static final Unit<Power> WATT = JOULE.divide(SECOND).alternate("W").as(POWER);
    public static final Unit<Power> KILOWATT = WATT.prefix(KILO);
    public static final Unit<Energy> KILOWATT_HOUR = KILOWATT.multiply(HOUR).as(ENERGY);

    // Area
    public static final Unit<Area> SQUARE_METRE = METRE.pow(2).as(AREA);

    // Volume
    public static final Unit<Volume> CUBIC_METRE = METRE.pow(3).as(VOLUME);

    private Units() {
    }
}

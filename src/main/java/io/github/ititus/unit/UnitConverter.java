package io.github.ititus.unit;

public interface UnitConverter {

    double convert(double value);

    UnitConverter inverse();

}

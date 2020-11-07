package io.github.ititus.si.unit;

public interface UnitConverter {

    double convert(double value);

    UnitConverter inverse();

}

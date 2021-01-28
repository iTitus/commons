package io.github.ititus.converters;

import io.github.ititus.math.number.BigComplex;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class BigComplexArgumentConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) {
        return BigComplex.of(source);
    }
}

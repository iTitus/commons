package io.github.ititus.converters;

import io.github.ititus.math.number.BigRational;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class BigRationalArgumentConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) {
        return BigRational.of(source);
    }
}

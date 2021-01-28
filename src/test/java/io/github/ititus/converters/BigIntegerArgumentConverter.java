package io.github.ititus.converters;

import io.github.ititus.math.number.BigIntegerMath;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class BigIntegerArgumentConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) {
        return BigIntegerMath.of(source);
    }
}

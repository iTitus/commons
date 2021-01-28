package io.github.ititus.converters;

import io.github.ititus.math.number.BigDecimalMath;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class BigDecimalArgumentConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) {
        return BigDecimalMath.of(source);
    }
}

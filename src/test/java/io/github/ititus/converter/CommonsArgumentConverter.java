package io.github.ititus.converter;

import io.github.ititus.math.number.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CommonsArgumentConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) {
        Class<?> type = context.getParameter().getType();
        if (type == byte.class || type == Byte.class) {
            return JavaMath.toByte(source);
        } else if (type == short.class || type == Short.class) {
            return JavaMath.toShort(source);
        } else if (type == int.class || type == Integer.class) {
            return JavaMath.toInt(source);
        } else if (type == long.class || type == Long.class) {
            return JavaMath.toLong(source);
        } else if (type == BigInteger.class) {
            return BigIntegerMath.of(source);
        } else if (type == float.class || type == Float.class) {
            return JavaMath.toFloat(source);
        } else if (type == double.class || type == Double.class) {
            return JavaMath.toDouble(source);
        } else if (type == BigDecimal.class) {
            return BigDecimalMath.of(source);
        } else if (type == BigRational.class) {
            return BigRational.of(source);
        } else if (type == BigComplex.class) {
            return BigComplex.of(source);
        }

        throw new ArgumentConversionException("Unknown target type" + type);
    }
}

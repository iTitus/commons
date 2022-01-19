package io.github.ititus.commons.si.quantity.value;

import io.github.ititus.commons.math.number.BigRational;
import io.github.ititus.commons.math.number.BigRationalConstants;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class QuantityValue extends Number implements Comparable<QuantityValue> {

    public static final QuantityValue ZERO = of(BigRationalConstants.ZERO);
    public static final QuantityValue ONE = of(BigRationalConstants.ONE);

    public static QuantityValue of(int value) {
        return of(BigRational.of(value));
    }

    public static QuantityValue of(long value) {
        return of(BigRational.of(value));
    }

    public static QuantityValue of(BigInteger value) {
        return of(BigRational.of(value));
    }

    public static QuantityValue of(float value) {
        return of(BigRational.of(value));
    }

    public static QuantityValue of(double value) {
        return of(BigRational.of(value));
    }

    public static QuantityValue of(BigDecimal value) {
        return of(BigRational.of(value));
    }

    public static QuantityValue of(BigRational value) {
        return BigRationalValue.ofBigRational(value);
    }

    public abstract BigRational bigRationalValue();

    public abstract boolean isZero();

    public abstract boolean isOne();

    public abstract QuantityValue abs();

    public abstract QuantityValue add(QuantityValue value);

    public abstract QuantityValue negate();

    public QuantityValue subtract(QuantityValue value) {
        return add(value.negate());
    }

    public abstract QuantityValue multiply(QuantityValue value);

    public abstract QuantityValue inverse();

    public QuantityValue divide(QuantityValue value) {
        return multiply(value.inverse());
    }

    public abstract QuantityValue pow(int n);

    public abstract QuantityValue root(int n);

}

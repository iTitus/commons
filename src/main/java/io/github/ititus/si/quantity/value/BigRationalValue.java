package io.github.ititus.si.quantity.value;

import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;

import java.util.Objects;

final class BigRationalValue extends QuantityValue {

    static final BigRationalValue ZERO = new BigRationalValue(BigRationalConstants.ZERO);
    static final BigRationalValue ONE = new BigRationalValue(BigRationalConstants.ONE);

    private final BigRational value;

    private BigRationalValue(BigRational value) {
        this.value = value;
    }

    static QuantityValue ofBigRational(BigRational value) {
        if (value.isZero()) {
            return ZERO;
        } else if (value.isOne()) {
            return ONE;
        }

        return new BigRationalValue(value);
    }

    @Override
    public BigRational bigRationalValue() {
        return value;
    }

    @Override
    public boolean isZero() {
        return value.isZero();
    }

    @Override
    public boolean isOne() {
        return value.isOne();
    }

    @Override
    public QuantityValue abs() {
        return ofBigRational(value.abs());
    }

    @Override
    public QuantityValue add(QuantityValue value) {
        return ofBigRational(this.value.add(value.bigRationalValue()));
    }

    @Override
    public QuantityValue negate() {
        return ofBigRational(value.negate());
    }

    @Override
    public QuantityValue multiply(QuantityValue value) {
        return ofBigRational(this.value.multiply(value.bigRationalValue()));
    }

    @Override
    public QuantityValue inverse() {
        return ofBigRational(value.inverse());
    }

    @Override
    public QuantityValue pow(int n) {
        return ofBigRational(value.pow(n));
    }

    @Override
    public QuantityValue root(int n) {
        return ofBigRational(value.pow(BigRational.ofInv(n)));
    }

    @Override
    public int compareTo(QuantityValue value) {
        return this.value.compareTo(value.bigRationalValue());
    }

    @Override
    public int intValue() {
        return value.intValue();
    }

    @Override
    public long longValue() {
        return value.longValue();
    }

    @Override
    public float floatValue() {
        return value.floatValue();
    }

    @Override
    public double doubleValue() {
        return value.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BigRationalValue)) {
            return false;
        }
        BigRationalValue that = (BigRationalValue) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BigRationalValue{" +
                "value=" + value +
                '}';
    }
}

package io.github.ititus.assertions;

import io.github.ititus.math.number.BigComplex;
import io.github.ititus.math.number.BigRational;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.data.Offset;
import org.assertj.core.data.Percentage;

import static io.github.ititus.math.number.BigComplexConstants.*;

public class BigComplexAssert extends AbstractAssert<BigComplexAssert, BigComplex> {

    public BigComplexAssert(BigComplex actual) {
        super(actual, BigComplexAssert.class);
    }

    public BigComplexAssert isZero() {
        isEqualTo(ZERO);
        return myself;
    }

    public BigComplexAssert isNotZero() {
        isNotEqualTo(ZERO);
        return myself;
    }

    public BigComplexAssert isOne() {
        isEqualTo(ONE);
        return myself;
    }

    public BigComplexAssert isImaginaryUnit() {
        isEqualTo(I);
        return myself;
    }

    public BigComplexAssert isCloseTo(BigComplex expected, Offset<BigRational> offset) {
        BigRational absDiff = actual.subtract(expected).abs();

        BigRationalAssert absDiffAssert = new BigRationalAssert(absDiff);
        if (offset.strict) {
            absDiffAssert.isLessThan(offset.value);
        } else {
            absDiffAssert.isLessThanOrEqualTo(offset.value);
        }

        return myself;
    }

    public BigComplexAssert isNotCloseTo(BigComplex expected, Offset<BigRational> offset) {
        BigRational absDiff = actual.subtract(expected).abs();

        BigRationalAssert absDiffAssert = new BigRationalAssert(absDiff);
        if (offset.strict) {
            absDiffAssert.isGreaterThanOrEqualTo(offset.value);
        } else {
            absDiffAssert.isGreaterThan(offset.value);
        }

        return myself;
    }

    public BigComplexAssert isCloseTo(BigComplex expected, Percentage percentage) {
        BigRational absDiff = actual.subtract(expected).abs();
        BigRational acceptableDiff = expected.abs().multiply(BigRational.of(percentage.value).divide(100));

        new BigRationalAssert(absDiff).isLessThan(acceptableDiff);

        return myself;
    }

    public BigComplexAssert isNotCloseTo(BigComplex expected, Percentage percentage) {
        BigRational absDiff = actual.subtract(expected).abs();
        BigRational acceptableDiff = expected.abs().multiply(BigRational.of(percentage.value).divide(100));

        new BigRationalAssert(absDiff).isGreaterThanOrEqualTo(acceptableDiff);

        return myself;
    }

    public BigRationalAssert real() {
        return new BigRationalAssert(actual.getReal());
    }

    public BigRationalAssert imag() {
        return new BigRationalAssert(actual.getImag());
    }

    public BigRationalAssert abs() {
        return new BigRationalAssert(actual.abs());
    }

    public BigRationalAssert angle() {
        return new BigRationalAssert(actual.angle());
    }
}

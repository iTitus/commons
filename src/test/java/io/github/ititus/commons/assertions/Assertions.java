package io.github.ititus.commons.assertions;

import io.github.ititus.commons.data.either.Either;
import io.github.ititus.commons.math.number.BigComplex;
import io.github.ititus.commons.math.number.BigRational;
import io.github.ititus.commons.si.quantity.value.QuantityValue;
import org.assertj.core.api.InstanceOfAssertFactories;

public class Assertions implements InstanceOfAssertFactories {

    protected Assertions() {
    }

    public static BigRationalAssert assertThat(BigRational actual) {
        return new BigRationalAssert(actual);
    }

    public static BigComplexAssert assertThat(BigComplex actual) {
        return new BigComplexAssert(actual);
    }

    public static QuantityValueAssert assertThat(QuantityValue actual) {
        return new QuantityValueAssert(actual);
    }

    public static <L, R> EitherAssert<L, R> assertThat(Either<L, R> actual) {
        return new EitherAssert<>(actual);
    }
}

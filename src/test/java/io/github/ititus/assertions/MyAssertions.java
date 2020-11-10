package io.github.ititus.assertions;

import io.github.ititus.math.number.BigRational;
import io.github.ititus.si.quantity.value.QuantityValue;
import org.assertj.core.api.InstanceOfAssertFactories;

public class MyAssertions implements InstanceOfAssertFactories {

    public static BigRationalAssert assertThat(BigRational actual) {
        return new BigRationalAssert(actual);
    }

    public static QuantityValueAssert assertThat(QuantityValue actual) {
        return new QuantityValueAssert(actual);
    }

    protected MyAssertions() {
    }
}

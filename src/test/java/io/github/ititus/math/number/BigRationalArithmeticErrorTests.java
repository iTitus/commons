package io.github.ititus.math.number;

import io.github.ititus.converter.NumberConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BigRationalArithmeticErrorTests {

    static Stream<Arguments> test_sqrt_negative() {
        return Stream.of(
                arguments(-1)
        );
    }

    static Stream<Arguments> test_ln_not_positive() {
        return Stream.of(
                arguments(0),
                arguments(-1)
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_sqrt_negative(@ConvertWith(NumberConverter.class) BigRational r) {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(r::sqrt);
    }

    @ParameterizedTest
    @MethodSource
    void test_ln_not_positive(@ConvertWith(NumberConverter.class) BigRational r) {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(r::ln);
    }

}

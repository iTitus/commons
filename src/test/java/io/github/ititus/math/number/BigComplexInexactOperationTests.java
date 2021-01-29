package io.github.ititus.math.number;

import io.github.ititus.converter.CommonsArgumentConverter;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigComplex.of;
import static io.github.ititus.math.number.BigComplexConstants.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.data.Offset.strictOffset;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BigComplexInexactOperationTests {

    private static final BigComplex ONE_I_TWO = of("1 + 2i");

    private static final Offset<BigRational> EPS = strictOffset(BigRational.of("1e-33"));

    static Stream<Arguments> test_abs() {
        return Stream.of(
                arguments(ONE, 1),
                arguments(MINUS_ONE, 1),
                arguments(I, 1),
                arguments(MINUS_I, 1),
                arguments(ONE_I_TWO, "2.236067977499789696409173668731276235440618359611525724270897245")
        );
    }

    static Stream<Arguments> test_angle() {
        return Stream.of(
                arguments(ONE, ZERO),
                arguments(MINUS_ONE, PI),
                arguments(I, PI.divide(2)),
                arguments(MINUS_I, PI.divide(-2)),
                arguments(ONE_I_TWO, "1.107148717794090503017065460178537040070047645401432646676539207")
        );
    }

    static Stream<Arguments> test_unit() {
        return Stream.of(
                arguments(ONE, ONE),
                arguments(MINUS_ONE, MINUS_ONE),
                arguments(I, I),
                arguments(MINUS_I, MINUS_I),
                arguments(ONE_I_TWO, "0.4472135954999579392818347337462552470881236719223051448541794491" +
                        " + 0.8944271909999158785636694674925104941762473438446102897083588982 i")
        );
    }

    static Stream<Arguments> test_sqrt() {
        return Stream.of(
                arguments(ONE, ONE),
                arguments(MINUS_ONE, I),
                arguments(I, "0.7071067811865475244008443621048490392848359376884740365883398690" +
                        " + 0.7071067811865475244008443621048490392848359376884740365883398690 i"),
                arguments(MINUS_I, "0.7071067811865475244008443621048490392848359376884740365883398690" +
                        " - 0.7071067811865475244008443621048490392848359376884740365883398690 i"),
                arguments(ONE_I_TWO, "1.272019649514068964252422461737491491715608041840096248616640383" +
                        " + 0.7861513777574232860695585858429589295231220578377232376649019701 i")
        );
    }

    static Stream<Arguments> test_exp() {
        return Stream.of(
                arguments(ZERO, ONE),
                arguments(ONE, "2.718281828459045235360287471352662497757247093699959574966967628"),
                arguments(MINUS_ONE, "0.3678794411714423215955237701614608674458111310317678345078368017"),
                arguments(I, "0.5403023058681397174009366074429766037323104206179222276700972554" +
                        " + 0.8414709848078965066525023216302989996225630607983710656727517100 i"),
                arguments(MINUS_I, "0.5403023058681397174009366074429766037323104206179222276700972554" +
                        " - 0.8414709848078965066525023216302989996225630607983710656727517100 i"),
                arguments(ONE_I_TWO, "-1.131204383756813638431255255510794710628867995826525750217721910" +
                        " + 2.471726672004818927616930893551664532736190369241008184200758835 i")
        );
    }

    static Stream<Arguments> test_ln() {
        return Stream.of(
                arguments(ONE, ZERO),
                arguments(MINUS_ONE, "3.141592653589793238462643383279502884197169399375105820974944592 i"),
                arguments(I, "1.570796326794896619231321691639751442098584699687552910487472296 i"),
                arguments(MINUS_I, "-1.570796326794896619231321691639751442098584699687552910487472296 i"),
                arguments(ONE_I_TWO, "0.8047189562170501873003796666130938197628006771342588609563239457" +
                        " + 1.107148717794090503017065460178537040070047645401432646676539207 i")
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_abs(@ConvertWith(CommonsArgumentConverter.class) BigComplex z,
                  @ConvertWith(CommonsArgumentConverter.class) BigRational expected) {
        BigRational actual = z.abs();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @MethodSource
    void test_angle(@ConvertWith(CommonsArgumentConverter.class) BigComplex z,
                    @ConvertWith(CommonsArgumentConverter.class) BigRational expected) {
        BigRational actual = z.angle();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_angle_zero() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(ZERO::angle);
    }

    @ParameterizedTest
    @MethodSource
    void test_unit(@ConvertWith(CommonsArgumentConverter.class) BigComplex z,
                   @ConvertWith(CommonsArgumentConverter.class) BigComplex expected) {
        BigComplex actual = z.unit();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_unit_zero() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(ZERO::unit);
    }

    @ParameterizedTest
    @MethodSource
    void test_sqrt(@ConvertWith(CommonsArgumentConverter.class) BigComplex z,
                   @ConvertWith(CommonsArgumentConverter.class) BigComplex expected) {
        BigComplex actual = z.sqrt();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @MethodSource
    void test_exp(@ConvertWith(CommonsArgumentConverter.class) BigComplex z,
                  @ConvertWith(CommonsArgumentConverter.class) BigComplex expected) {
        BigComplex actual = z.exp();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @MethodSource
    void test_ln(@ConvertWith(CommonsArgumentConverter.class) BigComplex z,
                 @ConvertWith(CommonsArgumentConverter.class) BigComplex expected) {
        BigComplex actual = z.ln();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @Test
    void test_ln_zero() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(ZERO::ln);
    }
}

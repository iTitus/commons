package io.github.ititus.math.function;

import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;

import java.math.MathContext;

public class NewtonApproximator {

    private static final BigRational ATAN_APPROX_FACTOR = BigRational.ofExp(28, -2);
    private static final int MAX_ITER = 1_000;

    public static BigRational atan(BigRational x) {
        return atan(x, MathContext.DECIMAL128);
    }

    public static BigRational atan(BigRational x, MathContext mc) {
        int precision = mc.getPrecision();
        if (precision == 0) {
            throw new ArithmeticException();
        }
        BigRational minDiff = BigRational.ofExp(1, -precision);

        int n = 0;
        BigRational x_n = x.divide(BigRationalConstants.ONE.add(ATAN_APPROX_FACTOR.multiply(x.squared()))).round(mc);
        BigRational diff;
        do {
            BigRational tan = x_n.tan();
            diff = tan.subtract(x).divide(BigRationalConstants.ONE.add(tan.squared()));
            x_n = x_n.subtract(diff)/*.round(mc)*/;
        } while (++n < MAX_ITER && diff.abs().compareTo(minDiff) > 0);

        return x_n.round(mc);
    }
}

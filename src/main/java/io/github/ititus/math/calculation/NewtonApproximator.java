package io.github.ititus.math.calculation;

import io.github.ititus.math.number.BigRational;

import java.math.MathContext;

import static io.github.ititus.math.number.BigRational.of;
import static io.github.ititus.math.number.BigRational.ofExp;
import static io.github.ititus.math.number.BigRationalConstants.ONE;

public class NewtonApproximator {

    private static final int MAX_ITER = 1_000;

    public static BigRational atan(BigRational x) {
        return atan(x, MathContext.DECIMAL128);
    }

    public static BigRational atan(BigRational x, MathContext mc) {
        int precision = mc.getPrecision();
        if (precision == 0) {
            throw new ArithmeticException();
        }

        BigRational minDiff = ofExp(1, -(precision + 1));

        int maxPrecision = precision + 4;
        MathContext maxMc = new MathContext(maxPrecision, mc.getRoundingMode());
        BigRational originalX = x;
        x = x.round(maxMc);

        int currentPrecision = 17;
        BigRational x_n = of(Math.atan(x.doubleValue()));

        boolean convergence = false;
        for (int n = 0; n < MAX_ITER; n++) {
            currentPrecision *= 3;
            MathContext currentMc;
            if (currentPrecision > maxPrecision) {
                currentPrecision = maxPrecision;
                currentMc = maxMc;
            } else {
                currentMc = new MathContext(currentPrecision, mc.getRoundingMode());
            }

            BigRational tan = x_n.tan().round(currentMc);
            BigRational diff = tan.subtract(x).divide(ONE.add(tan.squared())).round(currentMc);
            if (currentPrecision >= maxPrecision && diff.abs().compareTo(minDiff) < 0) {
                convergence = true;
                System.out.println("atan did converge after " + n + " iteration(s) for input " + originalX);
                break;
            }

            x_n = x_n.subtract(diff);
        }

        if (!convergence) {
            System.out.println("atan did not converge after " + MAX_ITER + " iterations for input " + originalX);
        }

        return x_n.round(mc);
    }
}

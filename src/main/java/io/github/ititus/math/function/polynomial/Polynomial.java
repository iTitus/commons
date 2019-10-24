package io.github.ititus.math.function.polynomial;

import io.github.ititus.math.function.ComplexFunction;
import io.github.ititus.math.function.Power;
import io.github.ititus.math.function.Product;
import io.github.ititus.math.function.Sum;
import io.github.ititus.math.function.constant.Constant;
import io.github.ititus.math.number.BigComplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Polynomial {

    private Polynomial() {
    }

    public static ComplexFunction of(BigComplex... constants) {
        if (Arrays.stream(constants).allMatch(c -> c.equals(BigComplex.ZERO))) {
            return Constant.zero();
        }

        List<ComplexFunction> terms = new ArrayList<>();

        for (int i = 0; i < constants.length; i++) {
            BigComplex z = constants[i];
            ComplexFunction m = Power.of(i);
            if (z.equals(BigComplex.ZERO)) {
                terms.add(Constant.zero());
            } else if (z.equals(BigComplex.ONE)) {
                terms.add(m);
            }
            terms.add(Product.of(Constant.of(z), m));
        }

        return Sum.of(terms.toArray(ComplexFunction[]::new));
    }
}

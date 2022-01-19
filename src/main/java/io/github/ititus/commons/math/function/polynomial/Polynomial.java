package io.github.ititus.commons.math.function.polynomial;

import io.github.ititus.commons.math.function.*;
import io.github.ititus.commons.math.function.constant.Constant;
import io.github.ititus.commons.math.number.BigComplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Polynomial {

    private Polynomial() {
    }

    public static ComplexFunction of(BigComplex... constants) {
        if (Arrays.stream(constants).allMatch(BigComplex::isZero)) {
            return Constant.zero();
        }

        List<ComplexFunction> terms = new ArrayList<>();
        for (int i = 0; i < constants.length; i++) {
            BigComplex z = constants[i];
            ComplexFunction m = Power.of(i);
            if (z.isZero()) {
                terms.add(Constant.zero());
            } else if (z.isOne()) {
                terms.add(m);
            }
            terms.add(Product.of(Constant.of(z), m));
        }

        return Sum.of(terms.toArray(ComplexFunction[]::new));
    }

    public static ComplexFunction ofRoots(BigComplex... roots) {
        ComplexFunction[] terms = Arrays.stream(roots)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .map(e -> Power.of(Sum.of(Identity.get(), Constant.of(e.getKey().negate())), e.getValue()))
                .toArray(ComplexFunction[]::new);

        return Product.of(terms);
    }
}

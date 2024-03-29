package io.github.ititus.commons.math.function;

import io.github.ititus.commons.math.function.constant.Constant;
import io.github.ititus.commons.math.number.BigComplex;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.ititus.commons.math.number.BigComplexConstants.ONE;
import static io.github.ititus.commons.math.number.BigComplexConstants.ZERO;
import static java.util.function.Predicate.not;

public final class Sum extends ComplexFunction {

    private final ComplexFunction[] terms;

    private Sum(ComplexFunction... terms) {
        this.terms = terms;
    }

    public static ComplexFunction of(ComplexFunction... terms) {
        // associativity & neutral element
        terms = Arrays.stream(terms)
                .filter(not(ComplexFunction::isZero))
                .flatMap(f -> f instanceof Sum ? Arrays.stream(((Sum) f).terms) : Stream.of(f))
                .toArray(ComplexFunction[]::new);

        // short circuit if too few terms
        if (terms.length == 0) {
            return Constant.zero();
        } else if (terms.length == 1) {
            return terms[0];
        }

        // combine constant terms
        BigComplex constant = Arrays.stream(terms)
                .filter(ComplexFunction::isConstant)
                .map(ComplexFunction::getConstant)
                .reduce(ZERO, BigComplex::add);

        Stream<ComplexFunction> stream = Arrays.stream(terms)
                .filter(not(ComplexFunction::isConstant));
        if (!constant.isZero()) {
            stream = Stream.concat(Stream.of(Constant.of(constant)), stream);
        }
        terms = stream.toArray(ComplexFunction[]::new);

        // short circuit if too few terms
        if (terms.length == 0) {
            return Constant.zero();
        } else if (terms.length == 1) {
            return terms[0];
        }

        // distributivity
        Map<ComplexFunction, BigComplex> map = Arrays.stream(terms)
                .collect(
                        Collectors.groupingBy(
                                f -> {
                                    if (f instanceof Product) {
                                        Product p = (Product) f;
                                        ComplexFunction[] arr1 = p.getTerms();
                                        if (arr1[0].isConstant()) {
                                            ComplexFunction[] arr2 = new ComplexFunction[arr1.length - 1];
                                            System.arraycopy(arr1, 1, arr2, 0, arr2.length);
                                            return Product.of(arr2);
                                        }
                                    }
                                    return f;
                                },
                                LinkedHashMap::new,
                                Collectors.reducing(ZERO, f -> {
                                    if (f instanceof Product) {
                                        Product p = (Product) f;
                                        ComplexFunction first = p.getTerms()[0];
                                        if (first.isConstant()) {
                                            return first.getConstant();
                                        }
                                    }
                                    return ONE;
                                }, BigComplex::add)
                        )
                );

        List<ComplexFunction> termList = new ArrayList<>();
        map.forEach((f, z) -> termList.add(Product.of(Constant.of(z), f)));
        terms = termList.toArray(ComplexFunction[]::new);

        return new Sum(terms);
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return Arrays.stream(terms)
                .map(f -> f.evaluate(z))
                .reduce(ZERO, BigComplex::add);
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        return of(Arrays.stream(terms)
                .map(f -> f.derivative(n))
                .toArray(ComplexFunction[]::new));
    }

    public ComplexFunction[] getTerms() {
        return terms;
    }

    @Override
    public String toString(boolean inner) {
        return (inner ? "(" : "")
                + Arrays.stream(terms).map(f -> f.toString(true)).collect(Collectors.joining(" + "))
                + (inner ? ")" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Sum)) {
            return false;
        }
        Sum s = (Sum) o;
        if (terms.length != s.terms.length) {
            return false;
        }
        return Arrays.asList(terms).containsAll(Arrays.asList(s.terms));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(Stream.concat(Stream.of(Sum.class), Arrays.stream(terms)).mapToInt(Object::hashCode).sorted().toArray());
    }
}

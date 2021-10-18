package io.github.ititus.math.function;

import io.github.ititus.math.function.constant.Constant;
import io.github.ititus.math.number.BigComplex;
import io.github.ititus.math.permutation.Permutations;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.ititus.math.number.BigComplexConstants.ONE;
import static io.github.ititus.math.number.BigComplexConstants.ZERO;
import static java.util.function.Predicate.not;

public final class Product extends ComplexFunction {

    private final ComplexFunction[] terms;

    private Product(ComplexFunction... terms) {
        this.terms = terms;
    }

    public static ComplexFunction of(ComplexFunction... terms) {
        // annihilator element
        if (Arrays.stream(terms).anyMatch(ComplexFunction::isZero)) {
            return Constant.zero();
        }

        // associativity & neutral element
        terms = Arrays.stream(terms)
                .filter(not(ComplexFunction::isOne))
                .flatMap(f -> f instanceof Product ? Arrays.stream(((Product) f).terms) : Stream.of(f))
                .toArray(ComplexFunction[]::new);

        // short circuit if too few terms
        if (terms.length == 0) {
            return Constant.one();
        } else if (terms.length == 1) {
            return terms[0];
        }

        // combine constant & monomial terms
        Constant c = Constant.of(
                Arrays.stream(terms)
                        .filter(ComplexFunction::isConstant)
                        .map(ComplexFunction::getConstant)
                        .reduce(ONE, BigComplex::multiply)
        );
        ComplexFunction p = Power.of(
                Arrays.stream(terms)
                        .filter(f -> (f instanceof Power && ((Power) f).getBase().isIdentity() && ((Power) f).getExponent().isConstant()) || f.isIdentity())
                        .map(f -> f instanceof Power ? ((Power) f).getExponent().getConstant() : ONE)
                        .reduce(ZERO, BigComplex::add)
        );
        Stream<ComplexFunction> stream = Arrays.stream(terms)
                .filter(not(ComplexFunction::isConstant))
                .filter(not(f -> (f instanceof Power && ((Power) f).getBase().isIdentity() && ((Power) f).getExponent().isConstant()) || f.isIdentity()));
        if (!p.isOne()) {
            stream = Stream.concat(Stream.of(p), stream);
        }
        if (!c.isOne()) {
            stream = Stream.concat(Stream.of(c), stream);
        }
        terms = stream.toArray(ComplexFunction[]::new);

        // short circuit if too few terms
        if (terms.length == 0) {
            return Constant.one();
        } else if (terms.length == 1) {
            return terms[0];
        }

        return new Product(terms);
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return Arrays.stream(terms)
                .map(f -> f.evaluate(z))
                .reduce(ONE, BigComplex::multiply);
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        return Sum.of(
                Permutations.getAllArraysWithSum(terms.length, n)
                        .stream()
                        .map(multiIndex -> {
                                    ComplexFunction[] product = new ComplexFunction[terms.length + 1];
                                    product[0] = Constant.multinomial(multiIndex);
                                    for (int i = 0; i < terms.length; i++) {
                                        product[i + 1] = terms[i].derivative(multiIndex[i]);
                                    }
                                    return of(product);
                                }
                        )
                        .toArray(ComplexFunction[]::new)
        );
    }

    public ComplexFunction[] getTerms() {
        return terms;
    }

    @Override
    protected String toString(boolean inner) {
        return Arrays.stream(terms)
                .map(f -> f.toString(true))
                .collect(Collectors.joining(" * "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Product)) {
            return false;
        }
        Product p = (Product) o;
        if (terms.length != p.terms.length) {
            return false;
        }
        return Arrays.asList(terms).containsAll(Arrays.asList(p.terms));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(Stream.concat(Stream.of(Sum.class), Arrays.stream(terms)).mapToInt(Object::hashCode).sorted().toArray());
    }
}

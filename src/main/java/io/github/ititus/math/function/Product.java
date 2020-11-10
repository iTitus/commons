package io.github.ititus.math.function;

import io.github.ititus.math.function.constant.Constant;
import io.github.ititus.math.number.BigComplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        terms = Arrays.stream(terms).filter(not(ComplexFunction::isOne)).flatMap(f -> f instanceof Product ?
                Arrays.stream(((Product) f).terms) : Stream.of(f)).toArray(ComplexFunction[]::new);

        // short circuit if too few terms
        if (terms.length == 0) {
            return Constant.one();
        } else if (terms.length == 1) {
            return terms[0];
        }

        // combine constant & monomial terms
        Constant c =
                Constant.of(Arrays.stream(terms).filter(ComplexFunction::isConstant).map(ComplexFunction::getConstant).reduce(BigComplex.ONE, BigComplex::multiply));
        ComplexFunction p =
                Power.of(Arrays.stream(terms).filter(f -> (f instanceof Power && ((Power) f).getBase().isIdentity() && ((Power) f).getExponent().isConstant()) || f.isIdentity()).map(f -> f instanceof Power ? ((Power) f).getExponent().getConstant() : BigComplex.ONE).reduce(BigComplex.ZERO, BigComplex::add));
        Stream<ComplexFunction> stream =
                Arrays.stream(terms).filter(not(ComplexFunction::isConstant)).filter(not(f -> (f instanceof Power && ((Power) f).getBase().isIdentity() && ((Power) f).getExponent().isConstant()) || f.isIdentity()));
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

    private static List<int[]> getAllArraysWithSum(int length, int sum) {
        if (sum < 0) {
            throw new IllegalArgumentException("length=" + length + " sum=" + sum);
        } else if (length == 0 && sum > 0) {
            return Collections.emptyList();
        } else if (sum == 0) {
            return Collections.singletonList(new int[length]);
        }

        List<int[]> l = new ArrayList<>();

        for (int s = 0; s <= sum; s++) {
            for (int[] sub : getAllArraysWithSum(length - 1, sum - s)) {
                int[] a = new int[length];
                a[0] = s;
                System.arraycopy(sub, 0, a, 1, sub.length);
                l.add(a);
            }
        }

        return l;
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return Arrays.stream(terms).map(f -> f.evaluate(z)).reduce(BigComplex.ONE, BigComplex::multiply);
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        return Sum.of(
                getAllArraysWithSum(terms.length, n)
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
        return Arrays.stream(terms).map(f -> f.toString(true)).collect(Collectors.joining(" * "));
    }

    @Override
    protected boolean equals0(ComplexFunction f) {
        if (!(f instanceof Product)) {
            return false;
        }
        Product p = (Product) f;
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

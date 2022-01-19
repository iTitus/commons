package io.github.ititus.commons.math.function;

import io.github.ititus.commons.math.function.constant.Constant;
import io.github.ititus.commons.math.function.exponential.Log;
import io.github.ititus.commons.math.number.BigComplex;
import io.github.ititus.commons.math.number.BigRational;

import java.util.Objects;
import java.util.stream.IntStream;

import static io.github.ititus.commons.math.number.BigComplexConstants.ONE;

public final class Power extends ComplexFunction {

    private final ComplexFunction base, exponent;

    private Power(ComplexFunction base, ComplexFunction exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    public static ComplexFunction of(int exponent) {
        return of(Identity.get(), exponent);
    }

    public static ComplexFunction of(ComplexFunction base, int exponent) {
        return of(base, Constant.of(exponent));
    }

    public static ComplexFunction of(long exponent) {
        return of(Identity.get(), exponent);
    }

    public static ComplexFunction of(ComplexFunction base, long exponent) {
        return of(base, Constant.of(exponent));
    }

    public static ComplexFunction of(BigRational exponent) {
        return of(Identity.get(), exponent);
    }

    public static ComplexFunction of(ComplexFunction base, BigRational exponent) {
        return of(base, Constant.of(exponent));
    }

    public static ComplexFunction of(BigComplex exponent) {
        return of(Identity.get(), exponent);
    }

    public static ComplexFunction of(ComplexFunction base, BigComplex exponent) {
        return of(base, Constant.of(exponent));
    }

    public static ComplexFunction of(ComplexFunction exponent) {
        return of(Identity.get(), exponent);
    }

    public static ComplexFunction of(ComplexFunction base, ComplexFunction exponent) {
        if (base.isOne() || exponent.isZero()) {
            return Constant.one();
        } else if (base.isZero()) {
            return Constant.zero();
        } else if (exponent.isOne()) {
            return base;
        } else if (exponent.isConstant()) {
            BigComplex c = exponent.getConstant();
            if (base.isConstant()) {
                return Constant.of(base.getConstant().pow(c));
            }

            if (c.isReal() && c.getReal().isNegative()) {
                return Rational.of(of(base, c.getReal().negate()));
            }
        }

        return new Power(base, exponent);
    }

    @Override
    public BigComplex evaluate(BigComplex z) {
        return base.evaluate(z).pow(exponent.evaluate(z));
    }

    @Override
    protected ComplexFunction derivative0(int n) {
        if (exponent.isConstant()) {
            if (base.isIdentity()) {
                BigComplex constant = exponent.getConstant();

                if (constant.isReal() && constant.getReal().isInt()) {
                    int e = constant.getReal().intValue();
                    if (n > e) {
                        return Constant.zero();
                    }
                    Constant product = Constant.of(IntStream.range(0, n).map(i -> e - i).reduce(1, (a, b) -> a * b));
                    return Product.of(product, of(base, Constant.of(e - n)));
                }

                Constant c = Constant.of(
                        IntStream.range(0, n)
                                .mapToObj(BigComplex::real)
                                .map(constant::subtract)
                                .reduce(ONE, BigComplex::multiply)
                );
                return Product.of(c, of(base, constant.subtract(BigComplex.real(n))));
            }

            return Product.of(exponent, base.derivative(), of(base, Sum.of(exponent, Constant.minusOne()))).derivative(n - 1);
        }

        return Product.of(of(base, Sum.of(exponent, Constant.minusOne())), Sum.of(Product.of(base.derivative(),
                exponent), Product.of(base, Log.of(base), exponent.derivative()))).derivative(n - 1);
    }

    public ComplexFunction getBase() {
        return base;
    }

    public ComplexFunction getExponent() {
        return exponent;
    }

    @Override
    public String toString(boolean inner) {
        String s = base.toString(true);
        s += "^";
        s += exponent.toString(true);
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Power)) {
            return false;
        }
        Power p = (Power) o;
        return base.equals(p.base) && exponent.equals(p.exponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Power.class, base, exponent);
    }
}

package io.github.ititus.math;

import io.github.ititus.math.function.*;
import io.github.ititus.math.function.constant.Constant;
import io.github.ititus.math.function.trigonometric.Cos;
import io.github.ititus.math.function.trigonometric.Sin;
import io.github.ititus.math.number.BigComplexConstants;
import io.github.ititus.math.number.BigRationalConstants;

public class Deriving {

    public static void main(String[] args) {
        ComplexFunction f = Product.of(Power.of(2), Sin.of());
        System.out.println("f(z) = " + f);

        /*RationalStatistics s = RationalStatistics.arithmetic();
        for (int i = 0; i < 1_000; i++) {
            long time = System.nanoTime();
            ComplexFunction d = f.derivative(1_000);
            s.add(BigRational.of(System.nanoTime() - time));

            d.isConstant();
            System.out.println(i + ": min=" + s.min() + " | max=" + s.max() + " | avg=" + s.average());
        }*/

        long time = System.nanoTime();
        ComplexFunction d = f.derivative(1000);
        time = System.nanoTime() - time;

        System.out.println("f^(1000)(z) = " + d);
        System.out.println("Time: " + time / 1_000_000.0 + " ms");
        System.out.println();

        Constant c = Constant.of(BigRationalConstants.ONE_OVER_TWO);
        f = Sum.of(Product.of(c, Identity.get()), Product.of(Constant.minusOne(), Power.of(Sin.of(), 2)));
        System.out.println("f(z) = " + f);
        System.out.println("f'(z) = " + f.derivative());
        System.out.println();

        f = Sum.of(Power.of(Sum.of(Constant.one(), Product.of(Constant.minusOne(), Identity.get(), Sin.of())),
                BigRationalConstants.ONE_OVER_TWO), Product.of(Constant.minusOne(), Cos.of()));
        System.out.println("f(z) = " + f);
        System.out.println("f'(z) = " + f.derivative());
        System.out.println();

        f = Power.of(Sin.of(), 2);
        System.out.println("f(z) = " + f);
        System.out.println("f'(z) = " + f.derivative());
        System.out.println("f''(z) = " + f.derivative(2));
        System.out.println("f^(3)(z) = " + f.derivative(3));
        System.out.println("f^(4)(z) = " + f.derivative(4));
        System.out.println();

        System.out.println(BigComplexConstants.I.pow(BigComplexConstants.I));
    }
}

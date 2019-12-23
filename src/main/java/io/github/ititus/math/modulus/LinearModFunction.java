package io.github.ititus.math.modulus;

import io.github.ititus.math.number.BigIntegerMath;

import java.math.BigInteger;
import java.util.Objects;

public class LinearModFunction {

    private final BigInteger n, a, b;

    public LinearModFunction(long n, long a, long b) {
        this(BigIntegerMath.of(n), BigIntegerMath.of(a), BigIntegerMath.of(b));
    }

    public LinearModFunction(BigInteger n, BigInteger a, BigInteger b) {
        this.n = n;
        this.a = a.mod(n);
        this.b = b.mod(n);
    }

    public static LinearModFunction identity(long n) {
        return identity(BigIntegerMath.of(n));
    }

    public static LinearModFunction identity(BigInteger n) {
        return new LinearModFunction(n, BigInteger.ONE, BigInteger.ZERO);
    }

    public BigInteger getSlope() {
        return a;
    }

    public BigInteger getOffset() {
        return b;
    }

    public BigInteger getModulus() {
        return n;
    }

    public BigInteger apply(BigInteger i) {
        return a.multiply(i).add(b).mod(n);
    }

    public LinearModFunction inverse() {
        BigInteger inv = a.modInverse(n);
        return new LinearModFunction(n, inv, inv.multiply(b).negate());
    }

    public LinearModFunction compose(LinearModFunction before) {
        if (!n.equals(before.n)) {
            throw new RuntimeException();
        }
        return new LinearModFunction(n, a.multiply(before.a), a.multiply(before.b).add(b));
    }

    public LinearModFunction andThen(LinearModFunction after) {
        if (!n.equals(after.n)) {
            throw new RuntimeException();
        }
        return new LinearModFunction(n, after.a.multiply(a), after.a.multiply(b).add(after.b));
    }

    public LinearModFunction selfCompose(long pow) {
        return selfCompose(BigIntegerMath.of(pow));
    }

    public LinearModFunction selfCompose(BigInteger pow) {
        if (pow.signum() == 0) {
            return identity(n);
        } else if (pow.signum() < 0) {
            return inverse().selfCompose(pow.negate());
        } else if (BigInteger.ONE.equals(pow)) {
            return this;
        }

        BigInteger aModPow = a.modPow(pow, n);
        BigInteger oneMinusAModPow = BigInteger.ONE.subtract(aModPow);
        BigInteger oneMinusAModInv = BigInteger.ONE.subtract(a).modInverse(n);

        return new LinearModFunction(n, aModPow, b.multiply(oneMinusAModPow).multiply(oneMinusAModInv));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinearModFunction)) {
            return false;
        }
        LinearModFunction that = (LinearModFunction) o;
        return n.equals(that.n) && a.equals(that.a) && b.equals(that.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, a, b);
    }

    @Override
    public String toString() {
        return a + "*x + " + b + " % " + n;
    }
}

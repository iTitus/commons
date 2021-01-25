package io.github.ititus.math.number;

import java.math.BigInteger;
import java.util.Objects;

public final class ExtendedGdcBigIntegerResult {

    private final BigInteger gcd;
    private final BigInteger bezoutCoeffA;
    private final BigInteger bezoutCoeffB;

    ExtendedGdcBigIntegerResult(BigInteger gcd, BigInteger bezoutCoeffA, BigInteger bezoutCoeffB) {
        this.gcd = Objects.requireNonNull(gcd);
        this.bezoutCoeffA = Objects.requireNonNull(bezoutCoeffA);
        this.bezoutCoeffB = Objects.requireNonNull(bezoutCoeffB);
    }

    public BigInteger getGcd() {
        return gcd;
    }

    public BigInteger getBezoutCoeffA() {
        return bezoutCoeffA;
    }

    public BigInteger getBezoutCoeffB() {
        return bezoutCoeffB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExtendedGdcBigIntegerResult)) {
            return false;
        }
        ExtendedGdcBigIntegerResult that = (ExtendedGdcBigIntegerResult) o;
        return gcd.equals(that.gcd) && bezoutCoeffA.equals(that.bezoutCoeffA) && bezoutCoeffB.equals(that.bezoutCoeffB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gcd, bezoutCoeffA, bezoutCoeffB);
    }
}

package io.github.ititus.math.number;

import io.github.ititus.data.ArrayUtil;

public final class ExtendedGdcLongResult {

    private final long gcd;
    private final long bezoutCoeffA;
    private final long bezoutCoeffB;

    ExtendedGdcLongResult(long gcd, long bezoutCoeffA, long bezoutCoeffB) {
        this.gcd = gcd;
        this.bezoutCoeffA = bezoutCoeffA;
        this.bezoutCoeffB = bezoutCoeffB;
    }

    public long getGcd() {
        return gcd;
    }

    public long getBezoutCoeffA() {
        return bezoutCoeffA;
    }

    public long getBezoutCoeffB() {
        return bezoutCoeffB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExtendedGdcLongResult)) {
            return false;
        }
        ExtendedGdcLongResult that = (ExtendedGdcLongResult) o;
        return gcd == that.gcd && bezoutCoeffA == that.bezoutCoeffA && bezoutCoeffB == that.bezoutCoeffB;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(gcd, bezoutCoeffA, bezoutCoeffB);
    }
}

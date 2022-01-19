package io.github.ititus.commons.math.number;

import io.github.ititus.commons.data.ArrayUtil;

public final class ExtendedGdcIntResult {

    private final int gcd;
    private final int bezoutCoeffA;
    private final int bezoutCoeffB;

    ExtendedGdcIntResult(int gcd, int bezoutCoeffA, int bezoutCoeffB) {
        this.gcd = gcd;
        this.bezoutCoeffA = bezoutCoeffA;
        this.bezoutCoeffB = bezoutCoeffB;
    }

    public int getGcd() {
        return gcd;
    }

    public int getBezoutCoeffA() {
        return bezoutCoeffA;
    }

    public int getBezoutCoeffB() {
        return bezoutCoeffB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExtendedGdcIntResult)) {
            return false;
        }
        ExtendedGdcIntResult that = (ExtendedGdcIntResult) o;
        return gcd == that.gcd && bezoutCoeffA == that.bezoutCoeffA && bezoutCoeffB == that.bezoutCoeffB;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(gcd, bezoutCoeffA, bezoutCoeffB);
    }
}

package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.number.JavaMath;

public final class Vec2i implements Comparable<Vec2i> {

    private final int x, y;

    public Vec2i() {
        this(0, 0);
    }

    public Vec2i(int[] arr) {
        if (arr.length != 2) {
            throw new IllegalArgumentException("illegal array size");
        }

        this.x = arr[0];
        this.y = arr[1];
    }

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i negate() {
        return new Vec2i(-x, -y);
    }

    public Vec2i add(Vec2i o) {
        return new Vec2i(x + o.x, y + o.y);
    }

    public Vec2i subtract(Vec2i o) {
        return new Vec2i(x - o.x, y - o.y);
    }

    public Vec2i multiply(int n) {
        return new Vec2i(x * n, y * n);
    }

    public Vec2i divide(int n) {
        return new Vec2i(x / n, y / n);
    }

    public int dot(Vec2i o) {
        return x * o.x + y * o.y;
    }

    public Vec2i reduce() {
        return divide(JavaMath.gcd(x, y));
    }

    public Vec2i sgn() {
        return new Vec2i(JavaMath.sgn(x), JavaMath.sgn(y));
    }

    public int manhattanDistance() {
        return Math.abs(x) + Math.abs(y);
    }

    public int manhattanDistanceTo(Vec2i o) {
        return Math.abs(x - o.x) + Math.abs(y - o.y);
    }

    public boolean isDirectlyAdjacentTo(Vec2i o) {
        return manhattanDistanceTo(o) == 1;
    }

    public double length() {
        return Math.hypot(x, y);
    }

    @Deprecated(forRemoval = true)
    public double distance() {
        return Math.hypot(x, y);
    }

    public double distanceTo(Vec2i o) {
        return Math.hypot(x - o.x, y - o.y);
    }

    public int innerProduct(Vec2i o) {
        return x * o.x + y * o.y;
    }

    public Vec2i flipY() {
        if (y == 0) {
            return this;
        }

        return new Vec2i(x, -y);
    }

    public Vec2i flipX() {
        if (x == 0) {
            return this;
        }

        return new Vec2i(-x, y);
    }

    /**
     * Assumes a right-handed coordinate system (y is up).
     * Returns the counter-clockwise angle between the vector (1, 0) and this angle.
     * Always returns a positive angle in [0, 2pi).
     * When using a left-handed coordinate system the angle is clockwise.
     */
    public double getAngle() {
        double angle = Math.atan2(y, x);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }

        return angle;
    }

    /**
     * Assumes a right-handed coordinate system (y is up).
     * Returns the counter-clockwise angle between this vector and the given vector.
     * Always returns a positive angle in [0, 2pi).
     * When using a left-handed coordinate system the angle is clockwise.
     */
    public double getAngleTo(Vec2i o) {
        double angle = Math.atan2(o.y, o.x) - Math.atan2(y, x);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }

        return angle;
    }

    /**
     * Assumes a right-handed coordinate system (y is up).
     * Rotates clockwise by the given degrees, only multiples of 90° supported.
     * When using a left-handed coordinate system the angle is counter-clockwise.
     */
    public Vec2i rotateCW(int degrees) {
        if (degrees % 90 != 0) {
            throw new IllegalArgumentException();
        }

        switch (Math.floorMod(degrees / 90, 4)) {
            case 0:
                return this;
            case 1:
                //noinspection SuspiciousNameCombination
                return new Vec2i(y, -x);
            case 2:
                return new Vec2i(-x, -y);
            case 3:
                //noinspection SuspiciousNameCombination
                return new Vec2i(-y, x);
            default:
                throw new RuntimeException();
        }
    }

    /**
     * Assumes a right-handed coordinate system (y is up).
     * Rotates counter-clockwise by the given degrees, only multiples of 90° supported.
     * When using a left-handed coordinate system the angle is clockwise.
     */
    public Vec2i rotateCCW(int degrees) {
        if (degrees % 90 != 0) {
            throw new IllegalArgumentException();
        }

        switch (Math.floorMod(degrees / 90, 4)) {
            case 0:
                return this;
            case 1:
                //noinspection SuspiciousNameCombination
                return new Vec2i(-y, x);
            case 2:
                return new Vec2i(-x, -y);
            case 3:
                //noinspection SuspiciousNameCombination
                return new Vec2i(y, -x);
            default:
                throw new RuntimeException();
        }
    }

    @Deprecated(forRemoval = true)
    public int getX() {
        return x;
    }

    public int x() {
        return x;
    }

    @Deprecated(forRemoval = true)
    public int getY() {
        return y;
    }

    public int y() {
        return y;
    }

    @Override
    public int compareTo(Vec2i o) {
        int c = Integer.compare(x, o.x);
        return c != 0 ? c : Integer.compare(y, o.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Vec2i)) {
            return false;
        }
        Vec2i v = (Vec2i) o;
        return x == v.x && y == v.y;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}

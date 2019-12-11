package io.github.ititus.math.vector;

import io.github.ititus.math.number.JavaMath;

import java.util.Objects;

public final class Vec2i {

    private final int x, y;

    public Vec2i() {
        this(0, 0);
    }

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
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

    public Vec2i reduce() {
        return divide(JavaMath.gcd(x, y));
    }

    public int manhattanDistance() {
        return Math.abs(x) + Math.abs(y);
    }

    public int manhattanDistanceTo(Vec2i o) {
        return Math.abs(x - o.x) + Math.abs(y - o.y);
    }

    public double distance() {
        return Math.hypot(x, y);
    }

    public double distanceTo(Vec2i o) {
        return Math.hypot(x - o.x, y - o.y);
    }

    public int innerProduct(Vec2i o) {
        return x * o.x + y * o.y;
    }

    /**
     * In clockwise rotation, always returns positive angle in [0, 2pi).
     */
    public double getAngleTo(Vec2i o) {
        double angle = Math.atan2(o.y, o.x) - Math.atan2(y, x);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }

        return angle;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vec2i)) {
            return false;
        }
        Vec2i v = (Vec2i) o;
        return x == v.x && y == v.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}

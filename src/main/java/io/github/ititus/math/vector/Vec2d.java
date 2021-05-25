package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;

public final class Vec2d {

    private final double x;
    private final double y;

    public Vec2d() {
        this(0, 0);
    }

    public Vec2d(double[] arr) {
        if (arr.length != 2) {
            throw new IllegalArgumentException("illegal array size");
        }

        this.x = arr[0];
        this.y = arr[1];
    }

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Vec2d)) {
            return false;
        }

        Vec2d vec2d = (Vec2d) o;
        return Double.compare(vec2d.x, x) == 0 && Double.compare(vec2d.y, y) == 0;
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

package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.matrix.Mat2d;

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

    public Vec2d negate() {
        return new Vec2d(-x, -y);
    }

    public Vec2d add(Vec2d o) {
        return new Vec2d(x + o.x, y + o.y);
    }

    public Vec2d subtract(Vec2d o) {
        return new Vec2d(x - o.x, y - o.y);
    }

    public Vec2d multiply(int n) {
        return multiply((double) n);
    }

    public Vec2d multiply(float f) {
        return multiply((double) f);
    }

    public Vec2d multiply(double d) {
        return new Vec2d(x * d, y * d);
    }

    public Vec2d multiply(Mat2d o) {
        return new Vec2d(
                x * o.m11() + y * o.m21(),
                x * o.m12() + y * o.m22()
        );
    }

    public Vec2d divide(int n) {
        return divide((double) n);
    }

    public Vec2d divide(float f) {
        return divide((double) f);
    }

    public Vec2d divide(double d) {
        return new Vec2d(x / d, y / d);
    }

    public double dot(Vec2d o) {
        return x * o.x + y * o.y;
    }

    public double lengthSquared() {
        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vec2d normalize() {
        double lsq = lengthSquared();
        if (lsq == 1) {
            return this;
        }

        return divide(Math.sqrt(lsq));
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

package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.matrix.Mat2f;

public final class Vec2f {

    private final float x;
    private final float y;

    public Vec2f() {
        this(0, 0);
    }

    public Vec2f(float[] arr) {
        if (arr.length != 2) {
            throw new IllegalArgumentException("illegal array size");
        }

        this.x = arr[0];
        this.y = arr[1];
    }

    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2f add(Vec2f o) {
        return new Vec2f(x + o.x, y + o.y);
    }

    public Vec2f subtract(Vec2f o) {
        return new Vec2f(x - o.x, y - o.y);
    }

    public Vec2f multiply(int n) {
        return multiply((float) n);
    }

    public Vec2f multiply(float f) {
        return new Vec2f(x * f, y * f);
    }

    public Vec2f multiply(Mat2f o) {
        return new Vec2f(
                x * o.m11() + y * o.m21(),
                x * o.m12() + y * o.m22()
        );
    }

    public Vec2f divide(int n) {
        return divide((float) n);
    }

    public Vec2f divide(float f) {
        return new Vec2f(x / f, y / f);
    }

    public float dot(Vec2f o) {
        return x * o.x + y * o.y;
    }

    public double dotD(Vec2f o) {
        return (double) x * o.x + (double) y * o.y;
    }

    public double lengthD() {
        return Math.hypot(x, y);
    }

    public float length() {
        return (float) lengthD();
    }

    public Vec2f normalize() {
        return divide(length());
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Vec2f)) {
            return false;
        }

        Vec2f vec2f = (Vec2f) o;
        return Float.compare(vec2f.x, x) == 0 && Float.compare(vec2f.y, y) == 0;
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

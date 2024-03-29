package io.github.ititus.commons.math.vector;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.math.matrix.Mat2f;

import java.nio.FloatBuffer;

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

    public Vec2f negate() {
        return new Vec2f(-x, -y);
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

    public float lengthSquared() {
        return x * x + y * y;
    }

    public float length() {
        return (float) Math.hypot(x, y);
    }

    public Vec2f normalize() {
        float lsq = lengthSquared();
        if (lsq == 1) {
            return this;
        }

        return divide((float) Math.sqrt(lsq));
    }

    public void write(FloatBuffer buffer) {
        buffer.put(x).put(y);
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

package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;

public final class Vec2f {

    private final float x;
    private final float y;

    public Vec2f() {
        this(0, 0);
    }

    public Vec2f(float... arr) {
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

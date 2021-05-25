package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.quaternion.QuatF;

public final class Vec4f {

    private final float x;
    private final float y;
    private final float z;
    private final float w;

    public Vec4f() {
        this(0, 0, 0, 0);
    }

    public Vec4f(QuatF quatF) {
        this(quatF.x(), quatF.y(), quatF.z(), quatF.w());
    }

    public Vec4f(float[] arr) {
        if (arr.length != 4) {
            throw new IllegalArgumentException("illegal array size");
        }

        this.x = arr[0];
        this.y = arr[1];
        this.z = arr[2];
        this.w = arr[3];
    }

    public Vec4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public float z() {
        return z;
    }

    public float w() {
        return w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Vec4f)) {
            return false;
        }

        Vec4f vec4f = (Vec4f) o;
        return Float.compare(vec4f.x, x) == 0 && Float.compare(vec4f.y, y) == 0 && Float.compare(vec4f.z, z) == 0 && Float.compare(vec4f.w, w) == 0;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(x, y, z, w);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ')';
    }
}

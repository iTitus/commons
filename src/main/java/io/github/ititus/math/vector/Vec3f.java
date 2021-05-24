package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;

public final class Vec3f {

    private final float x;
    private final float y;
    private final float z;

    public Vec3f() {
        this(0, 0, 0);
    }

    public Vec3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Vec3f)) {
            return false;
        }

        Vec3f vec3f = (Vec3f) o;
        return Float.compare(vec3f.x, x) == 0 && Float.compare(vec3f.y, y) == 0 && Float.compare(vec3f.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ')';
    }
}

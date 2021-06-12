package io.github.ititus.math.quaternion;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.vector.Vec4f;

public final class Quatf {

    private final float x;
    private final float y;
    private final float z;
    private final float w;

    public Quatf(Vec4f vec4f) {
        this(vec4f.x(), vec4f.y(), vec4f.z(), vec4f.w());
    }

    public Quatf(float x, float y, float z, float w) {
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
        } else if (!(o instanceof Quatf)) {
            return false;
        }

        Quatf quatF = (Quatf) o;
        return Float.compare(quatF.x, x) == 0 && Float.compare(quatF.y, y) == 0 && Float.compare(quatF.z, z) == 0 && Float.compare(quatF.w, w) == 0;
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

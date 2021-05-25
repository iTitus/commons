package io.github.ititus.math.quaternion;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.vector.Vec4d;

public final class QuatD {

    private final double x;
    private final double y;
    private final double z;
    private final double w;

    public QuatD(Vec4d vec4d) {
        this(vec4d.x(), vec4d.y(), vec4d.z(), vec4d.w());
    }

    public QuatD(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public double w() {
        return w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof QuatD)) {
            return false;
        }

        QuatD quatD = (QuatD) o;
        return Double.compare(quatD.x, x) == 0 && Double.compare(quatD.y, y) == 0 && Double.compare(quatD.z, z) == 0 && Double.compare(quatD.w, w) == 0;
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

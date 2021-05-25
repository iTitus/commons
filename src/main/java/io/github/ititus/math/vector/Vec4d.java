package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.quaternion.QuatD;

public final class Vec4d {

    private final double x;
    private final double y;
    private final double z;
    private final double w;

    public Vec4d() {
        this(0, 0, 0, 0);
    }

    public Vec4d(QuatD quatD) {
        this(quatD.x(), quatD.y(), quatD.z(), quatD.w());
    }

    public Vec4d(double... arr) {
        if (arr.length != 4) {
            throw new IllegalArgumentException("illegal array size");
        }

        this.x = arr[0];
        this.y = arr[1];
        this.z = arr[2];
        this.w = arr[3];
    }

    public Vec4d(double x, double y, double z, double w) {
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
        } else if (!(o instanceof Vec4d)) {
            return false;
        }

        Vec4d vec4d = (Vec4d) o;
        return Double.compare(vec4d.x, x) == 0 && Double.compare(vec4d.y, y) == 0 && Double.compare(vec4d.z, z) == 0 && Double.compare(vec4d.w, w) == 0;
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

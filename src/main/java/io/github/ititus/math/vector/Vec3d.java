package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;

public final class Vec3d {

    private final double x;
    private final double y;
    private final double z;

    public Vec3d() {
        this(0, 0, 0);
    }

    public Vec3d(double... arr) {
        if (arr.length != 3) {
            throw new IllegalArgumentException("illegal array size");
        }

        this.x = arr[0];
        this.y = arr[1];
        this.z = arr[2];
    }

    public Vec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Vec3d)) {
            return false;
        }

        Vec3d vec3d = (Vec3d) o;
        return Double.compare(vec3d.x, x) == 0 && Double.compare(vec3d.y, y) == 0 && Double.compare(vec3d.z, z) == 0;
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

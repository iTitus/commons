package io.github.ititus.commons.math.vector;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.math.matrix.Mat3d;

public final class Vec3d {

    private final double x;
    private final double y;
    private final double z;

    public Vec3d() {
        this(0, 0, 0);
    }

    public Vec3d(double[] arr) {
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

    public Vec3d negate() {
        return new Vec3d(-x, -y, -z);
    }

    public Vec3d add(Vec3d o) {
        return new Vec3d(x + o.x, y + o.y, z + o.z);
    }

    public Vec3d subtract(Vec3d o) {
        return new Vec3d(x - o.x, y - o.y, z - o.z);
    }

    public Vec3d multiply(int n) {
        return multiply((double) n);
    }

    public Vec3d multiply(float f) {
        return multiply((double) f);
    }

    public Vec3d multiply(double d) {
        return new Vec3d(x * d, y * d, z * d);
    }

    public Vec3d multiply(Mat3d o) {
        return new Vec3d(
                x * o.m11() + y * o.m21() + z * o.m31(),
                x * o.m12() + y * o.m22() + z * o.m32(),
                x * o.m13() + y * o.m23() + z * o.m33()
        );
    }

    public Vec3d divide(int n) {
        return divide((double) n);
    }

    public Vec3d divide(float f) {
        return divide((double) f);
    }

    public Vec3d divide(double d) {
        return new Vec3d(x / d, y / d, z / d);
    }

    public double dot(Vec3d o) {
        return x * o.x + y * o.y + z * o.z;
    }

    public Vec3d cross(Vec3d o) {
        return new Vec3d(y * o.z - z * o.y, z * o.x - x * o.z, x * o.y - y * o.x);
    }

    public double lengthSquared() {
        return x * x + y * y + z * z;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vec3d normalize() {
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

    public double z() {
        return z;
    }

    public Vec2d xy() {
        return new Vec2d(x, y);
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

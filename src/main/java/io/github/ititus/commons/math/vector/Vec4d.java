package io.github.ititus.commons.math.vector;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.math.matrix.Mat4d;
import io.github.ititus.commons.math.quaternion.Quatd;

public final class Vec4d {

    private final double x;
    private final double y;
    private final double z;
    private final double w;

    public Vec4d() {
        this(0, 0, 0, 0);
    }

    public Vec4d(Quatd quatD) {
        this(quatD.x(), quatD.y(), quatD.z(), quatD.w());
    }

    public Vec4d(double[] arr) {
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

    public Vec4d negate() {
        return new Vec4d(-x, -y, -z, -w);
    }

    public Vec4d add(Vec4d o) {
        return new Vec4d(x + o.x, y + o.y, z + o.z, w + o.w);
    }

    public Vec4d subtract(Vec4d o) {
        return new Vec4d(x - o.x, y - o.y, z - o.z, w - o.w);
    }

    public Vec4d multiply(int n) {
        return multiply((double) n);
    }

    public Vec4d multiply(float f) {
        return multiply((double) f);
    }

    public Vec4d multiply(double d) {
        return new Vec4d(x * d, y * d, z * d, w * d);
    }

    public Vec4d multiply(Mat4d o) {
        return new Vec4d(
                x * o.m11() + y * o.m21() + z * o.m31() + w * o.m41(),
                x * o.m12() + y * o.m22() + z * o.m32() + w * o.m42(),
                x * o.m13() + y * o.m23() + z * o.m33() + w * o.m43(),
                x * o.m14() + y * o.m24() + z * o.m34() + w * o.m44()
        );
    }

    public Vec4d divide(int n) {
        return divide((double) n);
    }

    public Vec4d divide(float f) {
        return divide((double) f);
    }

    public Vec4d divide(double d) {
        return new Vec4d(x / d, y / d, z / d, w / d);
    }

    public double dot(Vec4d o) {
        return x * o.x + y * o.y + z * o.z + w * o.w;
    }

    public double lengthSquared() {
        return x * x + y * y + z * z + w * w;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vec4d normalize() {
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

    public double w() {
        return w;
    }

    public Vec2d xy() {
        return new Vec2d(x, y);
    }

    public Vec3d xyz() {
        return new Vec3d(x, y, z);
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

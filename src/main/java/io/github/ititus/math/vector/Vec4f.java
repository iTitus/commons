package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.matrix.Mat4f;
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

    public Vec4f add(Vec4f o) {
        return new Vec4f(x + o.x, y + o.y, z + o.z, w + o.w);
    }

    public Vec4f subtract(Vec4f o) {
        return new Vec4f(x - o.x, y - o.y, z - o.z, w - o.w);
    }

    public Vec4f multiply(int n) {
        return multiply((float) n);
    }

    public Vec4f multiply(float f) {
        return new Vec4f(x * f, y * f, z * f, w * f);
    }

    public Vec4f multiply(Mat4f o) {
        return new Vec4f(
                x * o.m11() + y * o.m21() + z * o.m31() + w * o.m41(),
                x * o.m12() + y * o.m22() + z * o.m32() + w * o.m42(),
                x * o.m13() + y * o.m23() + z * o.m33() + w * o.m43(),
                x * o.m14() + y * o.m24() + z * o.m34() + w * o.m44()
        );
    }

    public Vec4f divide(int n) {
        return divide((float) n);
    }

    public Vec4f divide(float f) {
        return new Vec4f(x / f, y / f, z / f, w / f);
    }

    public float dot(Vec4f o) {
        return x * o.x + y * o.y + z * o.z + w * o.w;
    }

    public double dotD(Vec4f o) {
        return (double) x * o.x + (double) y * o.y + (double) z * o.z + (double) w * o.w;
    }

    public double lengthD() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public float length() {
        return (float) lengthD();
    }

    public Vec4f normalize() {
        return divide(length());
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

    public Vec2f xy() {
        return new Vec2f(x, y);
    }

    public Vec3f xyz() {
        return new Vec3f(x, y, z);
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

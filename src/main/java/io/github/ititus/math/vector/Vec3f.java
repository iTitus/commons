package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.matrix.Mat3f;

import java.nio.FloatBuffer;

public final class Vec3f {

    private final float x;
    private final float y;
    private final float z;

    public Vec3f() {
        this(0, 0, 0);
    }

    public Vec3f(float[] arr) {
        if (arr.length != 3) {
            throw new IllegalArgumentException("illegal array size");
        }

        this.x = arr[0];
        this.y = arr[1];
        this.z = arr[2];
    }

    public Vec3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3f negate() {
        return new Vec3f(-x, -y, -z);
    }

    public Vec3f add(Vec3f o) {
        return new Vec3f(x + o.x, y + o.y, z + o.z);
    }

    public Vec3f subtract(Vec3f o) {
        return new Vec3f(x - o.x, y - o.y, z - o.z);
    }

    public Vec3f multiply(int n) {
        return multiply((float) n);
    }

    public Vec3f multiply(float f) {
        return new Vec3f(x * f, y * f, z * f);
    }

    public Vec3f multiply(Mat3f o) {
        return new Vec3f(
                x * o.m11() + y * o.m21() + z * o.m31(),
                x * o.m12() + y * o.m22() + z * o.m32(),
                x * o.m13() + y * o.m23() + z * o.m33()
        );
    }

    public Vec3f divide(int n) {
        return divide((float) n);
    }

    public Vec3f divide(float f) {
        return new Vec3f(x / f, y / f, z / f);
    }

    public float dot(Vec3f o) {
        return x * o.x + y * o.y + z * o.z;
    }


    public Vec3f cross(Vec3f o) {
        return new Vec3f(y * o.z - z * o.y, z * o.x - x * o.z, x * o.y - y * o.x);
    }

    public float lengthSquared() {
        return x * x + y * y + z * z;
    }

    public float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    public Vec3f normalize() {
        float lsq = lengthSquared();
        if (lsq == 1) {
            return this;
        }

        return divide((float) Math.sqrt(lsq));
    }

    public void write(FloatBuffer buffer) {
        buffer.put(x).put(y).put(z);
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

    public Vec2f xy() {
        return new Vec2f(x, y);
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

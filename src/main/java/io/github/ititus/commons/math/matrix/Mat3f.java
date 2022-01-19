package io.github.ititus.commons.math.matrix;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.math.vector.Vec3f;

import java.nio.FloatBuffer;

public final class Mat3f {

    private final float m11, m12, m13;
    private final float m21, m22, m23;
    private final float m31, m32, m33;

    public Mat3f(float m11, float m12, float m13, float m21, float m22, float m23, float m31, float m32, float m33) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }

    public static Mat3f zero() {
        return new Mat3f(
                0, 0, 0,
                0, 0, 0,
                0, 0, 0
        );
    }

    public static Mat3f identity() {
        return new Mat3f(
                1, 0, 0,
                0, 1, 0,
                0, 0, 1
        );
    }

    public static Mat3f fromRows(Vec3f r1, Vec3f r2, Vec3f r3) {
        return new Mat3f(
                r1.x(), r1.y(), r1.z(),
                r2.x(), r2.y(), r2.z(),
                r3.x(), r3.y(), r3.z()
        );
    }

    public static Mat3f fromCols(Vec3f c1, Vec3f c2, Vec3f c3) {
        return new Mat3f(
                c1.x(), c2.x(), c3.x(),
                c1.y(), c2.y(), c3.y(),
                c1.z(), c2.z(), c3.z()
        );
    }

    public Mat3f transpose() {
        return new Mat3f(
                m11, m21, m31,
                m12, m22, m32,
                m13, m23, m33
        );
    }

    public Mat3f negate() {
        return new Mat3f(
                -m11, -m12, -m13,
                -m21, -m22, -m23,
                -m31, -m32, -m33
        );
    }

    public Mat3f add(Mat3f o) {
        return new Mat3f(
                m11 + o.m11, m12 + o.m12, m13 + o.m13,
                m21 + o.m21, m22 + o.m22, m23 + o.m23,
                m31 + o.m31, m32 + o.m32, m33 + o.m33
        );
    }

    public Mat3f subtract(Mat3f o) {
        return new Mat3f(
                m11 - o.m11, m12 - o.m12, m13 - o.m13,
                m21 - o.m21, m22 - o.m22, m23 - o.m23,
                m31 - o.m31, m32 - o.m32, m33 - o.m33
        );
    }

    public Mat3f multiply(int n) {
        return multiply((float) n);
    }

    public Mat3f multiply(float f) {
        return new Mat3f(
                m11 * f, m12 * f, m13 * f,
                m21 * f, m22 * f, m23 * f,
                m31 * f, m32 * f, m33 * f
        );
    }

    public Vec3f multiply(Vec3f o) {
        return new Vec3f(
                m11 * o.x() + m12 * o.y() + m13 * o.z(),
                m21 * o.x() + m22 * o.y() + m23 * o.z(),
                m31 * o.x() + m32 * o.y() + m33 * o.z()
        );
    }

    public Mat3f multiply(Mat3f o) {
        return new Mat3f(
                m11 * o.m11 + m12 * o.m21 + m13 * o.m31, m11 * o.m12 + m12 * o.m22 + m13 * o.m32, m11 * o.m13 + m12 * o.m23 + m13 * o.m33,
                m21 * o.m11 + m22 * o.m21 + m23 * o.m31, m21 * o.m12 + m22 * o.m22 + m23 * o.m32, m21 * o.m13 + m22 * o.m23 + m23 * o.m33,
                m31 * o.m11 + m32 * o.m21 + m33 * o.m31, m31 * o.m12 + m32 * o.m22 + m33 * o.m32, m31 * o.m13 + m32 * o.m23 + m33 * o.m33
        );
    }

    public Mat3f divide(int n) {
        return divide((float) n);
    }

    public Mat3f divide(float f) {
        return new Mat3f(
                m11 / f, m12 / f, m13 / f,
                m21 / f, m22 / f, m23 / f,
                m31 / f, m32 / f, m33 / f
        );
    }

    public float trace() {
        return m11 + m22 + m33;
    }

    public float determinant() {
        return m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32
                - m13 * m22 * m31 - m12 * m21 * m33 - m11 * m23 * m32;
    }

    public void write(FloatBuffer buffer) {
        buffer.put(m11).put(m21).put(m31);
        buffer.put(m12).put(m22).put(m32);
        buffer.put(m13).put(m23).put(m33);
    }

    public float m(int row, int col) {
        if (row < 0 || row >= 3) {
            throw new IllegalArgumentException("row out of bounds");
        } else if (col < 0 || col >= 3) {
            throw new IllegalArgumentException("col out of bounds");
        }

        switch (row) {
            case 0:
                switch (col) {
                    case 0:
                        return m11;
                    case 1:
                        return m12;
                    case 2:
                        return m13;
                }
            case 1:
                switch (col) {
                    case 0:
                        return m21;
                    case 1:
                        return m22;
                    case 2:
                        return m23;
                }
            case 2:
                switch (col) {
                    case 0:
                        return m31;
                    case 1:
                        return m32;
                    case 2:
                        return m33;
                }
        }

        throw new AssertionError();
    }

    public Vec3f row(int row) {
        if (row < 0 || row >= 3) {
            throw new IllegalArgumentException("row out of bounds");
        }

        switch (row) {
            case 0:
                return new Vec3f(m11, m12, m13);
            case 1:
                return new Vec3f(m21, m22, m23);
            case 2:
                return new Vec3f(m31, m32, m33);
        }

        throw new AssertionError();
    }

    public Vec3f col(int col) {
        if (col < 0 || col >= 3) {
            throw new IllegalArgumentException("col out of bounds");
        }

        switch (col) {
            case 0:
                return new Vec3f(m11, m21, m31);
            case 1:
                return new Vec3f(m21, m22, m32);
            case 2:
                return new Vec3f(m13, m23, m33);
        }

        throw new AssertionError();
    }

    public float m11() {
        return m11;
    }

    public float m12() {
        return m12;
    }

    public float m13() {
        return m13;
    }

    public float m21() {
        return m21;
    }

    public float m22() {
        return m22;
    }

    public float m23() {
        return m23;
    }

    public float m31() {
        return m31;
    }

    public float m32() {
        return m32;
    }

    public float m33() {
        return m33;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Mat3f)) {
            return false;
        }

        Mat3f mat3f = (Mat3f) o;
        return Float.compare(mat3f.m11, m11) == 0 && Float.compare(mat3f.m12, m12) == 0 && Float.compare(mat3f.m13, m13) == 0 && Float.compare(mat3f.m21, m21) == 0 && Float.compare(mat3f.m22, m22) == 0 && Float.compare(mat3f.m23, m23) == 0 && Float.compare(mat3f.m31, m31) == 0 && Float.compare(mat3f.m32, m32) == 0 && Float.compare(mat3f.m33, m33) == 0;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(m11, m12, m13, m21, m22, m23, m31, m32, m33);
    }

    @Override
    public String toString() {
        return "Mat3f{" +
                "m11=" + m11 +
                ", m12=" + m12 +
                ", m13=" + m13 +
                ", m21=" + m21 +
                ", m22=" + m22 +
                ", m23=" + m23 +
                ", m31=" + m31 +
                ", m32=" + m32 +
                ", m33=" + m33 +
                '}';
    }
}

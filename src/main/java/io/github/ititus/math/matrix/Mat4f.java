package io.github.ititus.math.matrix;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.vector.Vec3f;
import io.github.ititus.math.vector.Vec4f;

import java.nio.FloatBuffer;

public final class Mat4f {

    private final float m11, m12, m13, m14;
    private final float m21, m22, m23, m24;
    private final float m31, m32, m33, m34;
    private final float m41, m42, m43, m44;

    public Mat4f(float m11, float m12, float m13, float m14, float m21, float m22, float m23, float m24, float m31, float m32, float m33, float m34, float m41, float m42, float m43, float m44) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m14 = m14;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m24 = m24;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.m34 = m34;
        this.m41 = m41;
        this.m42 = m42;
        this.m43 = m43;
        this.m44 = m44;
    }

    public static Mat4f zero() {
        return new Mat4f(
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
    }

    public static Mat4f identity() {
        return new Mat4f(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
    }

    public static Mat4f fromRows(Vec4f r1, Vec4f r2, Vec4f r3, Vec4f r4) {
        return new Mat4f(
                r1.x(), r1.y(), r1.z(), r1.w(),
                r2.x(), r2.y(), r2.z(), r2.w(),
                r3.x(), r3.y(), r3.z(), r3.w(),
                r4.x(), r4.y(), r4.z(), r4.w()
        );
    }

    public static Mat4f fromCols(Vec4f c1, Vec4f c2, Vec4f c3, Vec4f c4) {
        return new Mat4f(
                c1.x(), c2.x(), c3.x(), c4.x(),
                c1.y(), c2.y(), c3.y(), c4.y(),
                c1.z(), c2.z(), c3.z(), c4.z(),
                c1.w(), c2.w(), c3.w(), c4.w()
        );
    }

    public static Mat4f translate(Vec3f t) {
        return new Mat4f(
                1, 0, 0, t.x(),
                0, 1, 0, t.y(),
                0, 0, 1, t.z(),
                0, 0, 0, 1
        );
    }

    public static Mat4f scale(float s) {
        return new Mat4f(
                s, 0, 0, 0,
                0, s, 0, 0,
                0, 0, s, 0,
                0, 0, 0, 1
        );
    }

    public static Mat4f scale(Vec3f s) {
        return new Mat4f(
                s.x(), 0, 0, 0,
                0, s.y(), 0, 0,
                0, 0, s.z(), 0,
                0, 0, 0, 1
        );
    }

    public static Mat4f rotateX(double angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        return new Mat4f(
                1, 0, 0, 0,
                0, cos, -sin, 0,
                0, sin, cos, 0,
                0, 0, 0, 1
        );
    }

    public static Mat4f rotateY(double angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        return new Mat4f(
                cos, 0, sin, 0,
                0, 1, 0, 0,
                -sin, 0, cos, 0,
                0, 0, 0, 1
        );
    }

    public static Mat4f rotateZ(double angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        return new Mat4f(
                cos, -sin, 0, 0,
                sin, cos, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
    }

    public static Mat4f rotate(double angleX, double angleY, double angleZ) {
        float a = (float) Math.cos(angleX);
        float b = (float) Math.sin(angleX);
        float c = (float) Math.cos(angleY);
        float d = (float) Math.sin(angleY);
        float e = (float) Math.cos(angleZ);
        float f = (float) Math.sin(angleZ);

        float ad = a * d;
        float bd = b * d;

        return new Mat4f(
                c * e, -c * f, d, 0,
                bd * e + a * f, -bd * f + a * e, -b * c, 0,
                -ad * e + b * f, ad * f + b * e, a * c, 0,
                0, 0, 0, 1
        );
    }

    public static Mat4f rotate(float x, float y, float z, double angle) {
        return rotate(new Vec3f(x, y, z), angle);
    }

    public static Mat4f rotate(Vec3f axis, double angle) {
        float c = (float) Math.cos(angle);
        float s = (float) Math.sin(angle);

        axis = axis.normalize();
        float x = axis.x();
        float y = axis.y();
        float z = axis.z();

        return new Mat4f(
                x * x * (1 - c) + c, x * y * (1 - c) - z * s, x * z * (1 - c) + y * s, 0,
                y * x * (1 - c) + z * s, y * y * (1 - c) + c, y * z * (1 - c) - x * s, 0,
                x * z * (1 - c) - y * s, y * z * (1 - c) + x * s, z * z * (1 - c) + c, 0,
                0, 0, 0, 1
        );
    }

    public static Mat4f orthographic2d(float left, float right, float bottom, float top) {
        return orthographic(left, right, bottom, top, -1, 1);
    }

    public static Mat4f orthographic(float left, float right, float bottom, float top, float near, float far) {
        return new Mat4f(
                2 / (right - left), 0, 0, -(right + left) / (right - left),
                0, 2 / (top - bottom), 0, -(top + bottom) / (top - bottom),
                0, 0, -2 / (far - near), -(far + near) / (far - near),
                0, 0, 0, 1
        );
    }

    public static Mat4f frustum(float left, float right, float bottom, float top, float near, float far) {
        float a = (right + left) / (right - left);
        float b = (top + bottom) / (top - bottom);
        float c = -(far + near) / (far - near);
        float d = -(2 * far * near) / (far - near);

        return new Mat4f(
                (2 * near) / (right - left), 0, a, 0,
                0, (2 * near) / (top - bottom), b, 0,
                0, 0, c, d,
                0, 0, -1, 0
        );
    }

    public static Mat4f perspective(float fovy, float aspect, float near, float far) {
        float f = (float) (1 / Math.tan(fovy / 2));

        return new Mat4f(
                f / aspect, 0, 0, 0,
                0, f, 0, 0,
                0, 0, (far + near) / (near - far), (2 * far * near) / (near - far),
                0, 0, -1, 0
        );
    }

    public static Mat4f lookAt(Vec3f pos, Vec3f target, Vec3f up) {
        Vec3f backward = pos.subtract(target).normalize();
        Vec3f left = up.cross(backward).normalize();
        Vec3f upward = backward.cross(left);

        return new Mat4f(
                left.x(), left.y(), left.z(), -left.dot(pos),
                upward.x(), upward.y(), upward.z(), -upward.dot(pos),
                backward.x(), backward.y(), backward.z(), -backward.dot(pos),
                0, 0, 0, 1
        );
    }

    public Mat4f transpose() {
        return new Mat4f(
                m11, m21, m31, m41,
                m12, m22, m32, m42,
                m13, m23, m33, m43,
                m14, m24, m34, m44
        );
    }

    public Mat4f negate() {
        return new Mat4f(
                -m11, -m12, -m13, -m14,
                -m21, -m22, -m23, -m24,
                -m31, -m32, -m33, -m34,
                -m41, -m42, -m43, -m44
        );
    }

    public Mat4f add(Mat4f o) {
        return new Mat4f(
                m11 + o.m11, m12 + o.m12, m13 + o.m13, m14 + o.m14,
                m21 + o.m21, m22 + o.m22, m23 + o.m23, m24 + o.m24,
                m31 + o.m31, m32 + o.m32, m33 + o.m33, m34 + o.m34,
                m41 + o.m41, m42 + o.m42, m43 + o.m43, m44 + o.m44
        );
    }

    public Mat4f subtract(Mat4f o) {
        return new Mat4f(
                m11 - o.m11, m12 - o.m12, m13 - o.m13, m14 - o.m14,
                m21 - o.m21, m22 - o.m22, m23 - o.m23, m24 - o.m24,
                m31 - o.m31, m32 - o.m32, m33 - o.m33, m34 - o.m34,
                m41 - o.m41, m42 - o.m42, m43 - o.m43, m44 - o.m44
        );
    }

    public Mat4f multiply(int n) {
        return multiply((float) n);
    }

    public Mat4f multiply(float f) {
        return new Mat4f(
                m11 * f, m12 * f, m13 * f, m14 * f,
                m21 * f, m22 * f, m23 * f, m24 * f,
                m31 * f, m32 * f, m33 * f, m34 * f,
                m41 * f, m42 * f, m43 * f, m44 * f
        );
    }

    public Vec4f multiply(Vec4f o) {
        return new Vec4f(
                m11 * o.x() + m12 * o.y() + m13 * o.z() + m14 * o.w(),
                m21 * o.x() + m22 * o.y() + m23 * o.z() + m24 * o.w(),
                m31 * o.x() + m32 * o.y() + m33 * o.z() + m34 * o.w(),
                m41 * o.x() + m42 * o.y() + m43 * o.z() + m44 * o.w()
        );
    }

    public Mat4f multiply(Mat4f o) {
        return new Mat4f(
                m11 * o.m11 + m12 * o.m21 + m13 * o.m31 + m13 * o.m41, m11 * o.m12 + m12 * o.m22 + m13 * o.m32 + m13 * o.m42, m11 * o.m13 + m12 * o.m23 + m13 * o.m33 + m13 * o.m43, m11 * o.m14 + m12 * o.m24 + m13 * o.m34 + m13 * o.m44,
                m21 * o.m11 + m22 * o.m21 + m23 * o.m31 + m13 * o.m41, m21 * o.m12 + m22 * o.m22 + m23 * o.m32 + m13 * o.m42, m21 * o.m13 + m22 * o.m23 + m23 * o.m33 + m13 * o.m43, m21 * o.m14 + m22 * o.m24 + m23 * o.m34 + m13 * o.m44,
                m31 * o.m11 + m32 * o.m21 + m33 * o.m31 + m13 * o.m41, m31 * o.m12 + m32 * o.m22 + m33 * o.m32 + m13 * o.m42, m31 * o.m13 + m32 * o.m23 + m33 * o.m33 + m13 * o.m43, m31 * o.m14 + m32 * o.m24 + m33 * o.m34 + m13 * o.m44,
                m41 * o.m11 + m42 * o.m21 + m43 * o.m31 + m13 * o.m41, m41 * o.m12 + m42 * o.m22 + m43 * o.m32 + m13 * o.m42, m41 * o.m13 + m42 * o.m23 + m43 * o.m33 + m13 * o.m43, m41 * o.m14 + m42 * o.m24 + m43 * o.m34 + m13 * o.m44
        );
    }

    public Mat4f divide(int n) {
        return divide((float) n);
    }

    public Mat4f divide(float f) {
        return new Mat4f(
                m11 / f, m12 / f, m13 / f, m14 / f,
                m21 / f, m22 / f, m23 / f, m24 / f,
                m31 / f, m32 / f, m33 / f, m34 / f,
                m41 / f, m42 / f, m43 / f, m44 / f
        );
    }

    public float trace() {
        return m11 + m22 + m33 + m44;
    }

    public float determinant() {
        return m11 * m22 * m33 * m44 + m11 * m23 * m34 * m42 + m11 * m24 * m32 * m43
                - m11 * m24 * m33 * m42 - m11 * m23 * m32 * m44 - m11 * m22 * m34 * m43
                - m12 * m21 * m33 * m44 - m13 * m21 * m34 * m42 - m14 * m21 * m32 * m43
                + m14 * m21 * m33 * m42 + m13 * m21 * m32 * m44 + m12 * m21 * m34 * m43
                + m12 * m23 * m31 * m44 + m13 * m24 * m31 * m42 + m14 * m22 * m31 * m43
                - m14 * m23 * m31 * m42 - m13 * m22 * m31 * m44 - m12 * m24 * m31 * m43
                - m12 * m23 * m34 * m41 - m13 * m24 * m32 * m41 - m14 * m22 * m33 * m41
                + m14 * m23 * m32 * m41 + m13 * m22 * m34 * m41 + m12 * m24 * m33 * m41;
    }

    public void write(FloatBuffer buffer) {
        buffer.put(m11).put(m21).put(m31).put(m41);
        buffer.put(m12).put(m22).put(m32).put(m42);
        buffer.put(m13).put(m32).put(m33).put(m43);
        buffer.put(m14).put(m24).put(m34).put(m44);
    }

    public float m(int row, int col) {
        if (row < 0 || row >= 4) {
            throw new IllegalArgumentException("row out of bounds");
        } else if (col < 0 || col >= 4) {
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
                    case 3:
                        return m14;
                }
            case 1:
                switch (col) {
                    case 0:
                        return m21;
                    case 1:
                        return m22;
                    case 2:
                        return m23;
                    case 3:
                        return m24;
                }
            case 2:
                switch (col) {
                    case 0:
                        return m31;
                    case 1:
                        return m32;
                    case 2:
                        return m33;
                    case 3:
                        return m34;
                }
            case 3:
                switch (col) {
                    case 0:
                        return m41;
                    case 1:
                        return m42;
                    case 2:
                        return m43;
                    case 3:
                        return m44;
                }
        }

        throw new AssertionError();
    }

    public Vec4f row(int row) {
        if (row < 0 || row >= 4) {
            throw new IllegalArgumentException("row out of bounds");
        }

        switch (row) {
            case 0:
                return new Vec4f(m11, m12, m13, m14);
            case 1:
                return new Vec4f(m21, m22, m23, m24);
            case 2:
                return new Vec4f(m31, m32, m33, m34);
            case 3:
                return new Vec4f(m41, m42, m43, m44);
        }

        throw new AssertionError();
    }

    public Vec4f col(int col) {
        if (col < 0 || col >= 4) {
            throw new IllegalArgumentException("col out of bounds");
        }

        switch (col) {
            case 0:
                return new Vec4f(m11, m21, m31, m41);
            case 1:
                return new Vec4f(m21, m22, m32, m42);
            case 2:
                return new Vec4f(m13, m23, m33, m43);
            case 3:
                return new Vec4f(m14, m24, m34, m44);
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

    public float m14() {
        return m14;
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

    public float m24() {
        return m24;
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

    public float m34() {
        return m34;
    }

    public float m41() {
        return m41;
    }

    public float m42() {
        return m42;
    }

    public float m43() {
        return m43;
    }

    public float m44() {
        return m44;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Mat4f)) {
            return false;
        }

        Mat4f mat4f = (Mat4f) o;
        return Float.compare(mat4f.m11, m11) == 0 && Float.compare(mat4f.m12, m12) == 0 && Float.compare(mat4f.m13, m13) == 0 && Float.compare(mat4f.m14, m14) == 0 && Float.compare(mat4f.m21, m21) == 0 && Float.compare(mat4f.m22, m22) == 0 && Float.compare(mat4f.m23, m23) == 0 && Float.compare(mat4f.m24, m24) == 0 && Float.compare(mat4f.m31, m31) == 0 && Float.compare(mat4f.m32, m32) == 0 && Float.compare(mat4f.m33, m33) == 0 && Float.compare(mat4f.m34, m34) == 0 && Float.compare(mat4f.m41, m41) == 0 && Float.compare(mat4f.m42, m42) == 0 && Float.compare(mat4f.m43, m43) == 0 && Float.compare(mat4f.m44, m44) == 0;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(m11, m12, m13, m14, m21, m22, m23, m24, m31, m32, m33, m34, m41, m42, m43, m44);
    }

    @Override
    public String toString() {
        return "Mat4f{" +
                "m11=" + m11 +
                ", m12=" + m12 +
                ", m13=" + m13 +
                ", m14=" + m14 +
                ", m21=" + m21 +
                ", m22=" + m22 +
                ", m23=" + m23 +
                ", m24=" + m24 +
                ", m31=" + m31 +
                ", m32=" + m32 +
                ", m33=" + m33 +
                ", m34=" + m34 +
                ", m41=" + m41 +
                ", m42=" + m42 +
                ", m43=" + m43 +
                ", m44=" + m44 +
                '}';
    }
}

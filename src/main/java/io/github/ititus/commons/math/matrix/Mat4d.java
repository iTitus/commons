package io.github.ititus.commons.math.matrix;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.math.vector.Vec4d;

public final class Mat4d {

    private final double m11, m12, m13, m14;
    private final double m21, m22, m23, m24;
    private final double m31, m32, m33, m34;
    private final double m41, m42, m43, m44;

    public Mat4d(double m11, double m12, double m13, double m14, double m21, double m22, double m23, double m24, double m31, double m32, double m33, double m34, double m41, double m42, double m43, double m44) {
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

    public static Mat4d zero() {
        return new Mat4d(
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
    }

    public static Mat4d identity() {
        return new Mat4d(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
    }

    public static Mat4d fromRows(Vec4d r1, Vec4d r2, Vec4d r3, Vec4d r4) {
        return new Mat4d(
                r1.x(), r1.y(), r1.z(), r1.w(),
                r2.x(), r2.y(), r2.z(), r2.w(),
                r3.x(), r3.y(), r3.z(), r3.w(),
                r4.x(), r4.y(), r4.z(), r4.w()
        );
    }

    public static Mat4d fromCols(Vec4d c1, Vec4d c2, Vec4d c3, Vec4d c4) {
        return new Mat4d(
                c1.x(), c2.x(), c3.x(), c4.x(),
                c1.y(), c2.y(), c3.y(), c4.y(),
                c1.z(), c2.z(), c3.z(), c4.z(),
                c1.w(), c2.w(), c3.w(), c4.w()
        );
    }

    public Mat4d transpose() {
        return new Mat4d(
                m11, m21, m31, m41,
                m12, m22, m32, m42,
                m13, m23, m33, m43,
                m14, m24, m34, m44
        );
    }

    public Mat4d negate() {
        return new Mat4d(
                -m11, -m12, -m13, -m14,
                -m21, -m22, -m23, -m24,
                -m31, -m32, -m33, -m34,
                -m41, -m42, -m43, -m44
        );
    }

    public Mat4d add(Mat4d o) {
        return new Mat4d(
                m11 + o.m11, m12 + o.m12, m13 + o.m13, m14 + o.m14,
                m21 + o.m21, m22 + o.m22, m23 + o.m23, m24 + o.m24,
                m31 + o.m31, m32 + o.m32, m33 + o.m33, m34 + o.m34,
                m41 + o.m41, m42 + o.m42, m43 + o.m43, m44 + o.m44
        );
    }

    public Mat4d subtract(Mat4d o) {
        return new Mat4d(
                m11 - o.m11, m12 - o.m12, m13 - o.m13, m14 - o.m14,
                m21 - o.m21, m22 - o.m22, m23 - o.m23, m24 - o.m24,
                m31 - o.m31, m32 - o.m32, m33 - o.m33, m34 - o.m34,
                m41 - o.m41, m42 - o.m42, m43 - o.m43, m44 - o.m44
        );
    }

    public Mat4d multiply(int n) {
        return multiply((double) n);
    }

    public Mat4d multiply(float f) {
        return multiply((double) f);
    }

    public Mat4d multiply(double d) {
        return new Mat4d(
                m11 * d, m12 * d, m13 * d, m14 * d,
                m21 * d, m22 * d, m23 * d, m24 * d,
                m31 * d, m32 * d, m33 * d, m34 * d,
                m41 * d, m42 * d, m43 * d, m44 * d
        );
    }

    public Vec4d multiply(Vec4d o) {
        return new Vec4d(
                m11 * o.x() + m12 * o.y() + m13 * o.z() + m14 * o.w(),
                m21 * o.x() + m22 * o.y() + m23 * o.z() + m24 * o.w(),
                m31 * o.x() + m32 * o.y() + m33 * o.z() + m34 * o.w(),
                m41 * o.x() + m42 * o.y() + m43 * o.z() + m44 * o.w()
        );
    }

    public Mat4d multiply(Mat4d o) {
        return new Mat4d(
                m11 * o.m11 + m12 * o.m21 + m13 * o.m31 + m13 * o.m41, m11 * o.m12 + m12 * o.m22 + m13 * o.m32 + m13 * o.m42, m11 * o.m13 + m12 * o.m23 + m13 * o.m33 + m13 * o.m43, m11 * o.m14 + m12 * o.m24 + m13 * o.m34 + m13 * o.m44,
                m21 * o.m11 + m22 * o.m21 + m23 * o.m31 + m13 * o.m41, m21 * o.m12 + m22 * o.m22 + m23 * o.m32 + m13 * o.m42, m21 * o.m13 + m22 * o.m23 + m23 * o.m33 + m13 * o.m43, m21 * o.m14 + m22 * o.m24 + m23 * o.m34 + m13 * o.m44,
                m31 * o.m11 + m32 * o.m21 + m33 * o.m31 + m13 * o.m41, m31 * o.m12 + m32 * o.m22 + m33 * o.m32 + m13 * o.m42, m31 * o.m13 + m32 * o.m23 + m33 * o.m33 + m13 * o.m43, m31 * o.m14 + m32 * o.m24 + m33 * o.m34 + m13 * o.m44,
                m41 * o.m11 + m42 * o.m21 + m43 * o.m31 + m13 * o.m41, m41 * o.m12 + m42 * o.m22 + m43 * o.m32 + m13 * o.m42, m41 * o.m13 + m42 * o.m23 + m43 * o.m33 + m13 * o.m43, m41 * o.m14 + m42 * o.m24 + m43 * o.m34 + m13 * o.m44
        );
    }

    public Mat4d divide(int n) {
        return divide((double) n);
    }

    public Mat4d divide(float f) {
        return divide((double) f);
    }

    public Mat4d divide(double d) {
        return new Mat4d(
                m11 / d, m12 / d, m13 / d, m14 / d,
                m21 / d, m22 / d, m23 / d, m24 / d,
                m31 / d, m32 / d, m33 / d, m34 / d,
                m41 / d, m42 / d, m43 / d, m44 / d
        );
    }

    public double trace() {
        return m11 + m22 + m33 + m44;
    }

    public double determinant() {
        return m11 * m22 * m33 * m44 + m11 * m23 * m34 * m42 + m11 * m24 * m32 * m43
                - m11 * m24 * m33 * m42 - m11 * m23 * m32 * m44 - m11 * m22 * m34 * m43
                - m12 * m21 * m33 * m44 - m13 * m21 * m34 * m42 - m14 * m21 * m32 * m43
                + m14 * m21 * m33 * m42 + m13 * m21 * m32 * m44 + m12 * m21 * m34 * m43
                + m12 * m23 * m31 * m44 + m13 * m24 * m31 * m42 + m14 * m22 * m31 * m43
                - m14 * m23 * m31 * m42 - m13 * m22 * m31 * m44 - m12 * m24 * m31 * m43
                - m12 * m23 * m34 * m41 - m13 * m24 * m32 * m41 - m14 * m22 * m33 * m41
                + m14 * m23 * m32 * m41 + m13 * m22 * m34 * m41 + m12 * m24 * m33 * m41;
    }

    public double m(int row, int col) {
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

    public Vec4d row(int row) {
        if (row < 0 || row >= 4) {
            throw new IllegalArgumentException("row out of bounds");
        }

        switch (row) {
            case 0:
                return new Vec4d(m11, m12, m13, m14);
            case 1:
                return new Vec4d(m21, m22, m23, m24);
            case 2:
                return new Vec4d(m31, m32, m33, m34);
            case 3:
                return new Vec4d(m41, m42, m43, m44);
        }

        throw new AssertionError();
    }

    public Vec4d col(int col) {
        if (col < 0 || col >= 4) {
            throw new IllegalArgumentException("col out of bounds");
        }

        switch (col) {
            case 0:
                return new Vec4d(m11, m21, m31, m41);
            case 1:
                return new Vec4d(m21, m22, m32, m42);
            case 2:
                return new Vec4d(m13, m23, m33, m43);
            case 3:
                return new Vec4d(m14, m24, m34, m44);
        }

        throw new AssertionError();
    }

    public double m11() {
        return m11;
    }

    public double m12() {
        return m12;
    }

    public double m13() {
        return m13;
    }

    public double m14() {
        return m14;
    }

    public double m21() {
        return m21;
    }

    public double m22() {
        return m22;
    }

    public double m23() {
        return m23;
    }

    public double m24() {
        return m24;
    }

    public double m31() {
        return m31;
    }

    public double m32() {
        return m32;
    }

    public double m33() {
        return m33;
    }

    public double m34() {
        return m34;
    }

    public double m41() {
        return m41;
    }

    public double m42() {
        return m42;
    }

    public double m43() {
        return m43;
    }

    public double m44() {
        return m44;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Mat4d)) {
            return false;
        }

        Mat4d mat4d = (Mat4d) o;
        return Double.compare(mat4d.m11, m11) == 0 && Double.compare(mat4d.m12, m12) == 0 && Double.compare(mat4d.m13, m13) == 0 && Double.compare(mat4d.m14, m14) == 0 && Double.compare(mat4d.m21, m21) == 0 && Double.compare(mat4d.m22, m22) == 0 && Double.compare(mat4d.m23, m23) == 0 && Double.compare(mat4d.m24, m24) == 0 && Double.compare(mat4d.m31, m31) == 0 && Double.compare(mat4d.m32, m32) == 0 && Double.compare(mat4d.m33, m33) == 0 && Double.compare(mat4d.m34, m34) == 0 && Double.compare(mat4d.m41, m41) == 0 && Double.compare(mat4d.m42, m42) == 0 && Double.compare(mat4d.m43, m43) == 0 && Double.compare(mat4d.m44, m44) == 0;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(m11, m12, m13, m14, m21, m22, m23, m24, m31, m32, m33, m34, m41, m42, m43, m44);
    }

    @Override
    public String toString() {
        return "Mat4d{" +
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

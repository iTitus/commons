package io.github.ititus.commons.math.matrix;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.math.vector.Vec3d;

public final class Mat3d {

    private final double m11, m12, m13;
    private final double m21, m22, m23;
    private final double m31, m32, m33;

    public Mat3d(double m11, double m12, double m13, double m21, double m22, double m23, double m31, double m32, double m33) {
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

    public static Mat3d zero() {
        return new Mat3d(
                0, 0, 0,
                0, 0, 0,
                0, 0, 0
        );
    }

    public static Mat3d identity() {
        return new Mat3d(
                1, 0, 0,
                0, 1, 0,
                0, 0, 1
        );
    }

    public static Mat3d fromRows(Vec3d r1, Vec3d r2, Vec3d r3) {
        return new Mat3d(
                r1.x(), r1.y(), r1.z(),
                r2.x(), r2.y(), r2.z(),
                r3.x(), r3.y(), r3.z()
        );
    }

    public static Mat3d fromCols(Vec3d c1, Vec3d c2, Vec3d c3) {
        return new Mat3d(
                c1.x(), c2.x(), c3.x(),
                c1.y(), c2.y(), c3.y(),
                c1.z(), c2.z(), c3.z()
        );
    }

    public Mat3d transpose() {
        return new Mat3d(
                m11, m21, m31,
                m12, m22, m32,
                m13, m23, m33
        );
    }

    public Mat3d negate() {
        return new Mat3d(
                -m11, -m12, -m13,
                -m21, -m22, -m23,
                -m31, -m32, -m33
        );
    }

    public Mat3d add(Mat3d o) {
        return new Mat3d(
                m11 + o.m11, m12 + o.m12, m13 + o.m13,
                m21 + o.m21, m22 + o.m22, m23 + o.m23,
                m31 + o.m31, m32 + o.m32, m33 + o.m33
        );
    }

    public Mat3d subtract(Mat3d o) {
        return new Mat3d(
                m11 - o.m11, m12 - o.m12, m13 - o.m13,
                m21 - o.m21, m22 - o.m22, m23 - o.m23,
                m31 - o.m31, m32 - o.m32, m33 - o.m33
        );
    }

    public Mat3d multiply(int n) {
        return multiply((double) n);
    }

    public Mat3d multiply(float f) {
        return multiply((double) f);
    }

    public Mat3d multiply(double d) {
        return new Mat3d(
                m11 * d, m12 * d, m13 * d,
                m21 * d, m22 * d, m23 * d,
                m31 * d, m32 * d, m33 * d
        );
    }

    public Vec3d multiply(Vec3d o) {
        return new Vec3d(
                m11 * o.x() + m12 * o.y() + m13 * o.z(),
                m21 * o.x() + m22 * o.y() + m23 * o.z(),
                m31 * o.x() + m32 * o.y() + m33 * o.z()
        );
    }

    public Mat3d multiply(Mat3d o) {
        return new Mat3d(
                m11 * o.m11 + m12 * o.m21 + m13 * o.m31, m11 * o.m12 + m12 * o.m22 + m13 * o.m32, m11 * o.m13 + m12 * o.m23 + m13 * o.m33,
                m21 * o.m11 + m22 * o.m21 + m23 * o.m31, m21 * o.m12 + m22 * o.m22 + m23 * o.m32, m21 * o.m13 + m22 * o.m23 + m23 * o.m33,
                m31 * o.m11 + m32 * o.m21 + m33 * o.m31, m31 * o.m12 + m32 * o.m22 + m33 * o.m32, m31 * o.m13 + m32 * o.m23 + m33 * o.m33
        );
    }

    public Mat3d divide(int n) {
        return divide((double) n);
    }

    public Mat3d divide(float f) {
        return divide((double) f);
    }

    public Mat3d divide(double d) {
        return new Mat3d(
                m11 / d, m12 / d, m13 / d,
                m21 / d, m22 / d, m23 / d,
                m31 / d, m32 / d, m33 / d
        );
    }

    public double trace() {
        return m11 + m22 + m33;
    }

    public double determinant() {
        return m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32
                - m13 * m22 * m31 - m12 * m21 * m33 - m11 * m23 * m32;
    }

    public double m(int row, int col) {
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

    public Vec3d row(int row) {
        if (row < 0 || row >= 3) {
            throw new IllegalArgumentException("row out of bounds");
        }

        switch (row) {
            case 0:
                return new Vec3d(m11, m12, m13);
            case 1:
                return new Vec3d(m21, m22, m23);
            case 2:
                return new Vec3d(m31, m32, m33);
        }

        throw new AssertionError();
    }

    public Vec3d col(int col) {
        if (col < 0 || col >= 3) {
            throw new IllegalArgumentException("col out of bounds");
        }

        switch (col) {
            case 0:
                return new Vec3d(m11, m21, m31);
            case 1:
                return new Vec3d(m21, m22, m32);
            case 2:
                return new Vec3d(m13, m23, m33);
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

    public double m21() {
        return m21;
    }

    public double m22() {
        return m22;
    }

    public double m23() {
        return m23;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Mat3d)) {
            return false;
        }

        Mat3d mat3d = (Mat3d) o;
        return Double.compare(mat3d.m11, m11) == 0 && Double.compare(mat3d.m12, m12) == 0 && Double.compare(mat3d.m13, m13) == 0 && Double.compare(mat3d.m21, m21) == 0 && Double.compare(mat3d.m22, m22) == 0 && Double.compare(mat3d.m23, m23) == 0 && Double.compare(mat3d.m31, m31) == 0 && Double.compare(mat3d.m32, m32) == 0 && Double.compare(mat3d.m33, m33) == 0;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(m11, m12, m13, m21, m22, m23, m31, m32, m33);
    }

    @Override
    public String toString() {
        return "Mat3d{" +
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

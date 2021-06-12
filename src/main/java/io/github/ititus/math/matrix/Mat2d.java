package io.github.ititus.math.matrix;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.vector.Vec2d;

public final class Mat2d {

    private final double m11, m12;
    private final double m21, m22;

    public Mat2d(double m11, double m12, double m21, double m22) {
        this.m11 = m11;
        this.m12 = m12;
        this.m21 = m21;
        this.m22 = m22;
    }

    public static Mat2d zero() {
        return new Mat2d(
                0, 0,
                0, 0
        );
    }

    public static Mat2d identity() {
        return new Mat2d(
                1, 0,
                0, 1
        );
    }

    public static Mat2d fromRows(Vec2d r1, Vec2d r2) {
        return new Mat2d(
                r1.x(), r1.y(),
                r2.x(), r2.y()
        );
    }

    public static Mat2d fromCols(Vec2d c1, Vec2d c2) {
        return new Mat2d(
                c1.x(), c2.x(),
                c1.y(), c2.y()
        );
    }

    public Mat2d transpose() {
        return new Mat2d(
                m11, m21,
                m12, m22
        );
    }

    public Mat2d add(Mat2d o) {
        return new Mat2d(
                m11 + o.m11, m12 + o.m12,
                m21 + o.m21, m22 + o.m22
        );
    }

    public Mat2d subtract(Mat2d o) {
        return new Mat2d(
                m11 - o.m11, m12 - o.m12,
                m21 - o.m21, m22 - o.m22
        );
    }

    public Mat2d multiply(int n) {
        return multiply((double) n);
    }

    public Mat2d multiply(float f) {
        return multiply((double) f);
    }

    public Mat2d multiply(double d) {
        return new Mat2d(
                m11 * d, m12 * d,
                m21 * d, m22 * d
        );
    }

    public Vec2d multiply(Vec2d o) {
        return new Vec2d(
                m11 * o.x() + m12 * o.y(),
                m21 * o.x() + m22 * o.y()
        );
    }

    public Mat2d multiply(Mat2d o) {
        return new Mat2d(
                m11 * o.m11 + m12 * o.m21, m11 * o.m12 + m12 * o.m22,
                m21 * o.m11 + m22 * o.m21, m21 * o.m12 + m22 * o.m22
        );
    }

    public Mat2d divide(int n) {
        return divide((double) n);
    }

    public Mat2d divide(float f) {
        return divide((double) f);
    }

    public Mat2d divide(double d) {
        return new Mat2d(
                m11 / d, m12 / d,
                m21 / d, m22 / d
        );
    }

    public double trace() {
        return m11 + m22;
    }

    public double determinant() {
        return m11 * m22 - m12 * m21;
    }

    public double m(int row, int col) {
        if (row < 0 || row >= 2) {
            throw new IllegalArgumentException("row out of bounds");
        } else if (col < 0 || col >= 2) {
            throw new IllegalArgumentException("col out of bounds");
        }

        switch (row) {
            case 0:
                switch (col) {
                    case 0:
                        return m11;
                    case 1:
                        return m12;
                }
            case 1:
                switch (col) {
                    case 0:
                        return m21;
                    case 1:
                        return m22;
                }
        }

        throw new AssertionError();
    }

    public Vec2d row(int row) {
        if (row < 0 || row >= 2) {
            throw new IllegalArgumentException("row out of bounds");
        }

        switch (row) {
            case 0:
                return new Vec2d(m11, m12);
            case 1:
                return new Vec2d(m21, m22);
        }

        throw new AssertionError();
    }

    public Vec2d col(int col) {
        if (col < 0 || col >= 2) {
            throw new IllegalArgumentException("col out of bounds");
        }

        switch (col) {
            case 0:
                return new Vec2d(m11, m21);
            case 1:
                return new Vec2d(m21, m22);
        }

        throw new AssertionError();
    }

    public double m11() {
        return m11;
    }

    public double m12() {
        return m12;
    }

    public double m21() {
        return m21;
    }

    public double m22() {
        return m22;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Mat2d)) {
            return false;
        }

        Mat2d mat2d = (Mat2d) o;
        return Double.compare(mat2d.m11, m11) == 0 && Double.compare(mat2d.m12, m12) == 0 && Double.compare(mat2d.m21, m21) == 0 && Double.compare(mat2d.m22, m22) == 0;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(m11, m12, m21, m22);
    }

    @Override
    public String toString() {
        return "Mat2d{" +
                "m11=" + m11 +
                ", m12=" + m12 +
                ", m21=" + m21 +
                ", m22=" + m22 +
                '}';
    }
}

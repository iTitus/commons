package io.github.ititus.commons.math.matrix;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.math.vector.Vec2f;

import java.nio.FloatBuffer;

public final class Mat2f {

    private final float m11, m12;
    private final float m21, m22;

    public Mat2f(float m11, float m12, float m21, float m22) {
        this.m11 = m11;
        this.m12 = m12;
        this.m21 = m21;
        this.m22 = m22;
    }

    public static Mat2f zero() {
        return new Mat2f(
                0, 0,
                0, 0
        );
    }

    public static Mat2f identity() {
        return new Mat2f(
                1, 0,
                0, 1
        );
    }

    public static Mat2f fromRows(Vec2f r1, Vec2f r2) {
        return new Mat2f(
                r1.x(), r1.y(),
                r2.x(), r2.y()
        );
    }

    public static Mat2f fromCols(Vec2f c1, Vec2f c2) {
        return new Mat2f(
                c1.x(), c2.x(),
                c1.y(), c2.y()
        );
    }

    public Mat2f transpose() {
        return new Mat2f(
                m11, m21,
                m12, m22
        );
    }

    public Mat2f negate() {
        return new Mat2f(
                -m11, -m12,
                -m21, -m22
        );
    }

    public Mat2f add(Mat2f o) {
        return new Mat2f(
                m11 + o.m11, m12 + o.m12,
                m21 + o.m21, m22 + o.m22
        );
    }

    public Mat2f subtract(Mat2f o) {
        return new Mat2f(
                m11 - o.m11, m12 - o.m12,
                m21 - o.m21, m22 - o.m22
        );
    }

    public Mat2f multiply(int n) {
        return multiply((float) n);
    }

    public Mat2f multiply(float f) {
        return new Mat2f(
                m11 * f, m12 * f,
                m21 * f, m22 * f
        );
    }

    public Vec2f multiply(Vec2f o) {
        return new Vec2f(
                m11 * o.x() + m12 * o.y(),
                m21 * o.x() + m22 * o.y()
        );
    }

    public Mat2f multiply(Mat2f o) {
        return new Mat2f(
                m11 * o.m11 + m12 * o.m21, m11 * o.m12 + m12 * o.m22,
                m21 * o.m11 + m22 * o.m21, m21 * o.m12 + m22 * o.m22
        );
    }

    public Mat2f divide(int n) {
        return divide((float) n);
    }

    public Mat2f divide(float f) {
        return new Mat2f(
                m11 / f, m12 / f,
                m21 / f, m22 / f
        );
    }

    public float trace() {
        return m11 + m22;
    }

    public float determinant() {
        return m11 * m22 - m12 * m21;
    }

    public void write(FloatBuffer buffer) {
        buffer.put(m11).put(m21);
        buffer.put(m12).put(m22);
    }

    public float m(int row, int col) {
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

    public Vec2f row(int row) {
        if (row < 0 || row >= 2) {
            throw new IllegalArgumentException("row out of bounds");
        }

        switch (row) {
            case 0:
                return new Vec2f(m11, m12);
            case 1:
                return new Vec2f(m21, m22);
        }

        throw new AssertionError();
    }

    public Vec2f col(int col) {
        if (col < 0 || col >= 2) {
            throw new IllegalArgumentException("col out of bounds");
        }

        switch (col) {
            case 0:
                return new Vec2f(m11, m21);
            case 1:
                return new Vec2f(m21, m22);
        }

        throw new AssertionError();
    }

    public float m11() {
        return m11;
    }

    public float m12() {
        return m12;
    }

    public float m21() {
        return m21;
    }

    public float m22() {
        return m22;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Mat2f)) {
            return false;
        }

        Mat2f mat2f = (Mat2f) o;
        return Float.compare(mat2f.m11, m11) == 0 && Float.compare(mat2f.m12, m12) == 0 && Float.compare(mat2f.m21, m21) == 0 && Float.compare(mat2f.m22, m22) == 0;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(m11, m12, m21, m22);
    }

    @Override
    public String toString() {
        return "Mat2f{" +
                "m11=" + m11 +
                ", m12=" + m12 +
                ", m21=" + m21 +
                ", m22=" + m22 +
                '}';
    }
}

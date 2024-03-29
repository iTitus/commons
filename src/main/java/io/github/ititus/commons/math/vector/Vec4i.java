package io.github.ititus.commons.math.vector;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.math.number.JavaMath;

public final class Vec4i implements Comparable<Vec4i> {

    private final int x;
    private final int y;
    private final int z;
    private final int w;

    public Vec4i() {
        this(0, 0, 0, 0);
    }

    public Vec4i(int[] arr) {
        if (arr.length != 4) {
            throw new IllegalArgumentException("illegal array size");
        }

        this.x = arr[0];
        this.y = arr[1];
        this.z = arr[2];
        this.w = arr[3];
    }

    public Vec4i(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4i negate() {
        return new Vec4i(-x, -y, -z, -w);
    }

    public Vec4i add(Vec4i o) {
        return new Vec4i(x + o.x, y + o.y, z + o.z, w + o.w);
    }

    public Vec4i subtract(Vec4i o) {
        return new Vec4i(x - o.x, y - o.y, z - o.z, w - o.w);
    }

    public int dot(Vec4i o) {
        return x * o.x + y * o.y + z * o.z + w * o.w;
    }

    public Vec4i sgn() {
        return new Vec4i(JavaMath.sgn(x), JavaMath.sgn(y), JavaMath.sgn(z), JavaMath.sgn(w));
    }

    public int manhattanDistance() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z) + Math.abs(w);
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int z() {
        return z;
    }

    public int w() {
        return w;
    }

    public Vec2i xy() {
        return new Vec2i(x, y);
    }

    public Vec3i xyz() {
        return new Vec3i(x, y, z);
    }

    @Override
    public int compareTo(Vec4i o) {
        int c1 = Integer.compare(x, o.x);
        if (c1 != 0) {
            return c1;
        }

        int c2 = Integer.compare(y, o.y);
        if (c2 != 0) {
            return c2;
        }

        int c3 = Integer.compare(z, o.z);
        return c3 != 0 ? c3 : Integer.compare(w, o.w);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vec4i)) {
            return false;
        }
        Vec4i vec4i = (Vec4i) o;
        return x == vec4i.x && y == vec4i.y && z == vec4i.z && w == vec4i.w;
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

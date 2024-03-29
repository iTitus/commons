package io.github.ititus.commons.math.vector;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.math.number.JavaMath;

public final class Vec3i implements Comparable<Vec3i> {

    private final int x;
    private final int y;
    private final int z;

    public Vec3i() {
        this(0, 0, 0);
    }

    public Vec3i(int[] arr) {
        if (arr.length != 3) {
            throw new IllegalArgumentException("illegal array size");
        }

        this.x = arr[0];
        this.y = arr[1];
        this.z = arr[2];
    }

    public Vec3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3i negate() {
        return new Vec3i(-x, -y, -z);
    }

    public Vec3i add(Vec3i o) {
        return new Vec3i(x + o.x, y + o.y, z + o.z);
    }

    public Vec3i subtract(Vec3i o) {
        return new Vec3i(x - o.x, y - o.y, z - o.z);
    }

    public int dot(Vec3i o) {
        return x * o.x + y * o.y + z * o.z;
    }

    public Vec3i cross(Vec3i o) {
        return new Vec3i(y * o.z - z * o.y, z * o.x - x * o.z, x * o.y - y * o.x);
    }

    public Vec3i sgn() {
        return new Vec3i(JavaMath.sgn(x), JavaMath.sgn(y), JavaMath.sgn(z));
    }

    public int manhattanDistance() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
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

    public Vec2i xy() {
        return new Vec2i(x, y);
    }

    @Override
    public int compareTo(Vec3i o) {
        int c1 = Integer.compare(x, o.x);
        if (c1 != 0) {
            return c1;
        }

        int c2 = Integer.compare(y, o.y);
        return c2 != 0 ? c2 : Integer.compare(z, o.z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vec3i)) {
            return false;
        }
        Vec3i vec3i = (Vec3i) o;
        return x == vec3i.x && y == vec3i.y && z == vec3i.z;
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

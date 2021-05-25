package io.github.ititus.math.vector;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.math.number.JavaMath;

public final class Vec3i implements Comparable<Vec3i> {

    private final int x;
    private final int y;
    private final int z;

    public Vec3i() {
        this(0, 0, 0);
    }

    public Vec3i(int... arr) {
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

    public Vec3i add(Vec3i o) {
        return new Vec3i(x + o.x, y + o.y, z + o.z);
    }

    public Vec3i subtract(Vec3i o) {
        return new Vec3i(x - o.x, y - o.y, z - o.z);
    }

    public Vec3i sgn() {
        return new Vec3i(JavaMath.sgn(x), JavaMath.sgn(y), JavaMath.sgn(z));
    }

    public int manhattanDistance() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    @Deprecated(forRemoval = true)
    public int getX() {
        return x;
    }

    public int x() {
        return x;
    }

    @Deprecated(forRemoval = true)
    public int getY() {
        return y;
    }

    public int y() {
        return y;
    }

    @Deprecated(forRemoval = true)
    public int getZ() {
        return z;
    }

    public int z() {
        return z;
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

package io.github.ititus.math.vector;

import io.github.ititus.math.number.JavaMath;

public class Vec3i {

    private final int x;
    private final int y;
    private final int z;

    public Vec3i() {
        this(0, 0, 0);
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
        return new Vec3i(JavaMath.signum(x), JavaMath.signum(y), JavaMath.signum(z));
    }

    public int manhattenDistance() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

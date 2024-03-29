package io.github.ititus.commons.math.hash;

import io.github.ititus.commons.math.base.BaseConverters;

import java.util.Arrays;
import java.util.Objects;

public final class HashResult {

    private final byte[] hash;

    public HashResult(byte[] hash) {
        Objects.requireNonNull(hash);
        this.hash = Arrays.copyOf(hash, hash.length);
    }

    public byte[] asBytes() {
        return Arrays.copyOf(hash, hash.length);
    }

    public String asString() {
        return BaseConverters.HEXADECIMAL.encodeUnsigned(hash);
    }
}

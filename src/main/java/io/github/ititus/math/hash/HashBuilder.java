package io.github.ititus.math.hash;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;

public class HashBuilder {

    private final MessageDigest md;

    public HashBuilder(MessageDigest md) {
        this.md = Objects.requireNonNull(md);
    }

    public HashBuilder update(String data) {
        Objects.requireNonNull(data);
        return update(data, StandardCharsets.UTF_8);
    }

    public HashBuilder update(String data, Charset cs) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(cs);
        return update(data.getBytes(cs));
    }

    public HashBuilder update(String... data) {
        Objects.requireNonNull(data);
        return update(StandardCharsets.UTF_8, data);
    }

    public HashBuilder update(Charset cs, String... data) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(cs);
        for (String s : data) {
            update(s, cs);
        }
        return this;
    }

    public HashBuilder update(byte input) {
        md.update(input);
        return this;
    }

    public HashBuilder update(byte... input) {
        Objects.requireNonNull(input);
        md.update(input);
        return this;
    }

    public HashBuilder update(byte[] input, int offset, int len) {
        Objects.requireNonNull(input);
        md.update(input, offset, len);
        return this;
    }

    public HashBuilder update(ByteBuffer input) {
        Objects.requireNonNull(input);
        md.update(input);
        return this;
    }

    public HashResult digest() {
        return new HashResult(md.digest());
    }
}

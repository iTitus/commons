package io.github.ititus.commons.math.hash;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    protected final String algorithm;

    protected Hash(String algorithm) {
        this.algorithm = algorithm;
    }

    private static MessageDigest getMessageDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public MessageDigest getMessageDigest() {
        return getMessageDigest(algorithm);
    }

    public HashBuilder builder() {
        return new HashBuilder(getMessageDigest());
    }

    public String hash(String... data) {
        return builder().update(data).digest().asString();
    }

    public String hash(Charset cs, String... data) {
        return builder().update(cs, data).digest().asString();
    }

    public String hash(String data) {
        return builder().update(data).digest().asString();
    }

    public String hash(String data, Charset cs) {
        return builder().update(data, cs).digest().asString();
    }

    public String hash(byte... data) {
        return builder().update(data).digest().asString();
    }

    public String hash(byte data) {
        return builder().update(data).digest().asString();
    }

    public String hash(ByteBuffer data) {
        return builder().update(data).digest().asString();
    }

    public String hash(byte[] input, int offset, int len) {
        return builder().update(input, offset, len).digest().asString();
    }

    public String getAlgorithm() {
        return algorithm;
    }
}

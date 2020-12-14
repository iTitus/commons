package io.github.ititus.math.base;

import java.util.Objects;

public final class HexConverter extends BaseConverter {

    public static final HexConverter INSTANCE = new HexConverter();

    private HexConverter() {
        super(16);
    }

    public String encode(byte[] bytes) {
        if (Objects.requireNonNull(bytes).length == 0) {
            throw new IllegalArgumentException();
        } else if (bytes.length == 1 && bytes[0] == 0) {
            return String.valueOf(character(0));
        }

        StringBuilder sb = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            sb.append(character((b & 0xf0) >>> 4)).append(character(b & 0x0f));
        }
        return sb.toString();
    }
}

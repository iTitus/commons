package io.github.ititus.commons.math.base;

import io.github.ititus.commons.math.base.binary.BinaryConverter;

public final class BaseConverters {

    public static final BinaryConverter BINARY = BinaryConverter.INSTANCE;
    public static final BaseConverter OCTAL = new BaseConverter(8);
    public static final HexConverter HEXADECIMAL = HexConverter.INSTANCE;
    public static final Base64Converter BASE_64 = Base64Converter.INSTANCE;

    private BaseConverters() {
    }
}

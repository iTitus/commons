package io.github.ititus.commons.math.base;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;

public final class Base64Converter {

    public static final Base64Converter INSTANCE = new Base64Converter();

    private Base64Converter() {
    }

    public String decodeGitHub(String s) {
        StringBuilder b = new StringBuilder();
        for (String line : s.split(Pattern.quote("\\n"))) {
            b.append(new String(Base64.getDecoder().decode(line), StandardCharsets.UTF_8));
        }
        return b.toString();
    }
}

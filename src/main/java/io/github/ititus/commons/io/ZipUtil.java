package io.github.ititus.commons.io;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;

public final class ZipUtil {

    private ZipUtil() {
    }

    public static FileSystem openZip(Path zip) throws IOException {
        return openZip(zip, false);
    }

    public static FileSystem openZip(Path zip, boolean create) throws IOException {
        URI uri;
        try {
            uri = new URI("jar", decodeUri(zip.normalize().toUri().toString()), null);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        Map<String, ?> env = Map.of("create", create);
        return FileSystems.newFileSystem(uri, env);
    }

    /**
     * from ZipPath#decode
     */
    private static int decode(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        } else if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }

        throw new IllegalArgumentException("illegal character '" + c + "' for decode");
    }

    /**
     * from ZipPath#decodeUri
     */
    private static String decodeUri(String s) {
        if (s == null) {
            return null;
        }

        int n = s.length();
        if (n == 0) {
            return s;
        } else if (s.indexOf('%') < 0) {
            return s;
        }

        StringBuilder sb = new StringBuilder(n);
        byte[] bb = new byte[n];
        boolean betweenBrackets = false;
        for (int i = 0; i < n; ) {
            char c = s.charAt(i);
            if (c == '[') {
                betweenBrackets = true;
            } else if (betweenBrackets && c == ']') {
                betweenBrackets = false;
            }

            if (c != '%' || betweenBrackets) {
                sb.append(c);
                i++;
                continue;
            }

            int nb = 0;
            while (c == '%') {
                bb[nb++] = (byte) (((decode(s.charAt(++i)) & 0xf) << 4) | (decode(s.charAt(++i)) & 0xf));
                if (++i >= n) {
                    break;
                }

                c = s.charAt(i);
            }

            sb.append(new String(bb, 0, nb, StandardCharsets.UTF_8));
        }

        return sb.toString();
    }
}

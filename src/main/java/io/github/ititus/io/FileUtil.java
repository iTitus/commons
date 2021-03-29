package io.github.ititus.io;

import java.nio.file.Path;
import java.util.Locale;
import java.util.Objects;

public final class FileUtil {

    private FileUtil() {
    }

    public static String getExtension(Path p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        return getExtension(p.getFileName().toString());
    }

    public static String getExtension(String name) {
        int i = name.lastIndexOf('.');
        if (i > 0 && i < name.length() - 1) {
            return name.substring(i + 1).toLowerCase(Locale.ROOT);
        }

        return "";
    }

    public static String getNameWithoutExtension(Path p) {
        return getNameWithoutExtension(Objects.requireNonNull(p).getFileName().toString());
    }

    public static String getNameWithoutExtension(String name) {
        int i = name.lastIndexOf('.');
        if (i > 0 && i < name.length() - 1) {
            return name.substring(0, i);
        }

        return name;
    }

    public static void closeSilently(AutoCloseable c) {
        try {
            c.close();
        } catch (Exception ignored) {
        }
    }
}

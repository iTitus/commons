package io.github.ititus.io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;

public final class PathUtil {

    public static final Comparator<Path> ASCIIBETICAL = (p1, p2) -> {
        try {
            p1 = p1.toRealPath();
            p2 = p2.toRealPath();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        int c1 = p1.getNameCount();
        int c2 = p2.getNameCount();

        int last = Math.min(c1, c2) - 1;
        for (int i = 0; i < last; i++) {
            int c = p1.getName(i).toString().compareTo(p2.getName(i).toString());
            if (c != 0) {
                return c;
            }
        }

        int c = c1 - c2;
        if (c == 0 && !p1.getName(last).toString().equals(p2.getName(last).toString())) {
            boolean d1 = Files.isDirectory(p1);
            boolean d2 = Files.isDirectory(p2);
            if (d1 != d2) {
                return d1 ? 1 : -1;
            }
        }

        return c;
    };

    private PathUtil() {
    }

    public static Optional<String> getExtension(Path p) {
        return getExtension(p.getFileName().toString());
    }

    public static Optional<String> getExtension(String name) {
        int i = name.lastIndexOf('.');
        if (i > 0 && i < name.length() - 1) {
            return Optional.of(name.substring(i + 1).toLowerCase(Locale.ROOT));
        }

        return Optional.empty();
    }

    public static String getNameWithoutExtension(Path p) {
        return getNameWithoutExtension(p.getFileName().toString());
    }

    public static String getNameWithoutExtension(String name) {
        int i = name.lastIndexOf('.');
        if (i > 0 && i < name.length() - 1) {
            return name.substring(0, i);
        }

        return name;
    }
}

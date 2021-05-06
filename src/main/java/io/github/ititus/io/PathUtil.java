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

        boolean sameLength = c1 == c2;
        int min = Math.min(c1, c2);

        for (int i = 0; i < min; i++) {
            String n1 = p1.getName(i).toString();
            String n2 = p2.getName(i).toString();

            if (sameLength && i == min - 1) {
                boolean d1 = Files.isDirectory(p1);
                boolean d2 = Files.isDirectory(p2);
                if (d1 != d2) {
                    return d1 ? -1 : 1;
                }
            }

            int c = n1.compareTo(n2);
            if (c != 0) {
                return c;
            }
        }

        return c1 - c2;
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

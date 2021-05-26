package io.github.ititus.io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;

public final class PathUtil {

    public static final Comparator<Path> ASCIIBETICAL = PathUtil::compareAsciibetical;
    public static final Comparator<Path> ASCIIBETICAL_NORMALIZE = (p1, p2) -> compareAsciibetical(p1.normalize(), p2.normalize());
    public static final Comparator<Path> ASCIIBETICAL_REAL = (p1, p2) -> compareAsciibetical(toRealPath(p1), toRealPath(p2));
    public static final Comparator<Path> ASCIIBETICAL_FILES_FIRST = PathUtil::compareAsciibeticalFilesFirst;
    public static final Comparator<Path> ASCIIBETICAL_NORMALIZE_FILES_FIRST = (p1, p2) -> compareAsciibeticalFilesFirst(p1.normalize(), p2.normalize());
    public static final Comparator<Path> ASCIIBETICAL_REAL_FILES_FIRST = (p1, p2) -> compareAsciibeticalFilesFirst(toRealPath(p1), toRealPath(p2));

    private PathUtil() {
    }

    public static Path toRealPath(Path p) {
        try {
            return p.toRealPath();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static Path resolveRealDir(Path p) {
        return resolveRealPath(p, true);
    }

    public static Path resolveRealFile(Path p) {
        return resolveRealPath(p, false);
    }

    public static Path resolveRealPath(Path p, boolean isDir) {
        try {
            Files.createDirectories(isDir ? p : p.normalize().getParent());
            p = p.toRealPath();

            if (isDir && !Files.isDirectory(p)) {
                throw new IllegalStateException("expected " + p + " to be a dir");
            } else if (!isDir && !Files.isRegularFile(p)) {
                throw new IllegalStateException("expected " + p + " to be a regular file");
            }

            return p;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static int compareAsciibetical(Path p1, Path p2) {
        if (p1.isAbsolute() != p2.isAbsolute()) {
            throw new IllegalArgumentException("both paths must either be absolute or relative");
        }

        int c1 = p1.getNameCount();
        int c2 = p2.getNameCount();

        int last = Math.min(c1, c2);
        for (int i = 0; i < last; i++) {
            int c = p1.getName(i).toString().compareTo(p2.getName(i).toString());
            if (c != 0) {
                return c;
            }
        }

        return c1 - c2;
    }

    public static int compareAsciibeticalFilesFirst(Path p1, Path p2) {
        if (p1.isAbsolute() != p2.isAbsolute()) {
            throw new IllegalArgumentException("both paths must either be absolute or relative");
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

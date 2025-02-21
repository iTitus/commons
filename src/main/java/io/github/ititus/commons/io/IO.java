package io.github.ititus.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public final class IO {

    private IO() {
    }

    public static void copyUrlToPath(String url, Path outPath) {
        copyUrlToPath(toURL(url), outPath);
    }

    public static void copyUrlToPath(URL url, Path outPath) {
        try (
                ReadableByteChannel in = openReadChannel(url);
                FileChannel out = openOverwriteFileChannel(outPath)
        ) {
            copy(in, out);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void copy(ReadableByteChannel in, FileChannel out) {
        try {
            out.transferFrom(in, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static URL toURL(String url) {
        try {
            return new URI(url).toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ReadableByteChannel openReadChannel(URL url) {
        Objects.requireNonNull(url);
        InputStream is;
        try {
            is = url.openStream();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return Channels.newChannel(is);
    }

    public static FileChannel openCreateNewFileChannel(Path path) {
        Objects.requireNonNull(path);
        try {
            return FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static FileChannel openOverwriteFileChannel(Path path) {
        Objects.requireNonNull(path);
        try {
            return FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static FileChannel openAppendFileChannel(Path path) {
        Objects.requireNonNull(path);
        try {
            return FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static FileChannel openReadFileChannel(Path path) {
        Objects.requireNonNull(path);
        try {
            return FileChannel.open(path, StandardOpenOption.READ);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void closeSilently(AutoCloseable c) {
        if (c == null) {
            return;
        }

        try {
            c.close();
        } catch (Exception ignored) {
        }
    }
}

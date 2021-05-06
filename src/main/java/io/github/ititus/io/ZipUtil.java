package io.github.ititus.io;

import java.io.IOException;
import java.net.URI;
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
        URI uri = URI.create("jar:file:" + zip.toUri().getRawPath());
        Map<String, ?> env = Map.of("create", create);
        return FileSystems.newFileSystem(uri, env);
    }
}

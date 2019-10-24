package io.github.ititus.math.hash;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public final class SHA1 extends Hash {

    public static final SHA1 INSTANCE = new SHA1();

    private SHA1() {
        super("SHA1");
    }

    public String computeGitBlobSHA1(Path file) {
        if (!Files.isRegularFile(Objects.requireNonNull(file))) {
            throw new IllegalArgumentException();
        }

        HashBuilder hb = builder();

        try (FileChannel ch = FileChannel.open(file, StandardOpenOption.READ)) {
            long size = ch.size();
            hb.update("blob " + size + '\0').update(ch.map(FileChannel.MapMode.READ_ONLY, 0, size));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return hb.digest().asString();
    }

    public String computeGitBlobSHA1(String data) {
        byte[] bytes = Objects.requireNonNull(data).getBytes(StandardCharsets.UTF_8);
        return builder().update("blob " + bytes.length + '\0').update(data).digest().asString();
    }
}

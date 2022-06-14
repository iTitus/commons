package io.github.ititus.commons.io;

import com.google.common.jimfs.Jimfs;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.github.ititus.commons.io.PathUtil.ASCIIBETICAL_REAL_FILES_FIRST;
import static io.github.ititus.commons.io.PathUtil.compareAsciibetical;
import static org.assertj.core.api.Assertions.assertThat;

class PathUtilTest {

    @Test
    void testAsciibetical1() {
        Path a = Path.of("a");
        assertThat(compareAsciibetical(a, a)).isZero();
    }

    @Test
    void testAsciibetical2() {
        Path a = Path.of("a/a");
        assertThat(compareAsciibetical(a, a)).isZero();
    }

    @Test
    void testAsciibetical3() {
        Path a = Path.of("a");
        Path b = Path.of("b");
        assertThat(compareAsciibetical(a, b)).isNegative();
    }

    @Test
    void testAsciibetical4() {
        Path a = Path.of("a/a");
        Path b = Path.of("a/b");
        assertThat(compareAsciibetical(a, b)).isNegative();
    }

    @Test
    void testAsciibetical5() {
        Path a = Path.of("a");
        Path b = Path.of("a/b");
        assertThat(compareAsciibetical(a, b)).isNegative();
    }

    @Test
    void testAsciibetical6() {
        Path a = Path.of("b");
        Path b = Path.of("a/b");
        assertThat(compareAsciibetical(a, b)).isNegative();
    }

    @Test
    void testAsciibeticalReal1() throws IOException {
        try (FileSystem fs = Jimfs.newFileSystem()) {
            Path a = fs.getPath("a");
            Files.createFile(a);

            //noinspection EqualsWithItself
            assertThat(ASCIIBETICAL_REAL_FILES_FIRST.compare(a, a)).isZero();
        }
    }

    @Test
    void testAsciibeticalReal2() throws IOException {
        try (FileSystem fs = Jimfs.newFileSystem()) {
            Path a = fs.getPath("a/a");
            Files.createFile(PathUtil.createParentsAndResolveFile(a));

            //noinspection EqualsWithItself
            assertThat(ASCIIBETICAL_REAL_FILES_FIRST.compare(a, a)).isZero();
        }
    }

    @Test
    void testAsciibeticalReal3() throws IOException {
        try (FileSystem fs = Jimfs.newFileSystem()) {
            Path a = fs.getPath("a");
            Path b = fs.getPath("b");

            Files.createFile(PathUtil.createParentsAndResolveFile(a));
            Files.createFile(PathUtil.createParentsAndResolveFile(b));

            assertThat(ASCIIBETICAL_REAL_FILES_FIRST.compare(a, b)).isNegative();
        }
    }

    @Test
    void testAsciibeticalReal4() throws IOException {
        try (FileSystem fs = Jimfs.newFileSystem()) {
            Path a = fs.getPath("a/a");
            Path b = fs.getPath("a/b");

            Files.createFile(PathUtil.createParentsAndResolveFile(a));
            Files.createFile(PathUtil.createParentsAndResolveFile(b));

            assertThat(ASCIIBETICAL_REAL_FILES_FIRST.compare(a, b)).isNegative();
        }
    }

    @Test
    void testAsciibeticalReal5() throws IOException {
        try (FileSystem fs = Jimfs.newFileSystem()) {
            Path a = fs.getPath("a");
            Path b = fs.getPath("a/b");

            Files.createDirectories(a);
            Files.createFile(PathUtil.createParentsAndResolveFile(b));

            assertThat(ASCIIBETICAL_REAL_FILES_FIRST.compare(a, b)).isNegative();
        }
    }

    @Test
    void testAsciibeticalReal6() throws IOException {
        try (FileSystem fs = Jimfs.newFileSystem()) {
            Path a = fs.getPath("b");
            Path b = fs.getPath("a/b");

            PathUtil.createOrResolveRealDir(a);
            Files.createFile(PathUtil.createParentsAndResolveFile(b));

            assertThat(ASCIIBETICAL_REAL_FILES_FIRST.compare(a, b)).isNegative();
        }
    }

    @Test
    void testAsciibeticalReal7() throws IOException {
        try (FileSystem fs = Jimfs.newFileSystem()) {
            Path a = fs.getPath("b");
            Path b = fs.getPath("a");

            Files.createFile(PathUtil.createParentsAndResolveFile(a));
            PathUtil.createOrResolveRealDir(b);

            assertThat(ASCIIBETICAL_REAL_FILES_FIRST.compare(a, b)).isNegative();
        }
    }
}

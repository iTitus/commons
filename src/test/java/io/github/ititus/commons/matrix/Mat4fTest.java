package io.github.ititus.commons.matrix;

import io.github.ititus.commons.math.matrix.Mat4f;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Mat4fTest {

    @Test
    void testMultiply() {
        Mat4f a = new Mat4f(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        );
        Mat4f b = new Mat4f(
                17, 18, 19, 20,
                21, 22, 23, 24,
                25, 26, 27, 28,
                29, 30, 31, 32
        );
        Mat4f c = new Mat4f(
                250, 260, 270, 280,
                618, 644, 670, 696,
                986, 1028, 1070, 1112,
                1354, 1412, 1470, 1528
        );

        assertThat(a.multiply(b)).isEqualTo(c);
    }
}

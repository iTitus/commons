package io.github.ititus.math.permutation;

import org.junit.jupiter.api.Test;

import static io.github.ititus.data.ArrayUtil.array;
import static io.github.ititus.math.permutation.Permutations.getAllArraysWithSum;
import static org.assertj.core.api.Assertions.assertThat;

class GetAllArraysWithSumTest {

    @Test
    void sum0() {
        assertThat(getAllArraysWithSum(0, 0)).containsExactlyInAnyOrder(new int[0]);
        assertThat(getAllArraysWithSum(1, 0)).containsExactlyInAnyOrder(new int[1]);
    }

    @Test
    void sum1() {
        assertThat(getAllArraysWithSum(0, 1)).containsExactlyInAnyOrder();
        assertThat(getAllArraysWithSum(1, 1)).containsExactlyInAnyOrder(array(1));
        assertThat(getAllArraysWithSum(2, 1)).containsExactlyInAnyOrder(array(0, 1), array(1, 0));
        assertThat(getAllArraysWithSum(3, 1)).containsExactlyInAnyOrder(array(0, 0, 1), array(0, 1, 0), array(1, 0, 0));
    }

    @Test
    void sum2() {
        assertThat(getAllArraysWithSum(0, 2)).containsExactlyInAnyOrder();
        assertThat(getAllArraysWithSum(1, 2)).containsExactlyInAnyOrder(array(2));
        assertThat(getAllArraysWithSum(2, 2)).containsExactlyInAnyOrder(array(0, 2), array(1, 1), array(2, 0));
        assertThat(getAllArraysWithSum(3, 2)).containsExactlyInAnyOrder(
                array(0, 0, 2), array(0, 1, 1), array(0, 2, 0),
                array(1, 0, 1), array(1, 1, 0),
                array(2, 0, 0)
        );
        assertThat(getAllArraysWithSum(4, 2)).containsExactlyInAnyOrder(
                array(0, 0, 0, 2), array(0, 0, 1, 1), array(0, 0, 2, 0), array(0, 1, 0, 1), array(0, 1, 1, 0),
                array(0, 2, 0, 0),
                array(1, 0, 0, 1), array(1, 0, 1, 0), array(1, 1, 0, 0),
                array(2, 0, 0, 0)
        );
    }
}

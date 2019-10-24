package io.github.ititus.math.permutation;

import org.junit.Test;

import static io.github.ititus.TestUtil.deepEq;
import static io.github.ititus.data.ArrayUtil.array;
import static io.github.ititus.math.permutation.Permutations.getAllArraysWithSum;
import static org.junit.Assert.assertThat;

public class TestGetAllArraysWithSum {

    @Test
    public void sum0() {
        assertThat(getAllArraysWithSum(0, 0), deepEq(new int[0]));
        assertThat(getAllArraysWithSum(1, 0), deepEq(new int[1]));
    }

    @Test
    public void sum1() {
        assertThat(getAllArraysWithSum(0, 1), deepEq());
        assertThat(getAllArraysWithSum(1, 1), deepEq(array(1)));
        assertThat(getAllArraysWithSum(2, 1), deepEq(array(0, 1), array(1, 0)));
        assertThat(getAllArraysWithSum(3, 1), deepEq(array(0, 0, 1), array(0, 1, 0), array(1, 0, 0)));
    }

    @Test
    public void sum2() {
        assertThat(getAllArraysWithSum(0, 2), deepEq());
        assertThat(getAllArraysWithSum(1, 2), deepEq(array(2)));
        assertThat(getAllArraysWithSum(2, 2), deepEq(array(0, 2), array(1, 1), array(2, 0)));
        assertThat(getAllArraysWithSum(3, 2), deepEq(
                array(0, 0, 2), array(0, 1, 1), array(0, 2, 0),
                array(1, 0, 1), array(1, 1, 0),
                array(2, 0, 0)
        ));
        assertThat(getAllArraysWithSum(4, 2), deepEq(
                array(0, 0, 0, 2), array(0, 0, 1, 1), array(0, 0, 2, 0), array(0, 1, 0, 1), array(0, 1, 1, 0), array(0, 2, 0, 0),
                array(1, 0, 0, 1), array(1, 0, 1, 0), array(1, 1, 0, 0),
                array(2, 0, 0, 0)
        ));
    }
}

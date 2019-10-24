package io.github.ititus;

import io.github.ititus.data.ObjectUtil;
import io.github.ititus.math.number.BigRational;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public final class TestUtil {

    private TestUtil() {
    }

    public static Matcher<BigRational> closeTo(BigRational operand, BigRational error) {
        if (error.isNegative()) {
            throw new IllegalArgumentException();
        }

        return new TypeSafeDiagnosingMatcher<>() {
            @Override
            protected boolean matchesSafely(BigRational item, Description mismatchDescription) {
                BigRational delta = item.subtract(operand).abs();

                if (delta.subtract(error).compareTo(error) > 0) {
                    mismatchDescription.appendValue(item).appendText(" differed by ").appendValue(delta);
                    return false;
                }

                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("a numeric value within ").appendValue(error).appendText(" of ").appendValue(operand);
            }
        };
    }

    public static <T> BaseMatcher<? extends Collection<? extends T>> deepEq() {
        return deepEq(List.of());
    }

    public static <T> BaseMatcher<? extends Collection<? extends T>> deepEq(T element) {
        return deepEq(List.of(element));
    }

    @SafeVarargs
    public static <T> BaseMatcher<? extends Collection<? extends T>> deepEq(T... elements) {
        return deepEq(List.of(elements));
    }

    public static <T> BaseMatcher<? extends Collection<? extends T>> deepEq(Collection<?> c) {
        return new BaseMatcher<>() {

            @Override
            public boolean matches(Object that) {
                if (!(that instanceof Collection)) {
                    return false;
                }

                Collection<?> thatC = (Collection<?>) that;

                if (c.size() != thatC.size()) {
                    return false;
                }

                Iterator<?> it = c.iterator();
                Iterator<?> thatIt = thatC.iterator();
                while (it.hasNext() && thatIt.hasNext()) {
                    if (!Objects.deepEquals(it.next(), thatIt.next())) {
                        return false;
                    }
                }

                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(ObjectUtil.deepToString(c));
            }

            @Override
            public void describeMismatch(Object that, Description description) {
                description.appendText("was ").appendText(ObjectUtil.deepToString(that));
            }
        };
    }

}

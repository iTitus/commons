package io.github.ititus.commons.math.random;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomUtil {

    @SafeVarargs
    public static <T> T select(Random r, T... a) {
        Objects.requireNonNull(r);
        if (Objects.requireNonNull(a).length == 0) {
            throw new IllegalArgumentException();
        }

        return a[r.nextInt(a.length)];
    }

    @SuppressWarnings("unchecked")
    public static <T> T select(Random r, Collection<? extends T> c) {
        Objects.requireNonNull(r);
        if (Objects.requireNonNull(c).isEmpty()) {
            throw new IllegalArgumentException();
        } else if (c instanceof List) {
            return select(r, (List<? extends T>) c);
        }

        return (T) c.toArray(Object[]::new)[r.nextInt(c.size())];
    }

    public static <T> T select(Random r, List<? extends T> l) {
        Objects.requireNonNull(r);
        if (Objects.requireNonNull(l).isEmpty()) {
            throw new IllegalArgumentException();
        }

        return l.get(r.nextInt(l.size()));
    }

    public static int rollDice(Random r, int sides) {
        Objects.requireNonNull(r);
        if (sides < 1) {
            throw new IllegalArgumentException();
        }

        return rollDice(r, 1, sides);
    }

    public static int rollDice(Random r, int diceCount, int sides) {
        Objects.requireNonNull(r);
        if (diceCount <= 0 || sides <= 0) {
            throw new IllegalArgumentException();
        }

        int sum = 0;
        for (int i = 0; i < diceCount; i++) {
            sum += 1 + r.nextInt(sides);
        }

        return sum;
    }
}

package io.github.ititus.math.random;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class AdvRandom extends Random {

    public AdvRandom() {
        super();
    }

    public AdvRandom(long seed) {
        super(seed);
    }

    @SafeVarargs
    public final <T> T select(T... a) {
        return RandomUtil.select(this, a);
    }

    public <T> T select(Collection<? extends T> c) {
        return RandomUtil.select(this, c);
    }

    public <T> T select(List<? extends T> l) {
        return RandomUtil.select(this, l);
    }

    public int rollDice(int sides) {
        return RandomUtil.rollDice(this, sides);
    }

    public int rollDice(int diceCount, int sides) {
        return RandomUtil.rollDice(this, diceCount, sides);
    }
}

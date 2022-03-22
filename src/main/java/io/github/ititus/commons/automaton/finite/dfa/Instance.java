package io.github.ititus.commons.automaton.finite.dfa;

import java.util.Objects;

public final class Instance {

    private static final Instance INVALID = new Instance(null);

    private final State current;

    private Instance(State initial) {
        this.current = initial;
    }

    public static Instance invalid() {
        return INVALID;
    }

    public static Instance create(State current) {
        return new Instance(Objects.requireNonNull(current));
    }

    public Instance accept(CharSequence input) {
        if (input.isEmpty()) {
            return this;
        }

        Instance i = this;
        int len = input.length();
        int idx = 0;
        while (idx < len && i.isValid()) {
            char ch = input.charAt(idx++);
            int cp = ch;
            if (Character.isHighSurrogate(ch) && idx < len) {
                char next = input.charAt(idx);
                if (Character.isLowSurrogate(next)) {
                    idx++;
                    cp = Character.toCodePoint(ch, next);
                }
            }

            i = i.accept(cp);
        }

        return i;
    }

    public Instance accept(int codepoint) {
        if (isInvalid()) {
            throw new IllegalStateException();
        }

        var target = current.nullableAccept(codepoint);
        return target != null ? new Instance(target) : INVALID;
    }

    public State current() {
        return current;
    }

    public boolean isFinished() {
        return isValid() && current.end();
    }

    public boolean isValid() {
        return current != null;
    }

    public boolean isInvalid() {
        return current == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Instance)) {
            return false;
        }

        Instance instance = (Instance) o;
        return Objects.equals(current, instance.current);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(current);
    }

    @Override
    public String toString() {
        return "Instance{" +
                "current=" + current +
                '}';
    }
}

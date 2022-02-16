package io.github.ititus.commons.automaton.finite.nfa;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class Instance {

    private static final Instance INVALID = new Instance(Set.of());

    private final Set<State> current;

    private Instance(Set<State> initial) {
        this.current = initial;
    }

    public static Instance invalid() {
        return INVALID;
    }

    public static Instance create(State current) {
        return new Instance(Set.copyOf(Objects.requireNonNull(current).acceptEpsilon()));
    }

    public static Instance create(Set<State> current) {
        if (Objects.requireNonNull(current).isEmpty()) {
            return invalid();
        }

        return new Instance(current.stream().flatMap(s -> s.acceptEpsilon().stream()).collect(Collectors.toUnmodifiableSet()));
    }

    public Instance accept(CharSequence input) {
        if (isInvalid()) {
            throw new IllegalStateException();
        }

        Instance i = this;
        for (var it = input.codePoints().iterator(); i.isValid() && it.hasNext(); ) {
            i = i.accept(it.next());
        }

        return i;
    }

    public Instance acceptEpsilon() {
        if (isInvalid()) {
            throw new IllegalStateException();
        }

        return this;
    }

    public Instance accept(int codepoint) {
        if (isInvalid()) {
            throw new IllegalStateException();
        }

        return create(
                current.stream()
                        .flatMap(s -> s.accept(codepoint).stream())
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    public Set<State> current() {
        return current;
    }

    public boolean isFinished() {
        return isValid() && current.stream().anyMatch(State::end);
    }

    public boolean isValid() {
        return !current.isEmpty();
    }

    public boolean isInvalid() {
        return current.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Instance)) {
            return false;
        }

        Instance instance = (Instance) o;
        return current.equals(instance.current);
    }

    @Override
    public int hashCode() {
        return current.hashCode();
    }

    @Override
    public String toString() {
        return "Instance{" +
                "current=" + current +
                '}';
    }
}

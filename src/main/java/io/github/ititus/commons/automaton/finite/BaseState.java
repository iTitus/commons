package io.github.ititus.commons.automaton.finite;

import java.util.Collection;

public interface BaseState<S extends BaseState<S>> {

    String name();

    boolean end();

    Collection<TargetedRule<S>> rules();

}

package io.github.ititus.commons.automaton.finite.dfa;

import io.github.ititus.commons.automaton.finite.DotUtil;
import org.junit.jupiter.api.Test;

class DFATest {

    @Test
    void testMinimize() {
        var s1 = State.create("1");
        var s2 = State.createEnd("2");
        var s3 = State.create("3");
        var s4 = State.create("4");

        s1.addRule(s2, 'c').addRule(s3, 'a');
        s3.addSelfRule('a').addRule(s2, 'c').addRule(s4, 'b');
        s4.addSelfRule('a', 'b').addRule(s2, 'c');

        var dfa = new DFA(s1);
        System.out.println(DotUtil.toDot(dfa));

        var min = dfa.minimize();
        System.out.println(DotUtil.toDot(min));
    }
}

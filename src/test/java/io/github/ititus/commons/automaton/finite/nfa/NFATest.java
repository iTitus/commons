package io.github.ititus.commons.automaton.finite.nfa;

import io.github.ititus.commons.automaton.finite.DotUtil;
import org.junit.jupiter.api.Test;

class NFATest {

    @Test
    void testToDFA() {
        var s0 = State.create("00");
        var s1 = State.create("01");
        var s2 = State.create("02");
        var s3 = State.create("03");
        var s4 = State.create("04");
        var s5 = State.create("05");
        var s6 = State.create("06");
        var s7 = State.create("07");
        var s8 = State.create("08");
        var s9 = State.create("09");
        var s10 = State.createEnd("10");

        s0.addEpsilonTransition(s1).addEpsilonTransition(s7);
        s1.addEpsilonTransition(s2).addEpsilonTransition(s4);
        s2.addRule(s3, 'a');
        s3.addEpsilonTransition(s6);
        s4.addRule(s5, 'b');
        s5.addEpsilonTransition(s6);
        s6.addEpsilonTransition(s1).addEpsilonTransition(s7);
        s7.addRule(s8, 'a');
        s8.addRule(s9, 'b');
        s9.addRule(s10, 'b');

        var nfa = new NFA(s0);
        System.out.println(DotUtil.toDot(nfa));

        var dfa = nfa.toDFA();
        System.out.println(DotUtil.toDot(dfa));
    }
}
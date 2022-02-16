package io.github.ititus.commons.automaton.finite.dfa;

import io.github.ititus.commons.automaton.finite.DotUtil;
import org.junit.jupiter.api.Test;

class DFAMinimizationTests {

    @Test
    void simple() {
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

    @Test
    void simpleString() {
        var initial = State.create("initial");
        var content = State.create("content");
        var escape = State.create("escape");
        var end = State.createEnd("end");

        initial.addRule(content, '"');
        escape.addRule(content, '"', '\\');
        content.addRule(end, '"').addRule(escape, '\\').addSelfNotRule('"', '\\');

        var dfa = new DFA(initial);
        System.out.println(DotUtil.toDot(dfa));

        var minimized = dfa.minimize();
        System.out.println(DotUtil.toDot(minimized));
    }
}

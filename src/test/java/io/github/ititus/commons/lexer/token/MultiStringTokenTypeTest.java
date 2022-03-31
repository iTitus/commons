package io.github.ititus.commons.lexer.token;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static io.github.ititus.commons.lexer.MatchResult.*;
import static org.assertj.core.api.Assertions.assertThat;

class MultiStringTokenTypeTest {

    static class MultiStringTokenTypeForTesting extends MultiStringTokenType<Void> {

        MultiStringTokenTypeForTesting(CharSequence... tokens) {
            super("test", tokens);
        }

        MultiStringTokenTypeForTesting(Collection<? extends CharSequence> tokens) {
            super("test", tokens);
        }

        @Override
        public Void convert(String token) {
            return null;
        }
    }

    @Test
    void testMatches() {
        var t = new MultiStringTokenTypeForTesting("a", "bc", "def");

        assertThat(t.matches("")).isEqualTo(PREFIX_ONLY_MATCH);

        assertThat(t.matches("a")).isEqualTo(FULL_MATCH);
        assertThat(t.matches("b")).isEqualTo(PREFIX_ONLY_MATCH);
        assertThat(t.matches("c")).isEqualTo(NO_MATCH);
        assertThat(t.matches("d")).isEqualTo(PREFIX_ONLY_MATCH);

        assertThat(t.matches("ab")).isEqualTo(NO_MATCH);
        assertThat(t.matches("bc")).isEqualTo(FULL_MATCH);
        assertThat(t.matches("de")).isEqualTo(PREFIX_ONLY_MATCH);

        assertThat(t.matches("bcd")).isEqualTo(NO_MATCH);
        assertThat(t.matches("def")).isEqualTo(FULL_MATCH);

        assertThat(t.matches("defg")).isEqualTo(NO_MATCH);
    }
}

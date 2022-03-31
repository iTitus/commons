package io.github.ititus.commons.lexer.token;

import org.junit.jupiter.api.Test;

import static io.github.ititus.commons.lexer.MatchResult.*;
import static io.github.ititus.commons.lexer.token.StringTokenType.matches;
import static org.assertj.core.api.Assertions.assertThat;

class StringTokenTypeTest {

    @Test
    void testPrefixMatch() {
        assertThat(matches("abc", "")).isEqualTo(PREFIX_ONLY_MATCH);
        assertThat(matches("abc", "a")).isEqualTo(PREFIX_ONLY_MATCH);
        assertThat(matches("abc", "ab")).isEqualTo(PREFIX_ONLY_MATCH);
    }

    @Test
    void testFullMatch() {
        assertThat(matches("abc", "abc")).isEqualTo(FULL_MATCH);
    }

    @Test
    void testNoMatch() {
        assertThat(matches("abc", "b")).isEqualTo(NO_MATCH);
        assertThat(matches("abc", "abcd")).isEqualTo(NO_MATCH);
    }

    @Test
    void testEmptyToken() {
        assertThat(matches("", "")).isEqualTo(FULL_MATCH);
        assertThat(matches("", "a")).isEqualTo(NO_MATCH);
    }
}

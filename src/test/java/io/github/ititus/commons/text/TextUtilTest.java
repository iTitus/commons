package io.github.ititus.commons.text;

import org.junit.jupiter.api.Test;

import static io.github.ititus.commons.text.TextUtil.quote;
import static org.assertj.core.api.Assertions.assertThat;

class TextUtilTest {

    @Test
    void testQuote() {
        assertThat(quote("")).isEqualTo("\"\"");

        assertThat(quote("\"")).isEqualTo("\"\\\"\"");
        assertThat(quote(" \"")).isEqualTo("\" \\\"\"");
        assertThat(quote("  \"")).isEqualTo("\"  \\\"\"");
        assertThat(quote("\" ")).isEqualTo("\"\\\" \"");
        assertThat(quote("\"  ")).isEqualTo("\"\\\"  \"");
        assertThat(quote(" \" ")).isEqualTo("\" \\\" \"");
        assertThat(quote("  \"  ")).isEqualTo("\"  \\\"  \"");

        assertThat(quote("\\\"")).isEqualTo("\"\\\"\"");
        assertThat(quote(" \\\"")).isEqualTo("\" \\\"\"");
        assertThat(quote("  \\\"")).isEqualTo("\"  \\\"\"");
        assertThat(quote("\\\" ")).isEqualTo("\"\\\" \"");
        assertThat(quote("\\\"  ")).isEqualTo("\"\\\"  \"");
        assertThat(quote(" \\\" ")).isEqualTo("\" \\\" \"");
        assertThat(quote("  \\\"  ")).isEqualTo("\"  \\\"  \"");

        assertThat(quote("\\")).isEqualTo("\"\\\\\"");
        assertThat(quote(" \\")).isEqualTo("\" \\\\\"");
        assertThat(quote("  \\")).isEqualTo("\"  \\\\\"");
        assertThat(quote("\\ ")).isEqualTo("\"\\\\ \"");
        assertThat(quote("\\  ")).isEqualTo("\"\\\\  \"");
        assertThat(quote(" \\ ")).isEqualTo("\" \\\\ \"");
        assertThat(quote("  \\  ")).isEqualTo("\"  \\\\  \"");

        assertThat(quote("\\\\")).isEqualTo("\"\\\\\"");
        assertThat(quote(" \\\\")).isEqualTo("\" \\\\\"");
        assertThat(quote("  \\\\")).isEqualTo("\"  \\\\\"");
        assertThat(quote("\\\\ ")).isEqualTo("\"\\\\ \"");
        assertThat(quote("\\\\  ")).isEqualTo("\"\\\\  \"");
        assertThat(quote(" \\\\ ")).isEqualTo("\" \\\\ \"");
        assertThat(quote("  \\\\  ")).isEqualTo("\"  \\\\  \"");
    }
}

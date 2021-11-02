package io.github.ititus.text;

public class TextUtil {

    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static boolean startsWith(CharSequence s, CharSequence prefix) {
        if (s instanceof String && prefix instanceof String) {
            return ((String) s).startsWith((String) prefix);
        }

        int prefixLength = prefix.length();
        if (prefixLength == 0) {
            return true;
        } else if (s.length() < prefixLength) {
            return false;
        }

        for (int i = 0; i < prefixLength; i++) {
            if (s.charAt(i) != prefix.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static String toStringAsEscape(char c) {
        return toStringAsEscape((int) c);
    }

    public static String toStringAsEscape(int codepoint) {
        switch (codepoint) {
            case '\b':
                return "\\b";
            case ' ':
                return "\\s";
            case '\t':
                return "\\t";
            case '\n':
                return "\\n";
            case '\f':
                return "\\f";
            case '\r':
                return "\\r";
            default:
                return Character.toString(codepoint);
        }
    }

    public static String quote(String s) {
        StringBuilder b = new StringBuilder().append('"');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '"') {
                if ((i - 1 < 0 || s.charAt(i - 1) != '\\') || (i - 2 >= 0 && s.charAt(i - 2) == '\\')) {
                    b.append('\\');
                }
            } else if (c == '\\') {
                if ((i - 1 < 0 || s.charAt(i - 1) != '\\') && (i + 1 >= s.length() || (s.charAt(i + 1) != '"' && s.charAt(i + 1) != '\\'))) {
                    b.append('\\');
                }
            }

            b.append(c);
        }

        return b.append('"').toString();
    }
}

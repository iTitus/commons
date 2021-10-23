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
}

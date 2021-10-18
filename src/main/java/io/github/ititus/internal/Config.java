package io.github.ititus.internal;

public final class Config {

    public static final boolean FORCE_ASCII = readBooleanProperty("force_ascii", false);

    private Config() {
    }

    private static String getPropertyName(String name) {
        return System.getProperty("ititus.commons." + name);
    }

    private static boolean readBooleanProperty(String name, boolean def) {
        boolean result = def;
        try {
            result = Boolean.parseBoolean(getPropertyName(name));
        } catch (IllegalArgumentException | NullPointerException ignored) {
        }

        return result;
    }
}

package io.github.ititus.system;

import io.github.ititus.data.Lazy;

import java.util.Locale;

public enum OS {

    WINDOWS,
    MAC,
    UNIX,
    SOLARIS,
    UNKNOWN;

    private static final Lazy<OS> CURRENT = Lazy.of(() -> {
        String osName = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        if (osName.contains("win")) {
            return OS.WINDOWS;
        } else if (osName.contains("mac")) {
            return OS.MAC;
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            return OS.UNIX;
        } else if (osName.contains("solaris") || osName.contains("sunos")) {
            return OS.SOLARIS;
        }

        System.err.println("Unknown OS " + osName);
        return OS.UNKNOWN;
    });

    public static OS current() {
        return CURRENT.get();
    }
}

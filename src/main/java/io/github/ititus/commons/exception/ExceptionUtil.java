package io.github.ititus.commons.exception;

public final class ExceptionUtil {

    private ExceptionUtil() {
    }

    public static void print(Throwable t) {
        System.out.flush();
        System.err.flush();
        print(t, 0);
    }

    private static void print(Throwable t, int indent) {
        String msg = t.getLocalizedMessage();
        System.err.println("  ".repeat(indent) + t.getClass().getName() + (msg != null ? ": " + msg : ""));

        Throwable[] ss = t.getSuppressed();
        if (ss.length > 0) {
            System.err.println("  ".repeat(indent) + "Suppressed (" + ss.length + "):");
            for (int i = 0, length = ss.length; i < length; i++) {
                if (i > 0) {
                    System.err.println();
                }

                print(ss[i], indent + 1);
            }
        }

        if (t.getCause() != null) {
            System.err.println("  ".repeat(indent) + "Caused by:");
            print(t.getCause(), indent + 1);
        }
    }
}

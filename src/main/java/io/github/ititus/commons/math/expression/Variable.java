package io.github.ititus.commons.math.expression;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Variable {

    private static final Pattern VARIABLE_PATTERN = Pattern.compile(
            "[a-zA-Z][a-zA-Z0-9]*"
                    + "(?:_(?:(?:[a-zA-Z0-9])|(?:\\{[a-zA-Z0-9]+})))?"
                    + "(?:^(?:(?:[a-zA-Z0-9])|(?:\\{[a-zA-Z0-9]+})))?"
    );

    private static final Variable X = new Variable("x");
    private static final Variable Y = new Variable("y");
    private static final Variable Z = new Variable("z");
    private static final Variable I = new Variable("i");
    private static final Variable J = new Variable("j");
    private static final Variable K = new Variable("k");
    private static final Variable N = new Variable("n");

    private final String name;

    private Variable(String name) {
        if (!VARIABLE_PATTERN.matcher(Objects.requireNonNull(name)).matches()) {
            throw new IllegalArgumentException("Invalid name");
        }

        this.name = name;
    }

    public static Variable of(String name) {
        return new Variable(name);
    }

    public static Variable x() {
        return X;
    }

    public static Variable y() {
        return Y;
    }

    public static Variable z() {
        return Z;
    }

    public static Variable i() {
        return I;
    }

    public static Variable j() {
        return J;
    }

    public static Variable k() {
        return K;
    }

    public static Variable n() {
        return N;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Variable)) {
            return false;
        }

        Variable variable = (Variable) o;
        return name.equals(variable.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name='" + name + '\'' +
                '}';
    }
}

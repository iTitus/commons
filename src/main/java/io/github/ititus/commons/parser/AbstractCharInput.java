package io.github.ititus.commons.parser;

abstract class AbstractCharInput implements CharInput {

    protected AbstractCharInput() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof CharInput)) {
            return false;
        }

        CharInput that = (CharInput) o;
        return seq().toString().contentEquals(that.seq());
    }

    @Override
    public int hashCode() {
        return seq().toString().hashCode();
    }

    @Override
    public String toString() {
        return "CharInput[" + seq() + ']';
    }
}

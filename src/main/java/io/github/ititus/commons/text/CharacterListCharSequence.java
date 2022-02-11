package io.github.ititus.commons.text;

import java.util.List;

public class CharacterListCharSequence implements CharSequence {

    private final List<Character> characters;

    public CharacterListCharSequence(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public int length() {
        return characters.size();
    }

    @Override
    public char charAt(int index) {
        return characters.get(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return SubCharSequence.of(this, start, end);
    }

    @Override
    public String toString() {
        int size = characters.size();
        char[] chars = new char[size];
        for (int i = 0; i < size; i++) {
            chars[i] = characters.get(i);
        }

        return String.valueOf(chars);
    }
}

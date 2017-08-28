package org.parsky.character;

public class EndOfInputCharacterMatcher implements CharacterMatcher {
    public static final char EOI = (char) -1;

    private static EndOfInputCharacterMatcher SINGLETON = new EndOfInputCharacterMatcher();

    public static EndOfInputCharacterMatcher endOfInput() {
        return SINGLETON;
    }

    private EndOfInputCharacterMatcher() {}

    @Override
    public boolean matches(char character) {
        return character == EOI;
    }
}

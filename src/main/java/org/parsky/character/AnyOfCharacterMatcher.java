package org.parsky.character;

public class AnyOfCharacterMatcher implements CharacterMatcher {
    private final char[] possibilities;

    public AnyOfCharacterMatcher(char[] possibilities) {
        this.possibilities = possibilities;
    }

    @Override
    public boolean matches(char character) {
        for (char possibility : possibilities) {
            if (possibility == character)
                return true;
        }

        return false;
    }
}

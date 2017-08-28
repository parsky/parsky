package org.parsky.character;

public class NoneOfCharacterMatcher implements CharacterMatcher {
    private final char[] possibilities;

    public NoneOfCharacterMatcher(char[] possibilities) {
        this.possibilities = possibilities;
    }

    @Override
    public boolean matches(char character) {
        for (char possibility : possibilities) {
            if (possibility == character)
                return false;
        }

        return true;
    }
}

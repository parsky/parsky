package org.parsky.character;

public class NotCharacterMatcher implements CharacterMatcher {
    private final CharacterMatcher delegate;

    public NotCharacterMatcher(CharacterMatcher delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean matches(char character) {
        return !delegate.matches(character);
    }
}

package org.parsky.character;

public class AnyCharacterMatcher implements CharacterMatcher {
    private static final AnyCharacterMatcher SINGLETON = new AnyCharacterMatcher();

    public static AnyCharacterMatcher any () {
        return SINGLETON;
    }

    private AnyCharacterMatcher() {}

    @Override
    public boolean matches(char character) {
        return true;
    }
}

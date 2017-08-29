package org.parsky.character;

public class WhiteSpaceCharacterMatcher implements CharacterMatcher {
    private static WhiteSpaceCharacterMatcher SINGLETON = new WhiteSpaceCharacterMatcher();

    public static WhiteSpaceCharacterMatcher whitespace () {
        return SINGLETON;
    }

    private WhiteSpaceCharacterMatcher() {}

    @Override
    public boolean matches(char character) {
        return Character.isWhitespace(character);
    }
}

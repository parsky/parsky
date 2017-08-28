package org.parsky.character;

public class CharacterRangeMatcher implements CharacterMatcher {
    private final char start;
    private final char end;

    public CharacterRangeMatcher(char start, char end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean matches(char character) {
        return character >= start && character <= end;
    }
}

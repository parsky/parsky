package org.parsky.character;

import java.util.List;

public class OrCharacterMatcher implements CharacterMatcher {
    private final List<CharacterMatcher> options;

    public OrCharacterMatcher(List<CharacterMatcher> options) {
        this.options = options;
    }

    @Override
    public boolean matches(char character) {
        for (CharacterMatcher option : options) {
            if (option.matches(character)) return true;
        }

        return false;
    }
}

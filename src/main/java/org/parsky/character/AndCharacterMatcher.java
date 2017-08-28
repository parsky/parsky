package org.parsky.character;

import java.util.List;

public class AndCharacterMatcher implements CharacterMatcher {
    private final List<CharacterMatcher> options;

    public AndCharacterMatcher(List<CharacterMatcher> options) {
        this.options = options;
    }

    @Override
    public boolean matches(char character) {
        for (CharacterMatcher option : options) {
            if (!option.matches(character)) return false;
        }

        return true;
    }
}

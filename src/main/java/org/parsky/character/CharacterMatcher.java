package org.parsky.character;

import com.google.common.collect.ImmutableList;

public interface CharacterMatcher {
    static CharacterMatcher anyOf(String listOfChars) {
        return new AnyOfCharacterMatcher(listOfChars.toCharArray());
    }
    static CharacterMatcher any() {
        return AnyCharacterMatcher.any();
    }
    static CharacterMatcher range(char start, char end) {
        return new CharacterRangeMatcher(start, end);
    }
    static CharacterMatcher character(char c) {
        return new CharacterRangeMatcher(c, c);
    }
    static CharacterMatcher noneOf(String listOfChars) {
        return new NoneOfCharacterMatcher(listOfChars.toCharArray());
    }
    static CharacterMatcher endOfInput() {
        return EndOfInputCharacterMatcher.endOfInput();
    }
    static CharacterMatcher and(CharacterMatcher matcher, CharacterMatcher... list) {
        return new AndCharacterMatcher(ImmutableList.<CharacterMatcher>builder().add(matcher).add(list).build());
    }
    static CharacterMatcher or(CharacterMatcher matcher, CharacterMatcher... list) {
        return new OrCharacterMatcher(ImmutableList.<CharacterMatcher>builder().add(matcher).add(list).build());
    }
    static CharacterMatcher not(CharacterMatcher matcher) {
        return new NotCharacterMatcher(matcher);
    }
    static CharacterMatcher whiteSpace() {
        return WhiteSpaceCharacterMatcher.whitespace();
    }

    boolean matches(char character);
}

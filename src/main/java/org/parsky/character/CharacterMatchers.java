package org.parsky.character;

import com.google.common.collect.ImmutableList;

public class CharacterMatchers {
    public static CharacterMatcher anyOf(String listOfChars) {
        return new AnyOfCharacterMatcher(listOfChars.toCharArray());
    }
    public static CharacterMatcher any() {
        return AnyCharacterMatcher.any();
    }
    public static CharacterMatcher range(char start, char end) {
        return new CharacterRangeMatcher(start, end);
    }
    public static CharacterMatcher character(char c) {
        return new CharacterRangeMatcher(c, c);
    }
    public static CharacterMatcher noneOf(String listOfChars) {
        return new NoneOfCharacterMatcher(listOfChars.toCharArray());
    }
    public static CharacterMatcher endOfInput() {
        return EndOfInputCharacterMatcher.endOfInput();
    }
    public static CharacterMatcher and(CharacterMatcher matcher, CharacterMatcher... list) {
        return new AndCharacterMatcher(ImmutableList.<CharacterMatcher>builder().add(matcher).add(list).build());
    }
    public static CharacterMatcher or(CharacterMatcher matcher, CharacterMatcher... list) {
        return new OrCharacterMatcher(ImmutableList.<CharacterMatcher>builder().add(matcher).add(list).build());
    }
    public static CharacterMatcher not(CharacterMatcher matcher) {
        return new NotCharacterMatcher(matcher);
    }
    public static CharacterMatcher whiteSpace() {
        return WhiteSpaceCharacterMatcher.whitespace();
    }

}

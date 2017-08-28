package org.parsky.character;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class CharacterMatcherTest {
    @Test
    public void synonyms() throws Exception {
        assertThat(CharacterMatcher.anyOf("list"), instanceOf(AnyOfCharacterMatcher.class));
        assertThat(CharacterMatcher.any(), instanceOf(AnyCharacterMatcher.class));
        assertThat(CharacterMatcher.and(mock(CharacterMatcher.class)), instanceOf(AndCharacterMatcher.class));
        assertThat(CharacterMatcher.character('a'), instanceOf(CharacterRangeMatcher.class));
        assertThat(CharacterMatcher.range('a', 'z'), instanceOf(CharacterRangeMatcher.class));
        assertThat(CharacterMatcher.or(mock(CharacterMatcher.class)), instanceOf(OrCharacterMatcher.class));
        assertThat(CharacterMatcher.noneOf("list"), instanceOf(NoneOfCharacterMatcher.class));
        assertThat(CharacterMatcher.endOfInput(), instanceOf(EndOfInputCharacterMatcher.class));
        assertThat(CharacterMatcher.not(mock(CharacterMatcher.class)), instanceOf(NotCharacterMatcher.class));
        assertThat(CharacterMatcher.whiteSpace(), instanceOf(WhiteSpaceCharacterMatcher.class));
    }
}
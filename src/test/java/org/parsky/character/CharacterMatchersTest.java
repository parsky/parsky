package org.parsky.character;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class CharacterMatchersTest {
    @Test
    public void synonyms() throws Exception {
        assertThat(CharacterMatchers.anyOf("list"), instanceOf(AnyOfCharacterMatcher.class));
        assertThat(CharacterMatchers.any(), instanceOf(AnyCharacterMatcher.class));
        assertThat(CharacterMatchers.and(mock(CharacterMatcher.class)), instanceOf(AndCharacterMatcher.class));
        assertThat(CharacterMatchers.character('a'), instanceOf(CharacterRangeMatcher.class));
        assertThat(CharacterMatchers.range('a', 'z'), instanceOf(CharacterRangeMatcher.class));
        assertThat(CharacterMatchers.or(mock(CharacterMatcher.class)), instanceOf(OrCharacterMatcher.class));
        assertThat(CharacterMatchers.noneOf("list"), instanceOf(NoneOfCharacterMatcher.class));
        assertThat(CharacterMatchers.endOfInput(), instanceOf(EndOfInputCharacterMatcher.class));
        assertThat(CharacterMatchers.not(mock(CharacterMatcher.class)), instanceOf(NotCharacterMatcher.class));
        assertThat(CharacterMatchers.whiteSpace(), instanceOf(WhiteSpaceCharacterMatcher.class));
    }
}
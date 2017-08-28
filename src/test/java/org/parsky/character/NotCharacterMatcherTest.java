package org.parsky.character;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class NotCharacterMatcherTest {
    private final CharacterMatcher characterMatcher = mock(CharacterMatcher.class);
    private NotCharacterMatcher underTest = new NotCharacterMatcher(characterMatcher);

    @Test
    public void match() throws Exception {
        char input = 'a';

        given(characterMatcher.matches(input)).willReturn(true);

        boolean result = underTest.matches(input);

        assertThat(result, is(false));
    }
}
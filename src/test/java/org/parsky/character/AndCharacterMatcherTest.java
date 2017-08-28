package org.parsky.character;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class AndCharacterMatcherTest {
    private final CharacterMatcher characterMatcher1 = mock(CharacterMatcher.class);
    private final CharacterMatcher characterMatcher2 = mock(CharacterMatcher.class);
    private final List<CharacterMatcher> characterMatchers = asList(characterMatcher1, characterMatcher2);
    private AndCharacterMatcher underTest = new AndCharacterMatcher(characterMatchers);

    @Test
    public void matchFirstFail() throws Exception {
        char input = 'a';

        given(characterMatcher1.matches(input)).willReturn(false);

        boolean result = underTest.matches(input);

        assertThat(result, is(false));
        verifyZeroInteractions(characterMatcher2);
    }

    @Test
    public void matchSecondFail() throws Exception {
        char input = 'a';

        given(characterMatcher1.matches(input)).willReturn(true);
        given(characterMatcher2.matches(input)).willReturn(false);

        boolean result = underTest.matches(input);

        assertThat(result, is(false));
    }

    @Test
    public void matchBothSuccess() throws Exception {
        char input = 'a';

        given(characterMatcher1.matches(input)).willReturn(true);
        given(characterMatcher2.matches(input)).willReturn(true);

        boolean result = underTest.matches(input);

        assertThat(result, is(true));
    }
}
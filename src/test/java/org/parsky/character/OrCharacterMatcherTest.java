package org.parsky.character;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class OrCharacterMatcherTest {
    private final CharacterMatcher characterMatcher1 = mock(CharacterMatcher.class);
    private final CharacterMatcher characterMatcher2 = mock(CharacterMatcher.class);
    private final List<CharacterMatcher> characterMatchers = asList(characterMatcher1, characterMatcher2);
    private final OrCharacterMatcher underTest = new OrCharacterMatcher(characterMatchers);

    @Test
    public void firstMatch() throws Exception {
        char input = 'a';

        given(characterMatcher1.matches(input)).willReturn(true);

        boolean result = underTest.matches(input);

        assertThat(result, is(true));
        verifyZeroInteractions(characterMatcher2);
    }

    @Test
    public void secondMatch() throws Exception {
        char input = 'a';

        given(characterMatcher1.matches(input)).willReturn(false);
        given(characterMatcher2.matches(input)).willReturn(true);

        boolean result = underTest.matches(input);

        assertThat(result, is(true));
    }

    @Test
    public void bothFail() throws Exception {
        char input = 'a';

        given(characterMatcher1.matches(input)).willReturn(false);
        given(characterMatcher2.matches(input)).willReturn(false);

        boolean result = underTest.matches(input);

        assertThat(result, is(false));
    }
}
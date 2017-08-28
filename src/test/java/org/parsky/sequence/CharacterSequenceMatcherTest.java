package org.parsky.sequence;

import org.junit.Test;
import org.parsky.character.CharacterMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.TextNode;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CharacterSequenceMatcherTest {
    private final CharacterMatcher characterMatcher = mock(CharacterMatcher.class);
    private CharacterSequenceMatcher underTest = new CharacterSequenceMatcher(characterMatcher);

    @Test
    public void matches() throws Exception {
        SequenceMatcherRequest request = SequentTestUtils.request("test");

        given(characterMatcher.matches('t')).willReturn(true);

        SequenceMatcherResult result = underTest.matches(request);

        assertThat(result.matched(), is(true));
        assertThat(result.getJump(), is(1));
        assertThat(result.getMatchResult().getNode(), is(instanceOf(TextNode.class)));
    }

    @Test
    public void mismatch() throws Exception {
        SequenceMatcherRequest request = SequentTestUtils.request("test");

        given(characterMatcher.matches('t')).willReturn(false);

        SequenceMatcherResult result = underTest.matches(request);

        assertThat(result.isMismatch(), is(true));
        assertThat(result.getJump(), is(0));
    }
}
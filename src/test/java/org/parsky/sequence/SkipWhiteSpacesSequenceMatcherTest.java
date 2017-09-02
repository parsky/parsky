package org.parsky.sequence;

import org.junit.Test;
import org.parsky.character.WhiteSpaceCharacterMatcher;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class SkipWhiteSpacesSequenceMatcherTest {
    private SkipWhiteSpacesSequenceMatcher underTest = new SkipWhiteSpacesSequenceMatcher(
            WhiteSpaceCharacterMatcher.whitespace(),
            new StringSequenceMatcher("test")
    );

    @Test
    public void match() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("   test    "));

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getNode(), instanceOf(ContentNode.class));
        assertThat(((ContentNode<String>) result.getMatchResult().getNode()).getContent(), is("test"));
    }

    @Test
    public void mismatch() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("   tes    "));

        assertThat(result.matched(), is(false));
    }
}
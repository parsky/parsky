package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class MatchedTextSequenceMatcherTest {
    private MatchedTextSequenceMatcher underTest = new MatchedTextSequenceMatcher(new OneOrMoreSequenceMatcher(new StringSequenceMatcher("test")));

    @Test
    public void match() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("testtest"));

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getNode(), instanceOf(ContentNode.class));
        assertThat(((ContentNode<String>) result.getMatchResult().getNode()).getContent(), is("testtest"));
    }

    @Test
    public void mismatch() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("te"));

        assertThat(result.matched(), is(false));
    }
}
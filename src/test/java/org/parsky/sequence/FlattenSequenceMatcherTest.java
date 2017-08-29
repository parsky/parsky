package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.TextNode;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class FlattenSequenceMatcherTest {
    private FlattenSequenceMatcher underTest = new FlattenSequenceMatcher(new OneOrMoreSequenceMatcher(new StringSequenceMatcher("test")));

    @Test
    public void match() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("testtest"));

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getNode(), instanceOf(TextNode.class));
        assertThat(((TextNode) result.getMatchResult().getNode()).getText(), is("testtest"));
    }

    @Test
    public void mismatch() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("te"));

        assertThat(result.matched(), is(false));
    }
}
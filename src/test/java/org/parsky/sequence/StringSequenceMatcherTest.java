package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.TextNode;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class StringSequenceMatcherTest {
    private final String string = "test";
    private StringSequenceMatcher underTest = new StringSequenceMatcher(string);

    @Test
    public void match() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("test 1"));

        assertThat(result.matched(), is(true));
        assertThat(result.getJump(), is(4));
        assertThat(result.getMatchResult().getNode(), is(instanceOf(TextNode.class)));
    }

    @Test
    public void mismatch() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("tes 1"));

        assertThat(result.matched(), is(false));
        assertThat(result.getJump(), is(0));
    }
}
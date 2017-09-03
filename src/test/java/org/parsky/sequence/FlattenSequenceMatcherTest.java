package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;
import org.parsky.sequence.model.tree.ListNode;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class FlattenSequenceMatcherTest {
    @Test
    public void mergeLists() throws Exception {
        SequenceMatcherResult result = new FlattenSequenceMatcher(SequenceMatchers.sequence(
                new ZeroOrMoreSequenceMatcher(new StringSequenceMatcher("test")),
                new StringSequenceMatcher("a")
        )).matches(request("testtestaa"));

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getNode(), instanceOf(ListNode.class));
        assertThat(((ContentNode<String>) ((ListNode) result.getMatchResult().getNode()).getNodes().get(0)).getContent(), is("test"));
        assertThat(((ContentNode<String>) ((ListNode) result.getMatchResult().getNode()).getNodes().get(1)).getContent(), is("test"));
        assertThat(((ContentNode<String>) ((ListNode) result.getMatchResult().getNode()).getNodes().get(2)).getContent(), is("a"));
    }

    @Test
    public void mergeNoList() throws Exception {
        SequenceMatcherResult result = new FlattenSequenceMatcher(new StringSequenceMatcher("a")).matches(request("a"));

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getNode(), instanceOf(ContentNode.class));
    }

    @Test
    public void mergeNoMatch() throws Exception {
        SequenceMatcherResult result = new FlattenSequenceMatcher(SequenceMatchers.sequence(
                new ZeroOrMoreSequenceMatcher(new StringSequenceMatcher("test")),
                new StringSequenceMatcher("a")
        )).matches(request("b"));

        assertThat(result.matched(), is(false));
    }
}
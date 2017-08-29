package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ListNode;
import org.parsky.sequence.model.tree.TextNode;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class MergeSequenceMatcherTest {
    @Test
    public void mergeLists() throws Exception {
        SequenceMatcherResult result = new MergeSequenceMatcher(asList(
                new ZeroOrMoreSequenceMatcher(new StringSequenceMatcher("test")),
                new StringSequenceMatcher("a")
        )).matches(request("testtestaa"));

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getNode(), instanceOf(ListNode.class));
        assertThat(((TextNode) ((ListNode) result.getMatchResult().getNode()).getNodes().get(0)).getText(), is("test"));
        assertThat(((TextNode) ((ListNode) result.getMatchResult().getNode()).getNodes().get(1)).getText(), is("test"));
        assertThat(((TextNode) ((ListNode) result.getMatchResult().getNode()).getNodes().get(2)).getText(), is("a"));
    }
}
package org.parsky.sequence;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;
import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ListNode;
import org.parsky.sequence.model.tree.Node;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ConsecutiveSequenceMatcherTest {
    private SequenceMatcher sequenceMatcher1 = mock(SequenceMatcher.class);
    private SequenceMatcher sequenceMatcher2 = mock(SequenceMatcher.class);
    private List<SequenceMatcher> sequenceMatchers = asList(sequenceMatcher1, sequenceMatcher2);

    private ConsecutiveSequenceMatcher underTest = new ConsecutiveSequenceMatcher(sequenceMatchers);

    @Test
    public void matchesBoth() throws Exception {
        SequenceMatcherRequest request = mock(SequenceMatcherRequest.class);
        SequenceMatcherRequest request1 = mock(SequenceMatcherRequest.class);
        SequenceMatcherRequest request2 = mock(SequenceMatcherRequest.class);
        SequenceMatcherResult result1 = mock(SequenceMatcherResult.class);
        SequenceMatcherResult result2 = mock(SequenceMatcherResult.class);
        MatchResult matchResult1 = mock(MatchResult.class);
        MatchResult matchResult2 = mock(MatchResult.class);
        Node node1 = mock(Node.class);
        Node node2 = mock(Node.class);

        given(sequenceMatcher1.matches(request1)).willReturn(result1);
        given(request.incrementOffset(0)).willReturn(request1);
        given(request.incrementOffset(1)).willReturn(request2);
        given(sequenceMatcher2.matches(request2)).willReturn(result2);
        given(result1.getJump()).willReturn(1);
        given(result1.isError()).willReturn(false);
        given(result1.matched()).willReturn(true);
        given(result1.getMatchResult()).willReturn(matchResult1);
        given(matchResult1.getNode()).willReturn(node1);
        given(result2.getJump()).willReturn(1);
        given(result2.isError()).willReturn(false);
        given(result2.matched()).willReturn(true);
        given(result2.getMatchResult()).willReturn(matchResult2);
        given(matchResult2.getNode()).willReturn(node2);

        SequenceMatcherResult result = underTest.matches(request);

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getNode(), is(instanceOf(ListNode.class)));
        assertThat(((ListNode) result.getMatchResult().getNode()).getNodes(), IsIterableContainingInOrder.contains(node1, node2));
    }

    @Test
    public void matchesFailsSecond() throws Exception {
        SequenceMatcherRequest request = mock(SequenceMatcherRequest.class);
        SequenceMatcherRequest request1 = mock(SequenceMatcherRequest.class);
        SequenceMatcherRequest request2 = mock(SequenceMatcherRequest.class);
        SequenceMatcherResult result1 = mock(SequenceMatcherResult.class);
        SequenceMatcherResult result2 = mock(SequenceMatcherResult.class);
        MatchResult matchResult1 = mock(MatchResult.class);
        MatchResult matchResult2 = mock(MatchResult.class);
        Node node1 = mock(Node.class);
        Node node2 = mock(Node.class);

        given(sequenceMatcher1.matches(request1)).willReturn(result1);
        given(request.incrementOffset(0)).willReturn(request1);
        given(request.incrementOffset(1)).willReturn(request2);
        given(sequenceMatcher2.matches(request2)).willReturn(result2);
        given(result1.getJump()).willReturn(1);
        given(result1.isError()).willReturn(false);
        given(result1.matched()).willReturn(true);
        given(result1.getMatchResult()).willReturn(matchResult1);
        given(matchResult1.getNode()).willReturn(node1);
        given(result2.getJump()).willReturn(1);
        given(result2.isError()).willReturn(false);
        given(result2.matched()).willReturn(false);
        given(result2.getMatchResult()).willReturn(matchResult2);
        given(matchResult2.getNode()).willReturn(node2);

        SequenceMatcherResult result = underTest.matches(request);

        assertThat(result.matched(), is(false));
        assertThat(result.isError(), is(false));
    }

    @Test
    public void matchesError() throws Exception {
        SequenceMatcherRequest request = mock(SequenceMatcherRequest.class);
        SequenceMatcherRequest request1 = mock(SequenceMatcherRequest.class);
        SequenceMatcherRequest request2 = mock(SequenceMatcherRequest.class);
        SequenceMatcherResult result1 = mock(SequenceMatcherResult.class);
        SequenceMatcherResult result2 = mock(SequenceMatcherResult.class);
        MatchResult matchResult1 = mock(MatchResult.class);
        Node node1 = mock(Node.class);

        given(sequenceMatcher1.matches(request1)).willReturn(result1);
        given(request.incrementOffset(0)).willReturn(request1);
        given(request.incrementOffset(1)).willReturn(request2);
        given(sequenceMatcher2.matches(request2)).willReturn(result2);
        given(result1.getJump()).willReturn(1);
        given(result1.isError()).willReturn(true);
        given(result1.matched()).willReturn(false);
        given(result1.getMatchResult()).willReturn(matchResult1);
        given(matchResult1.getNode()).willReturn(node1);

        SequenceMatcherResult result = underTest.matches(request);

        assertThat(result, is(result1));
    }
}
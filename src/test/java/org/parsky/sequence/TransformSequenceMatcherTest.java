package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;
import org.parsky.sequence.transform.Transformation;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class TransformSequenceMatcherTest {
    private final SequenceMatcher sequenceMatcher = mock(SequenceMatcher.class);
    private final Transformation transformation = mock(Transformation.class);
    private TransformSequenceMatcher<String> underTest = new TransformSequenceMatcher<>(sequenceMatcher, transformation);

    @Test
    public void matches() throws Exception {
        SequenceMatcherRequest request = SequentTestUtils.request("test");
        MatchResult matchResult = request.text(1);

        ContentNode contentNode = mock(ContentNode.class);

        given(sequenceMatcher.matches(request)).willReturn(SequenceMatcherResult.match(1, matchResult));
        given(transformation.transform(matchResult)).willReturn(contentNode);

        SequenceMatcherResult result = underTest.matches(request);

        assertThat(result.matched(), is(true));
        assertEquals(result.getMatchResult().getNode(), contentNode);
    }

    @Test
    public void mismatch() throws Exception {
        SequenceMatcherRequest request = SequentTestUtils.request("test");

        given(sequenceMatcher.matches(request)).willReturn(SequenceMatcherResult.mismatch());

        SequenceMatcherResult result = underTest.matches(request);

        assertThat(result.isMismatch(), is(true));
        verifyZeroInteractions(transformation);
    }
}
package org.parsky.sequence;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class LabelledSequenceMatcherTest {
    private final SequenceMatcher sequenceMatcher = mock(SequenceMatcher.class);
    private final String label = "label";
    private final LabelledSequenceMatcher underTest = new LabelledSequenceMatcher(label, sequenceMatcher);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void match() throws Exception {
        SequenceMatcherRequest sequenceMatcherRequest = mock(SequenceMatcherRequest.class);
        SequenceMatcherResult sequenceMatcherResult = mock(SequenceMatcherResult.class);

        given(sequenceMatcher.matches(sequenceMatcherRequest)).willReturn(sequenceMatcherResult);

        SequenceMatcherResult result = underTest.matches(sequenceMatcherRequest);

        assertSame(result, sequenceMatcherResult);
    }

    @Test
    public void matchException() throws Exception {
        SequenceMatcherRequest sequenceMatcherRequest = mock(SequenceMatcherRequest.class);

        given(sequenceMatcher.matches(sequenceMatcherRequest)).willThrow(new RuntimeException("Exception"));

        expectedException.expectMessage(label);

        underTest.matches(sequenceMatcherRequest);
    }
}
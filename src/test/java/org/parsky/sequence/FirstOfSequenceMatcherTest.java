package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class FirstOfSequenceMatcherTest {
    private final SequenceMatcher sequenceMatcher1 = mock(SequenceMatcher.class);
    private final SequenceMatcher sequenceMatcher2 = mock(SequenceMatcher.class);
    private FirstOfSequenceMatcher underTest = new FirstOfSequenceMatcher(asList(sequenceMatcher1, sequenceMatcher2));

    @Test
    public void matchesFirst() throws Exception {
        SequenceMatcherRequest sequenceMatcherRequest = mock(SequenceMatcherRequest.class);
        SequenceMatcherResult sequenceMatcherResult = mock(SequenceMatcherResult.class);

        given(sequenceMatcher1.matches(sequenceMatcherRequest)).willReturn(sequenceMatcherResult);
        given(sequenceMatcherResult.isMismatch()).willReturn(false);

        SequenceMatcherResult result = underTest.matches(sequenceMatcherRequest);

        assertThat(result, is(sequenceMatcherResult));
        verifyZeroInteractions(sequenceMatcher2);
    }

    @Test
    public void matchesSecond() throws Exception {
        SequenceMatcherRequest sequenceMatcherRequest = mock(SequenceMatcherRequest.class);
        SequenceMatcherResult sequenceMatcherResult = mock(SequenceMatcherResult.class);
        SequenceMatcherResult sequenceMatcherResult2 = mock(SequenceMatcherResult.class);

        given(sequenceMatcher1.matches(sequenceMatcherRequest)).willReturn(sequenceMatcherResult);
        given(sequenceMatcher2.matches(sequenceMatcherRequest)).willReturn(sequenceMatcherResult2);
        given(sequenceMatcherResult.isMismatch()).willReturn(true);
        given(sequenceMatcherResult2.isMismatch()).willReturn(false);

        SequenceMatcherResult result = underTest.matches(sequenceMatcherRequest);

        assertThat(result, is(sequenceMatcherResult2));
    }

    @Test
    public void noMatch() throws Exception {
        SequenceMatcherRequest sequenceMatcherRequest = mock(SequenceMatcherRequest.class);
        SequenceMatcherResult sequenceMatcherResult = mock(SequenceMatcherResult.class);
        SequenceMatcherResult sequenceMatcherResult2 = mock(SequenceMatcherResult.class);

        given(sequenceMatcher1.matches(sequenceMatcherRequest)).willReturn(sequenceMatcherResult);
        given(sequenceMatcher2.matches(sequenceMatcherRequest)).willReturn(sequenceMatcherResult2);
        given(sequenceMatcherResult.isMismatch()).willReturn(true);
        given(sequenceMatcherResult2.isMismatch()).willReturn(true);

        SequenceMatcherResult result = underTest.matches(sequenceMatcherRequest);

        assertThat(result.matched(), is(false));
    }
}
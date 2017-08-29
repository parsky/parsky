package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OptionalSequenceMatcherTest {
    private final SequenceMatcher sequenceMatcher = mock(SequenceMatcher.class);
    private OptionalSequenceMatcher underTest = new OptionalSequenceMatcher(sequenceMatcher);

    @Test
    public void matchWhenNoMatch() throws Exception {
        MatchResult matchResult = mock(MatchResult.class);
        SequenceMatcherRequest request = mock(SequenceMatcherRequest.class);
        SequenceMatcherResult sequenceMatcherResult = mock(SequenceMatcherResult.class);

        given(sequenceMatcher.matches(request)).willReturn(sequenceMatcherResult);
        given(sequenceMatcherResult.isMismatch()).willReturn(true);
        given(request.empty()).willReturn(matchResult);

        SequenceMatcherResult result = underTest.matches(request);

        assertThat(result.matched(), is(true));
        assertThat(result.getJump(), is(0));
        assertNotSame(result, sequenceMatcherResult);
    }

    @Test
    public void matchWhenMatch() throws Exception {
        SequenceMatcherRequest request = mock(SequenceMatcherRequest.class);
        SequenceMatcherResult sequenceMatcherResult = mock(SequenceMatcherResult.class);

        given(sequenceMatcher.matches(request)).willReturn(sequenceMatcherResult);
        given(sequenceMatcherResult.isMismatch()).willReturn(false);

        SequenceMatcherResult result = underTest.matches(request);

        assertSame(result, sequenceMatcherResult);
    }
}
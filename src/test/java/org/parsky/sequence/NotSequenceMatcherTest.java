package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.parsky.sequence.SequentTestUtils.request;

public class NotSequenceMatcherTest {
    private NotSequenceMatcher underTest = new NotSequenceMatcher(new StringSequenceMatcher("test"));

    @Test
    public void matches() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("term"));

        assertThat(result.matched(), is(true));
        assertThat(result.getJump(), is(1));
    }

    @Test
    public void mismatches() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("test"));

        assertThat(result.matched(), is(false));
        assertThat(result.getJump(), is(0));
    }

    @Test
    public void error() throws Exception {
        SequenceMatcherRequest request = request("test");
        SequenceMatcherResult sequenceMatcherResult = request.error("Message");

        SequenceMatcher sequenceMatcher = mock(SequenceMatcher.class);

        given(sequenceMatcher.matches(request)).willReturn(sequenceMatcherResult);

        SequenceMatcherResult result = new NotSequenceMatcher(sequenceMatcher)
                .matches(request);

        assertThat(result.isError(), is(true));
    }
}
package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.parsky.sequence.SequentTestUtils.request;

public class ZeroOrMoreSequenceMatcherTest {
    private ZeroOrMoreSequenceMatcher underTest = new ZeroOrMoreSequenceMatcher(new StringSequenceMatcher("test"));

    @Test
    public void zeroOrMoreEmptyMatch() throws Exception {
        SequenceMatcherResult result = new ZeroOrMoreSequenceMatcher(SequenceMatchers.optional(SequenceMatchers.string("test")))
                .matches(request("te"));

        assertThat(result.matched(), is(true));
    }
    @Test
    public void zeroOrMoreError() throws Exception {
        SequenceMatcherRequest request = request("te");
        SequenceMatcher sequenceMatcher = mock(SequenceMatcher.class);

        given(sequenceMatcher.matches(request)).willReturn(request.error("error"));

        SequenceMatcherResult result = new ZeroOrMoreSequenceMatcher(sequenceMatcher)
                .matches(request);

        assertThat(result.isError(), is(true));
    }

    @Test
    public void zeroMatch() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("tes"));

        assertThat(result.matched(), is(true));
        assertThat(result.getJump(), is(0));
    }

    @Test
    public void oneMatch() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("testb"));

        assertThat(result.matched(), is(true));
        assertThat(result.getJump(), is(4));
    }

    @Test
    public void twoMatch() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("testtest"));

        assertThat(result.matched(), is(true));
        assertThat(result.getJump(), is(8));
    }
}
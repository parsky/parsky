package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherResult;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class ZeroOrMoreSequenceMatcherTest {
    private ZeroOrMoreSequenceMatcher underTest = new ZeroOrMoreSequenceMatcher(new StringSequenceMatcher("test"));

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
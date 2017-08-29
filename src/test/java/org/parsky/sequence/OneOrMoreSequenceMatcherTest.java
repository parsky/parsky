package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherResult;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class OneOrMoreSequenceMatcherTest {
    private OneOrMoreSequenceMatcher underTest = new OneOrMoreSequenceMatcher(new StringSequenceMatcher("test"));

    @Test
    public void mismatch() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("tes"));

        assertThat(result.matched(), is(false));
    }

    @Test
    public void match() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("test"));

        assertThat(result.matched(), is(true));
        assertThat(result.getJump(), is(4));
    }

    @Test
    public void matchTwo() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("testtesta"));

        assertThat(result.matched(), is(true));
        assertThat(result.getJump(), is(8));
    }
}
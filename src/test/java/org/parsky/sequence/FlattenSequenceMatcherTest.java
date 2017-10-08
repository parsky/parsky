package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class FlattenSequenceMatcherTest {
    @Test
    public void mergeLists() throws Exception {
        SequenceMatcherResult result = new FlattenSequenceMatcher(SequenceMatchers.sequence(
                new ZeroOrMoreSequenceMatcher(new StringSequenceMatcher("test")),
                new StringSequenceMatcher("a")
        )).matches(request("testtestaa"));

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getValue(), instanceOf(List.class));
    }

    @Test
    public void mergeNoList() throws Exception {
        SequenceMatcherResult result = new FlattenSequenceMatcher(new StringSequenceMatcher("a")).matches(request("a"));

        assertThat(result.matched(), is(true));
    }

    @Test
    public void mergeNoMatch() throws Exception {
        SequenceMatcherResult result = new FlattenSequenceMatcher(SequenceMatchers.sequence(
                new ZeroOrMoreSequenceMatcher(new StringSequenceMatcher("test")),
                new StringSequenceMatcher("a")
        )).matches(request("b"));

        assertThat(result.matched(), is(false));
    }
}
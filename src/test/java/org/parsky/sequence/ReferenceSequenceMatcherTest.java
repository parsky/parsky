package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherResult;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class ReferenceSequenceMatcherTest {
    private ReferenceSequenceMatcher<StringSequenceMatcher> referenceSequenceMatcher = new ReferenceSequenceMatcher<>();

    @Test
    public void match() throws Exception {
        referenceSequenceMatcher.assign(new StringSequenceMatcher("test"));

        SequenceMatcherResult result = referenceSequenceMatcher.matches(request("test"));

        assertThat(result.matched(), is(true));
        assertThat(result.getJump(), is(4));
    }
}
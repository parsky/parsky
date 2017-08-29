package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class ReferenceSequenceMatcherTest {
    private final AtomicReference<StringSequenceMatcher> reference = new AtomicReference<>();
    private ReferenceSequenceMatcher<StringSequenceMatcher> referenceSequenceMatcher = new ReferenceSequenceMatcher<StringSequenceMatcher>(reference);

    @Test
    public void match() throws Exception {
        reference.set(new StringSequenceMatcher("test"));

        SequenceMatcherResult result = referenceSequenceMatcher.matches(request("test"));

        assertThat(result.matched(), is(true));
        assertThat(result.getJump(), is(4));
    }
}
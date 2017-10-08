package org.parsky.sequence.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SequenceMatcherRequestTest {
    @Test
    public void incrementOffset() throws Exception {

        SequenceMatcherRequest result = new SequenceMatcherRequest<>("test".toCharArray(), 1, new Object())
                .incrementOffset(2);

        assertThat(result.getOffset(), is(3));
    }

    @Test
    public void isEndOfInput() throws Exception {
        SequenceMatcherRequest request = new SequenceMatcherRequest<>("test".toCharArray(), 10, new Object());
        assertThat(request.isEndOfInput(), is(true));
    }

    @Test
    public void notEndOfInput() throws Exception {
        SequenceMatcherRequest request = new SequenceMatcherRequest<>("test".toCharArray(), 1, new Object());
        assertThat(request.isEndOfInput(), is(false));
    }

    @Test
    public void text() throws Exception {
        SequenceMatcherRequest request = new SequenceMatcherRequest<>("test".toCharArray(), 1, new Object());

        SequenceMatcherResult result = request.text(3);

        assertEquals("est", result.getMatchResult().getValue());
    }
}
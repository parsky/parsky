package org.parsky.sequence.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class MatchResultTest {
    private Range range = mock(Range.class);

    private final Object object = new Object();
    private MatchResult underTest = new MatchResult(range, object);

    @Test
    public void name() throws Exception {
        Object newNode = new Object();

        MatchResult result = underTest.with(newNode);

        assertThat(result.getValue(), is(newNode));
        assertThat(result.getRange(), is(range));
    }

    @Test
    public void content() throws Exception {
        String content = "content";

        MatchResult underTest = new MatchResult(range, content);
        Object result = underTest.getValue();

        assertEquals(result, content);
    }
}
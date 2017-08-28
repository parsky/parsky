package org.parsky.sequence.model;

import org.junit.Test;
import org.parsky.sequence.model.tree.TextNode;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class SequenceMatcherRequestTest {
    @Test
    public void incrementOffset() throws Exception {

        SequenceMatcherRequest result = new SequenceMatcherRequest("test".toCharArray(), 1)
                .incrementOffset(2);

        assertThat(result.getOffset(), is(3));
    }

    @Test
    public void isEndOfInput() throws Exception {
        SequenceMatcherRequest request = new SequenceMatcherRequest("test".toCharArray(), 10);
        assertThat(request.isEndOfInput(), is(true));
    }

    @Test
    public void notEndOfInput() throws Exception {
        SequenceMatcherRequest request = new SequenceMatcherRequest("test".toCharArray(), 1);
        assertThat(request.isEndOfInput(), is(false));
    }

    @Test
    public void text() throws Exception {
        SequenceMatcherRequest request = new SequenceMatcherRequest("test".toCharArray(), 1);

        MatchResult result = request.text(3);

        assertThat(result.getNode(), is(instanceOf(TextNode.class)));
        assertThat(((TextNode) result.getNode()).getText(), is("est"));
    }
}
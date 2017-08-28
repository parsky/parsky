package org.parsky.sequence.model;

import org.junit.Test;
import org.parsky.sequence.model.tree.ContentNode;
import org.parsky.sequence.model.tree.Node;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class MatchResultTest {
    private Range range = mock(Range.class);
    private Node node = mock(Node.class);

    private MatchResult underTest = new MatchResult(range, node);

    @Test
    public void name() throws Exception {
        Node newNode = mock(Node.class);

        MatchResult result = underTest.with(newNode);

        assertThat(result.getNode(), is(newNode));
        assertThat(result.getRange(), is(range));
    }

    @Test
    public void content() throws Exception {
        String content = "content";
        ContentNode<String> contentNode = new ContentNode<>(content);

        MatchResult underTest = new MatchResult(range, contentNode);

        String result = underTest.content(String.class);

        assertThat(result, is(content));
    }
}
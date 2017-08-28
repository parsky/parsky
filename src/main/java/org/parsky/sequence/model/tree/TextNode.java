package org.parsky.sequence.model.tree;

public class TextNode implements Node {
    private final char[] content;
    private final int start;
    private final int end;

    public TextNode(char[] content, int start, int end) {
        this.content = content;
        this.start = start;
        this.end = end;
    }

    public String getText () {
        return new String(content, start, end - start);
    }
}

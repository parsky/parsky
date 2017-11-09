package org.parsky.sequence.model;

public class Range {
    public static Range range (char[] content, int start, int offset) {
        return new Range(content, start, start + offset);
    }

    private final char[] content;
    private final int startOffset;
    private final int endOffset;

    public Range(char[] content, int startOffset, int endOffset) {
        this.content = content;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    public char[] getContent() {
        return content;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }
}

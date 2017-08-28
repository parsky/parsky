package org.parsky.sequence.model;

public class Range {
    public static Range range (int start, int offset) {
        return new Range(start, start + offset);
    }

    private final int startOffset;
    private final int endOffset;

    public Range(int startOffset, int endOffset) {
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }
}

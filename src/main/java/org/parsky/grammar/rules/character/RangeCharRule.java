package org.parsky.grammar.rules.character;

public class RangeCharRule implements CharRule {
    private final char start;
    private final char end;

    public RangeCharRule(char start, char end) {
        this.start = start;
        this.end = end;
    }

    public char getStart() {
        return start;
    }

    public char getEnd() {
        return end;
    }
}

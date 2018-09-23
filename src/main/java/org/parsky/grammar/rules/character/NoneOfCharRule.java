package org.parsky.grammar.rules.character;

public class NoneOfCharRule implements CharRule {
    private final char[] nonPossibilities;

    public NoneOfCharRule(char[] nonPossibilities) {
        this.nonPossibilities = nonPossibilities;
    }

    public char[] getNonPossibilities() {
        return nonPossibilities;
    }
}

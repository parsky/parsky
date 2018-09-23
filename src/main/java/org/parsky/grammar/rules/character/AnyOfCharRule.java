package org.parsky.grammar.rules.character;

public class AnyOfCharRule implements CharRule {
    private final char[] possibilities;

    public AnyOfCharRule(char[] possibilities) {
        this.possibilities = possibilities;
    }

    public char[] getPossibilities() {
        return possibilities;
    }
}

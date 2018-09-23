package org.parsky.grammar.rules.character;

public class SingleCharRule implements CharRule {
    private final char character;

    public SingleCharRule(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}

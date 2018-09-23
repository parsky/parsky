package org.parsky.grammar.rules;

public class StringRule implements Rule {
    private final String text;

    public StringRule(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

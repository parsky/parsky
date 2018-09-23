package org.parsky.grammar.rules;

public class FailRule implements Rule {
    private final String message;

    public FailRule(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

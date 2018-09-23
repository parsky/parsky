package org.parsky.grammar.rules;

public class CaptureTextRule implements Rule {
    private final Rule rule;

    public CaptureTextRule(Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
        return rule;
    }
}

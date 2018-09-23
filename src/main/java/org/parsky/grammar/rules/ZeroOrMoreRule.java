package org.parsky.grammar.rules;

public class ZeroOrMoreRule implements Rule {
    private final Rule rule;

    public ZeroOrMoreRule(Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
        return rule;
    }
}

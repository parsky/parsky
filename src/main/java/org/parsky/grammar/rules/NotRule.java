package org.parsky.grammar.rules;

public class NotRule implements Rule {
    private final Rule rule;

    public NotRule(Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
        return rule;
    }
}

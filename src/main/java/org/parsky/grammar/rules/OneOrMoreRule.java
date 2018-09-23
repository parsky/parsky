package org.parsky.grammar.rules;

public class OneOrMoreRule implements Rule {
    private final Rule rule;

    public OneOrMoreRule(Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
        return rule;
    }
}

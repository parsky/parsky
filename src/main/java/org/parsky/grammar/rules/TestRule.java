package org.parsky.grammar.rules;

public class TestRule implements Rule {
    private final Rule rule;

    public TestRule(Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
        return rule;
    }
}

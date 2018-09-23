package org.parsky.grammar.prefix;

import org.parsky.grammar.RuleFactory;

public class PrefixRuleConfiguration {
    private final int precedence;
    private final RuleFactory rule;

    public PrefixRuleConfiguration(int precedence, RuleFactory rule) {
        this.precedence = precedence;
        this.rule = rule;
    }

    public int getPrecedence() {
        return precedence;
    }

    public RuleFactory getRule() {
        return rule;
    }
}

package org.parsky.grammar.infix;

import org.parsky.grammar.RuleFactory;

public class InfixRuleConfiguration {
    public static InfixRuleConfiguration infixRule (int precedence, RuleFactory rule) {
        return new InfixRuleConfiguration(precedence, rule);
    }

    private final int precedence;
    private final RuleFactory rule;

    protected InfixRuleConfiguration(int precedence, RuleFactory rule) {
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

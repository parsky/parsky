package org.parsky.engine.simple.strategy.character;

import org.parsky.grammar.rules.character.AnyOfCharRule;

public class AnyOfCharRuleEngine implements CharRuleEngine<AnyOfCharRule> {
    @Override
    public boolean matches(AnyOfCharRule rule, char input) {
        for (char c : rule.getPossibilities()) {
            if (input == c) return true;
        }

        return false;
    }
}

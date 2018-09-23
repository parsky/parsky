package org.parsky.engine.simple.strategy.character;

import org.parsky.grammar.rules.character.NoneOfCharRule;

public class NoneOfCharRuleEngine implements CharRuleEngine<NoneOfCharRule> {
    @Override
    public boolean matches(NoneOfCharRule rule, char input) {
        for (char c : rule.getNonPossibilities()) {
            if (input == c) return false;
        }
        return true;
    }
}

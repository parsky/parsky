package org.parsky.engine.simple.strategy.character;

import org.parsky.grammar.rules.character.SingleCharRule;

public class SingleCharRuleEngine implements CharRuleEngine<SingleCharRule> {
    @Override
    public boolean matches(SingleCharRule rule, char input) {
        return input == rule.getCharacter();
    }
}

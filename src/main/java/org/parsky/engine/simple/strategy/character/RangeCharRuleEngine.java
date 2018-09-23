package org.parsky.engine.simple.strategy.character;

import org.parsky.grammar.rules.character.RangeCharRule;

public class RangeCharRuleEngine implements CharRuleEngine<RangeCharRule> {
    @Override
    public boolean matches(RangeCharRule rule, char input) {
        return input >= rule.getStart() && input <= rule.getEnd();
    }
}

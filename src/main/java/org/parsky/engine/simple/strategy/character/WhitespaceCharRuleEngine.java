package org.parsky.engine.simple.strategy.character;

import org.parsky.grammar.rules.character.WhitespaceCharRule;

public class WhitespaceCharRuleEngine implements CharRuleEngine<WhitespaceCharRule> {
    @Override
    public boolean matches(WhitespaceCharRule rule, char input) {
        return Character.isWhitespace(input);
    }
}

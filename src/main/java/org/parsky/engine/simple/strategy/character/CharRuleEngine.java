package org.parsky.engine.simple.strategy.character;

import org.parsky.grammar.rules.character.CharRule;

public interface CharRuleEngine<T extends CharRule> {
    boolean matches (T rule, char input);
}

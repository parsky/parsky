package org.parsky.grammar.rules;


import org.parsky.grammar.rules.character.CharRule;

public class CharacterRule implements Rule {
    private final CharRule rule;

    public CharacterRule(CharRule rule) {
        this.rule = rule;
    }

    public CharRule getRule() {
        return rule;
    }
}

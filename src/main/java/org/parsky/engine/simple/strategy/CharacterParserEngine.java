package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.engine.simple.strategy.character.CharRuleEngine;
import org.parsky.engine.value.EmptyValueResult;
import org.parsky.grammar.rules.CharacterRule;

import java.util.Map;

public class CharacterParserEngine implements ParserEngine<CharacterRule> {
    private final Map<Class, CharRuleEngine> charRule;

    public CharacterParserEngine(Map<Class, CharRuleEngine> charRule) {
        this.charRule = charRule;
    }

    @Override
    public ParserResult apply(CharacterRule rule, ParserRequest request) {
        if (!charRule.containsKey(rule.getRule().getClass())) {
            return request.error(String.format("No CharRuleEngine defined for %s", rule.getRule().getClass()));
        }

        if (request.hasCurrent() && charRule.get(rule.getRule().getClass()).matches(rule.getRule(), request.currentChar())) {
            return request.next(EmptyValueResult.empty());
        }

        return request.mismatch();
    }
}

package org.parsky.engine.print.strategy;

import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.engine.print.strategy.character.CharPrintStrategy;
import org.parsky.grammar.rules.CharacterRule;

import java.util.Map;

public class CharacterPrintStrategy implements PrintStrategy<CharacterRule> {
    private final Map<Class, CharPrintStrategy> strategyMap;

    public CharacterPrintStrategy(Map<Class, CharPrintStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    @Override
    public PrintNode print(CharacterRule rule, PrintQueue queue) {
        if (!strategyMap.containsKey(rule.getRule().getClass())) {
            throw new IllegalArgumentException(String.format("Cannot find print strategy for %s", rule.getRule().getClass()));
        }
        return strategyMap.get(rule.getRule().getClass()).print(rule.getRule());
    }
}

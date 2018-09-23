package org.parsky.engine.print.strategy;

import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.Rule;

import java.util.Map;

public class AnyPrintStrategy implements PrintStrategy {
    private final Map<Class, PrintStrategy> strategyMap;

    public AnyPrintStrategy(Map<Class, PrintStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    @Override
    public PrintNode print(Rule rule, final PrintQueue queue) {
        if (!strategyMap.containsKey(rule.getClass())) {
            throw new IllegalArgumentException(String.format("Cannot find print strategy for %s", rule.getClass()));
        }

        return strategyMap.get(rule.getClass()).print(rule, queue);
    }
}

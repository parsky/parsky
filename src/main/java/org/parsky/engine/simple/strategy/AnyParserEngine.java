package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserEngine;
import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.grammar.rules.Rule;

import java.util.Map;

public class AnyParserEngine implements ParserEngine<Rule> {
    private final Map<Class, ParserEngine> strategyMap;

    public AnyParserEngine(Map<Class, ParserEngine> strategyMap) {
        this.strategyMap = strategyMap;
    }

    @Override
    public ParserResult apply(Rule rule, ParserRequest request) {
        if (!strategyMap.containsKey(rule.getClass())) {
            throw new IllegalArgumentException(String.format(
                    "No parser strategy found for %s",
                    rule.getClass()
            ));
        }

        ParserEngine strategy = strategyMap.get(rule.getClass());

        return strategy.apply(rule, request);
    }
}

package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.engine.value.ListValueResult;
import org.parsky.grammar.rules.ZeroOrMoreRule;

public class ZeroOrMoreParserEngine implements ParserEngine<ZeroOrMoreRule> {
    private final ParserEngine parserStrategy;

    public ZeroOrMoreParserEngine(ParserEngine parserStrategy) {
        this.parserStrategy = parserStrategy;
    }

    @Override
    public ParserResult apply(ZeroOrMoreRule rule, ParserRequest request) {
        ParserResult previousResult = request.ok(ListValueResult.list());
        ParserResult currentResult = previousResult.plus(parserStrategy.apply(rule.getRule(), request.plus(previousResult)));

        while (currentResult.success() && currentResult.hasProgress()) {
            previousResult = currentResult;
            currentResult = currentResult.plus(parserStrategy.apply(rule.getRule(), request.plus(previousResult)));
        }

        if (currentResult.getError().isPresent()) return currentResult;
        return previousResult;
    }
}

package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserEngine;
import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.value.ListValueResult;
import org.parsky.grammar.rules.OneOrMoreRule;

public class OneOrMoreParserEngine implements ParserEngine<OneOrMoreRule> {
    private final ParserEngine parserEngine;

    public OneOrMoreParserEngine(ParserEngine parserEngine) {
        this.parserEngine = parserEngine;
    }

    @Override
    public ParserResult apply(OneOrMoreRule rule, ParserRequest request) {
        ParserRequest currentRequest = request;
        ParserResult previousResult = request.ok(ListValueResult.list());
        ParserResult currentResult = parserEngine.apply(rule.getRule(), currentRequest);

        if (!currentResult.success()) return currentResult;
        else {
            while (currentResult.success() && currentResult.hasProgress()) {
                previousResult = previousResult.plus(currentResult);
                currentRequest = currentRequest.plus(currentResult);
                currentResult = parserEngine.apply(rule.getRule(), currentRequest);
            }
            return previousResult;
        }
    }
}

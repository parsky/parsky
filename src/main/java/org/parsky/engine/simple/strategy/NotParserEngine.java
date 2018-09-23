package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.engine.value.EmptyValueResult;
import org.parsky.grammar.rules.NotRule;

public class NotParserEngine implements ParserEngine<NotRule> {
    private final ParserEngine parserEngine;

    public NotParserEngine(ParserEngine parserEngine) {
        this.parserEngine = parserEngine;
    }

    @Override
    public ParserResult apply(NotRule rule, ParserRequest request) {
        ParserResult result = parserEngine.apply(rule.getRule(), request);

        if (result.getError().isPresent()) return result;
        if (result.success()) return request.mismatch();
        else return request.next(EmptyValueResult.empty());
    }
}

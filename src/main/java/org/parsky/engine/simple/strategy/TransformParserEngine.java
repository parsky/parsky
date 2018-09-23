package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.grammar.rules.TransformRule;

public class TransformParserEngine implements ParserEngine<TransformRule> {
    private final ParserEngine parserEngine;

    public TransformParserEngine(ParserEngine parserEngine) {
        this.parserEngine = parserEngine;
    }

    @Override
    public ParserResult apply(TransformRule rule, ParserRequest request) {
        ParserResult result = parserEngine.apply(rule.getRule(), request);

        if (result.success()) {
            return result.transform(request.plus(result), rule.getTransform());
        }

        return result;
    }
}

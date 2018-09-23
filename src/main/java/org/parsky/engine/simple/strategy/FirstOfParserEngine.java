package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.grammar.rules.FirstOfRule;
import org.parsky.grammar.rules.Rule;

public class FirstOfParserEngine implements ParserEngine<FirstOfRule> {
    private final ParserEngine parserEngine;

    public FirstOfParserEngine(ParserEngine parserEngine) {
        this.parserEngine = parserEngine;
    }

    @Override
    public ParserResult apply(FirstOfRule rule, ParserRequest request) {
        for (Rule alternative : rule.getAlternatives()) {
            ParserResult result = parserEngine.apply(alternative, request);
            if (result.getError().isPresent() || result.success()) return result;
        }

        return request.mismatch();
    }
}

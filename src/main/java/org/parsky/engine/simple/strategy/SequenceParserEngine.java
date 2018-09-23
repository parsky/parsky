package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.engine.value.ListValueResult;
import org.parsky.grammar.rules.Rule;
import org.parsky.grammar.rules.SequenceRule;

public class SequenceParserEngine implements ParserEngine<SequenceRule> {
    private final ParserEngine parserEngine;

    public SequenceParserEngine(ParserEngine parserEngine) {
        this.parserEngine = parserEngine;
    }

    @Override
    public ParserResult apply(SequenceRule rule, ParserRequest request) {
        ParserRequest currentRequest = request;
        ParserResult result = request.ok(ListValueResult.list());

        for (Rule subRule : rule.getSequence()) {
            result = result.plus(parserEngine.apply(subRule, currentRequest.plus(result)));

            if (result.getError().isPresent()) return result;
            if (!result.success()) return request.mismatch();
        }

        return result;
    }
}

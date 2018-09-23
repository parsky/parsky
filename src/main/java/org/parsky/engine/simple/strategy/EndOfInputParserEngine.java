package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.engine.value.EmptyValueResult;
import org.parsky.grammar.rules.EndOfInputRule;

public class EndOfInputParserEngine implements ParserEngine<EndOfInputRule> {
    @Override
    public ParserResult apply(EndOfInputRule rule, ParserRequest request) {
        if (!request.hasCurrent()) {
            return request.ok(EmptyValueResult.empty());
        } else {
            return request.mismatch();
        }
    }
}

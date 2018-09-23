package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.engine.value.EmptyValueResult;
import org.parsky.grammar.rules.EmptyRule;

public class EmptyParserEngine implements ParserEngine<EmptyRule> {
    @Override
    public ParserResult apply(EmptyRule rule, ParserRequest request) {
        return request.ok(EmptyValueResult.empty());
    }
}

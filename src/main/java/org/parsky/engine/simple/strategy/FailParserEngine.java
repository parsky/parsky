package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.grammar.rules.FailRule;

public class FailParserEngine implements ParserEngine<FailRule> {
    @Override
    public ParserResult apply(FailRule rule, ParserRequest request) {
        return request.error(rule.getMessage());
    }
}

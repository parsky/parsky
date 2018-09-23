package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.engine.value.EmptyValueResult;
import org.parsky.grammar.rules.TestRule;

public class TestParserEngine implements ParserEngine<TestRule> {
    private final ParserEngine parserEngine;

    public TestParserEngine(ParserEngine parserEngine) {
        this.parserEngine = parserEngine;
    }

    @Override
    public ParserResult apply(TestRule rule, ParserRequest request) {
        ParserResult result = parserEngine.apply(rule.getRule(), request.test());

        if (result.getError().isPresent()) return result;
        if (result.success()) return request.ok(EmptyValueResult.empty());
        else return request.mismatch();
    }
}

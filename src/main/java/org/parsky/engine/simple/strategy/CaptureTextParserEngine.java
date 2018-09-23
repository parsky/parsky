package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserEngine;
import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.value.ConstantValueResult;
import org.parsky.grammar.rules.CaptureTextRule;

public class CaptureTextParserEngine implements ParserEngine<CaptureTextRule> {
    private final ParserEngine parserEngine;

    public CaptureTextParserEngine(ParserEngine parserEngine) {
        this.parserEngine = parserEngine;
    }

    @Override
    public ParserResult apply(CaptureTextRule rule, ParserRequest request) {
        ParserResult result = parserEngine.apply(rule.getRule(), request);
        if (result.success()) {
            return result.withValue(ConstantValueResult.constant(
                    new String(
                            request.getContent(),
                            request.getOffset(),
                            result.getSteps()
                    )
            ));
        } else {
            return result;
        }
    }
}

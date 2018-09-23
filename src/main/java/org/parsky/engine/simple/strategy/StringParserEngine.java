package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserEngine;
import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.value.ConstantValueResult;
import org.parsky.grammar.rules.StringRule;

public class StringParserEngine implements ParserEngine<StringRule> {
    @Override
    public ParserResult apply(StringRule rule, ParserRequest request) {
        ParserRequest currentRequest = request;

        for (char c : rule.getText().toCharArray()) {
            if (currentRequest.hasCurrent() && currentRequest.currentChar() == c) {
                currentRequest = currentRequest.step();
            } else {
                return request.mismatch();
            }
        }

        return currentRequest.ok(
                ConstantValueResult.constant(rule.getText()),
                rule.getText().length()
        );
    }
}

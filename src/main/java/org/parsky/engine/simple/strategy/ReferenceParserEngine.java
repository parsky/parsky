package org.parsky.engine.simple.strategy;

import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.grammar.rules.ReferenceRule;

public class ReferenceParserEngine implements ParserEngine<ReferenceRule> {
    private final ParserEngine parserEngine;

    public ReferenceParserEngine(ParserEngine parserEngine) {
        this.parserEngine = parserEngine;
    }

    public ParserResult apply(ReferenceRule rule, ParserRequest request) {
        if (!rule.getRule().isPresent()) {
            return request.error(String.format("No rule defined for reference %s", rule.getName()));
        }
        request.getContext().put(new TrackRule(rule.getName()));
        ParserResult result = parserEngine.apply(rule.getRule().get(), request);
        if (!result.getError().isPresent()) {
            request.getContext().pop(TrackRule.class);
        }
        return result;
    }

    public static class TrackRule {
        private final String rule;

        public TrackRule(String rule) {
            this.rule = rule;
        }

        public String getRule() {
            return rule;
        }
    }
}

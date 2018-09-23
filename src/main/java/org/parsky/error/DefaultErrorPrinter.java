package org.parsky.error;

import com.google.common.base.Optional;
import org.apache.commons.lang3.tuple.Pair;
import org.parsky.engine.print.PrintParserEngine;
import org.parsky.engine.simple.strategy.ReferenceParserEngine;
import org.parsky.grammar.rules.ReferenceRule;

public class DefaultErrorPrinter implements ErrorPrinter {
    public static DefaultErrorPrinter create () {
        return new DefaultErrorPrinter(new PositionService(), new ExcerptService());
    }

    private final PositionService positionService;
    private final ExcerptService excerptService;

    DefaultErrorPrinter(PositionService positionService, ExcerptService excerptService) {
        this.positionService = positionService;
        this.excerptService = excerptService;
    }

    @Override
    public String print(Request request) {
        Pair<Integer, Integer> position = positionService.position(request.getContent(), request.getOffset());
        Optional<ReferenceParserEngine.TrackRule> rule = request.getContext().peek(ReferenceParserEngine.TrackRule.class);
        return String.format(
                "(Line: %d, Column: %d) %s\n%s\nFailed Rule: %s\nGrammar:\n%s",
                position.getLeft(),
                position.getRight(),
                request.getMessage(),
                excerptService.excerpt(request.getContent(), request.getOffset()),
                rule.isPresent() ? rule.get().getRule() : "unknown",
                PrintParserEngine.print(request.getGrammar())
        );
    }
}

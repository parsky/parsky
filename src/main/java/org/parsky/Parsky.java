package org.parsky;

import com.google.common.base.Optional;
import org.parsky.context.Context;
import org.parsky.context.ContextFactory;
import org.parsky.context.DefaultContextFactory;
import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.ParserEngine;
import org.parsky.engine.simple.SimpleParserEngine;
import org.parsky.error.DefaultErrorPrinter;
import org.parsky.grammar.Grammar;
import org.parsky.grammar.rules.Rule;
import org.parsky.error.ErrorPrinter;

public class Parsky {
    public static Parsky simple (Grammar grammar) {
        return new Parsky(
                grammar,
                SimpleParserEngine.create(),
                new DefaultContextFactory(),
                DefaultErrorPrinter.create()
        );
    }

    private final Grammar grammar;
    private final ParserEngine engine;
    private final ContextFactory contextFactory;
    private final ErrorPrinter errorPrinter;

    public Parsky(Grammar grammar, ParserEngine engine, ContextFactory contextFactory, ErrorPrinter errorPrinter) {
        this.grammar = grammar;
        this.engine = engine;
        this.contextFactory = contextFactory;
        this.errorPrinter = errorPrinter;
    }

    public Object parse(Object contextItem, String content) throws ParskyException {
        Rule start = grammar.rule(grammar.startRule());

        char[] array = content.toCharArray();
        Context context = contextFactory.create();
        context.put(contextItem);
        ParserResult result = engine.apply(start, new ParserRequest(
                array,
                0,
                false,
                context
        ));

        if (result.success()) return result.getValue();
        else {
            Optional<String> error = result.getError();
            String errorMessage = error.or("Could not parse content");
            throw new ParskyException(
                    errorPrinter.print(new ErrorPrinter.Request(
                            grammar,
                            context,
                            array,
                            result.getSteps(),
                            errorMessage
                    ))
            );
        }
    }

    public Object parse(String content) throws ParskyException {
        Rule start = grammar.rule(grammar.startRule());

        char[] array = content.toCharArray();
        Context context = contextFactory.create();
        ParserResult result = engine.apply(start, new ParserRequest(
                array,
                0,
                false,
                context
        ));

        if (result.success()) return result.getValue();
        else {
            Optional<String> error = result.getError();
            String errorMessage = error.or("Could not parse content");
            throw new ParskyException(
                    errorPrinter.print(new ErrorPrinter.Request(
                            grammar,
                            context,
                            array,
                            result.getSteps(),
                            errorMessage
                    ))
            );
        }
    }
}

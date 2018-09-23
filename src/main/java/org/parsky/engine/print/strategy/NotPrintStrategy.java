package org.parsky.engine.print.strategy;

import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.NotRule;

public class NotPrintStrategy implements PrintStrategy<NotRule> {
    private final PrintStrategy printStrategy;

    public NotPrintStrategy(PrintStrategy printStrategy) {
        this.printStrategy = printStrategy;
    }

    @Override
    public PrintNode print(NotRule rule, PrintQueue queue) {
        PrintNode print = printStrategy.print(rule.getRule(), queue);

        if (print.isIgnored()) return print;
        return PrintNode.simple(String.format("!(%s)", print.getContent()));
    }
}
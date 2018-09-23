package org.parsky.engine.print.strategy;

import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.TransformRule;

public class TransformPrintStrategy implements PrintStrategy<TransformRule> {
    private final PrintStrategy printStrategy;

    public TransformPrintStrategy(PrintStrategy printStrategy) {
        this.printStrategy = printStrategy;
    }

    @Override
    public PrintNode print(TransformRule rule, PrintQueue queue) {
        return printStrategy.print(rule.getRule(), queue);
    }
}

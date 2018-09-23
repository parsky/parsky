package org.parsky.engine.print.strategy;

import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.CaptureTextRule;

public class CaptureTextPrintStrategy implements PrintStrategy<CaptureTextRule> {
    private final PrintStrategy printStrategy;

    public CaptureTextPrintStrategy(PrintStrategy printStrategy) {
        this.printStrategy = printStrategy;
    }

    @Override
    public PrintNode print(CaptureTextRule rule, PrintQueue queue) {
        return printStrategy.print(rule.getRule(), queue);
    }
}

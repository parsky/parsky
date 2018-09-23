package org.parsky.engine.print.strategy;

import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.TestRule;

public class TestPrintStrategy implements PrintStrategy<TestRule> {
    private final PrintStrategy printStrategy;

    public TestPrintStrategy(PrintStrategy printStrategy) {
        this.printStrategy = printStrategy;
    }

    @Override
    public PrintNode print(TestRule rule, PrintQueue queue) {
        PrintNode print = printStrategy.print(rule.getRule(), queue);

        if (print.isIgnored()) return print;
        return PrintNode.simple(String.format("t(%s)", print.getContent()));
    }
}

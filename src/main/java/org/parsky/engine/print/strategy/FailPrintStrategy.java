package org.parsky.engine.print.strategy;

import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.FailRule;

public class FailPrintStrategy implements PrintStrategy<FailRule> {
    @Override
    public PrintNode print(FailRule rule, PrintQueue queue) {
        return PrintNode.ignore();
    }
}

package org.parsky.engine.print.strategy;

import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.Rule;

public class EmptyPrintStrategy implements PrintStrategy {
    @Override
    public PrintNode print(Rule rule, PrintQueue queue) {
        return PrintNode.ignore();
    }
}

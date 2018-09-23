package org.parsky.engine.print.strategy;

import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.ReferenceRule;

public class ReferencePrintStrategy implements PrintStrategy<ReferenceRule> {
    @Override
    public PrintNode print(ReferenceRule rule, PrintQueue queue) {
        queue.enqueue(rule);
        return PrintNode.simple(rule.getName());
    }
}

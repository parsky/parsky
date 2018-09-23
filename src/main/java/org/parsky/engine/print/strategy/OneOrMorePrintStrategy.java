package org.parsky.engine.print.strategy;

import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.OneOrMoreRule;

public class OneOrMorePrintStrategy implements PrintStrategy<OneOrMoreRule> {
    private final PrintStrategy printStrategy;

    public OneOrMorePrintStrategy(PrintStrategy printStrategy) {
        this.printStrategy = printStrategy;
    }

    @Override
    public PrintNode print(OneOrMoreRule rule, final PrintQueue queue) {
        PrintNode node = printStrategy.print(rule.getRule(), queue);

        if (node.isIgnored()) return PrintNode.ignore();
        if (node.isSimple()) return PrintNode.simple(String.format("%s+", node.getContent()));
        else return PrintNode.simple(String.format("(%s)+", node.getContent()));
    }
}

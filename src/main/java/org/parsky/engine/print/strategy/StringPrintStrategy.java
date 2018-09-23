package org.parsky.engine.print.strategy;

import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.StringRule;

public class StringPrintStrategy implements PrintStrategy<StringRule> {
    @Override
    public PrintNode print(StringRule rule, PrintQueue queue) {
        return PrintNode.simple(String.format("\"%s\"", rule.getText()));
    }
}

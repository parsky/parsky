package org.parsky.engine.print.strategy.character;

import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.character.RangeCharRule;

public class RangeCharPrintStrategy implements CharPrintStrategy<RangeCharRule> {
    @Override
    public PrintNode print(RangeCharRule rule) {
        return PrintNode.simple(String.format("[%c-%c]", rule.getStart(), rule.getEnd()));
    }
}

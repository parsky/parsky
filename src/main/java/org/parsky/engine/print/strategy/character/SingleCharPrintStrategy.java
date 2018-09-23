package org.parsky.engine.print.strategy.character;

import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.character.SingleCharRule;

public class SingleCharPrintStrategy implements CharPrintStrategy<SingleCharRule> {
    @Override
    public PrintNode print(SingleCharRule rule) {
        return PrintNode.simple(String.format("'%c'", rule.getCharacter()));
    }
}

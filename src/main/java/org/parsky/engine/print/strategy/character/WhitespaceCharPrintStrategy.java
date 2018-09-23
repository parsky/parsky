package org.parsky.engine.print.strategy.character;

import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.character.WhitespaceCharRule;

public class WhitespaceCharPrintStrategy implements CharPrintStrategy<WhitespaceCharRule> {
    @Override
    public PrintNode print(WhitespaceCharRule rule) {
        return PrintNode.ignore();
    }
}

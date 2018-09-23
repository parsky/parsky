package org.parsky.engine.print.strategy.character;

import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.SequenceRule;
import org.parsky.grammar.rules.character.AnyOfCharRule;
import org.parsky.grammar.rules.character.WhitespaceCharRule;

public class AnyOfCharPrintStrategy implements CharPrintStrategy<AnyOfCharRule> {
    @Override
    public PrintNode print(AnyOfCharRule rule) {
        return PrintNode.simple(String.format("[%s]", new String(rule.getPossibilities())));
    }
}

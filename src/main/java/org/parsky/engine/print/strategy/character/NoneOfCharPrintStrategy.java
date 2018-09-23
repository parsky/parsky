package org.parsky.engine.print.strategy.character;

import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.character.NoneOfCharRule;

public class NoneOfCharPrintStrategy implements CharPrintStrategy<NoneOfCharRule> {
    @Override
    public PrintNode print(NoneOfCharRule rule) {
        return PrintNode.simple(String.format("[^%s]", new String(rule.getNonPossibilities())));
    }
}

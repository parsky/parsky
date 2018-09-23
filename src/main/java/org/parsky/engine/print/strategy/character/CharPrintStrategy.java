package org.parsky.engine.print.strategy.character;

import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.character.CharRule;

public interface CharPrintStrategy<T extends CharRule> {
    PrintNode print (T rule);
}

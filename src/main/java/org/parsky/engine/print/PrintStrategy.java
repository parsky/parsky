package org.parsky.engine.print;

import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.Rule;

public interface PrintStrategy<T extends Rule> {
    PrintNode print (T rule, PrintQueue queue);
}

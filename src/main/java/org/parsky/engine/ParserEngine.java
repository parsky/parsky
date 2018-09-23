package org.parsky.engine;

import org.parsky.grammar.rules.Rule;

public interface ParserEngine<T extends Rule> {
    ParserResult apply (T rule, ParserRequest request);
}

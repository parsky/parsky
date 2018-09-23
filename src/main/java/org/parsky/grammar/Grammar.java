package org.parsky.grammar;

import org.parsky.grammar.rules.Rule;

public interface Grammar {
    String startRule();
    Rule rule(String name);
    Rule rule(Class name);
}

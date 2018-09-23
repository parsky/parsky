package org.parsky.grammar;

import org.parsky.grammar.rules.Rule;

public interface RuleFactory {
    Rule create (Grammar grammar);
}

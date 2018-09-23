package org.parsky.grammar.recursive;

import org.parsky.grammar.Grammar;
import org.parsky.grammar.rules.Rule;

public interface DependentRuleFactory {
    Rule create (Rule dependency, Grammar grammar);
}

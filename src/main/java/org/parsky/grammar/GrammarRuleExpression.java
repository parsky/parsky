package org.parsky.grammar;

import org.parsky.sequence.SequenceMatcher;

public interface GrammarRuleExpression {
    SequenceMatcher sequenceMatcher (Grammar context);
}

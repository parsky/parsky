package org.parsky.grammar.rules;

import java.util.List;

public class SequenceRule implements Rule {
    private final List<Rule> sequence;

    public SequenceRule(List<Rule> sequence) {
        this.sequence = sequence;
    }

    public List<Rule> getSequence() {
        return sequence;
    }
}

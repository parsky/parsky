package org.parsky.grammar.rules;

import java.util.List;

public class FirstOfRule implements Rule {
    private final List<Rule> alternatives;

    public FirstOfRule(List<Rule> alternatives) {
        this.alternatives = alternatives;
    }

    public List<Rule> getAlternatives() {
        return alternatives;
    }
}

package org.parsky.grammar.rules;

import com.google.common.base.Optional;

import java.util.concurrent.atomic.AtomicReference;

public class ReferenceRule implements Rule {
    public static ReferenceRule create (String name, Rule rule) {
        ReferenceRule referenceRule = new ReferenceRule(name);
        referenceRule.set(rule);
        return referenceRule;
    }

    private final AtomicReference<Rule> rule = new AtomicReference<Rule>();
    private final String name;

    public ReferenceRule(String name) {
        this.name = name;
    }

    public ReferenceRule set(Rule rule) {
        this.rule.set(rule);
        return this;
    }

    public Optional<Rule> getRule() {
        return Optional.fromNullable(rule.get());
    }

    public String getName() {
        return name;
    }
}

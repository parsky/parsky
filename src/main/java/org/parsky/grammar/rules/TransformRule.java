package org.parsky.grammar.rules;

import org.parsky.grammar.rules.transform.Transform;

public class TransformRule implements Rule {
    private final Rule rule;
    private final Transform transform;

    public TransformRule(Rule rule, Transform transform) {
        this.rule = rule;
        this.transform = transform;
    }

    public Rule getRule() {
        return rule;
    }

    public Transform getTransform() {
        return transform;
    }
}

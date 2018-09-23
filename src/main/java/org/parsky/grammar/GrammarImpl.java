package org.parsky.grammar;

import org.parsky.grammar.rules.Rule;
import org.parsky.grammar.rules.ReferenceRule;

import java.util.Map;

public class GrammarImpl implements Grammar {
    private final String startRule;
    private final Map<String, ReferenceRule> references;

    GrammarImpl(String startRule, Map<String, ReferenceRule> references) {
        this.startRule = startRule;
        this.references = references;
    }

    public String startRule() {
        return startRule;
    }

    public Rule rule(String name) {
        if (!references.containsKey(name)) {
            references.put(name, new ReferenceRule(name));
        }
        return references.get(name);
    }

    @Override
    public Rule rule(Class name) {
        return rule(name.getSimpleName());
    }

    public Map<String, ReferenceRule> getReferences() {
        return references;
    }

    public String getStartRule() {
        return startRule;
    }
}

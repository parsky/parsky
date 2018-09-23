package org.parsky.grammar;

import org.parsky.grammar.rules.ReferenceRule;
import org.parsky.grammar.rules.Rule;

import java.util.HashMap;
import java.util.Map;

public class GrammarBuilder {
    public static GrammarBuilder newGrammar () {
        return new GrammarBuilder(new HashMap<String, RuleFactory>());
    }

    private final Map<String, RuleFactory> definitions;

    private GrammarBuilder(Map<String, RuleFactory> definitions) {
        this.definitions = definitions;
    }

    public GrammarBuilder define (String name, RuleFactory ruleFactory) {
        if (definitions.containsKey(name)) throw new IllegalStateException(String.format(
                "Rule with name %s is already defined", name
        ));
        definitions.put(name, ruleFactory);
        return this;
    }
    public GrammarBuilder define (String name, Rule rule) {
        return define(name, RuleFactories.simple(rule));
    }
    public GrammarBuilder define (Class name, RuleFactory ruleFactory) {
        return define(name.getSimpleName(), ruleFactory);
    }

    public GrammarBuilder define (Class name, Rule rule) {
        return define(name.getSimpleName(), RuleFactories.simple(rule));
    }

    public Grammar build (Class startRule) {
        return build(startRule.getSimpleName());
    }
    public Grammar build (String startRule) {
        HashMap<String, ReferenceRule> references = new HashMap<String, ReferenceRule>();
        Grammar grammar = new GrammarImpl(startRule, references);
        for (Map.Entry<String, RuleFactory> entry : definitions.entrySet()) {
            Rule rule = entry.getValue().create(grammar);
            if (!references.containsKey(entry.getKey())) {
                references.put(entry.getKey(), new ReferenceRule(entry.getKey()));
            }
            references.get(entry.getKey()).set(rule);
        }
        return grammar;
    }
}

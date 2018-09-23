package org.parsky.grammar.recursive;

import org.parsky.grammar.Grammar;
import org.parsky.grammar.RuleFactory;
import org.parsky.grammar.rules.Rule;
import org.parsky.grammar.rules.Rules;

import java.util.List;

public class RecursiveRuleFactory implements RuleFactory {
    public static RecursiveRuleFactory create (RuleFactory initial, List<DependentRuleFactory> dependents) {
        return new RecursiveRuleFactory(initial, dependents);
    }

    private final RuleFactory initialRule;
    private final List<DependentRuleFactory> dependentRuleFactories;

    public RecursiveRuleFactory(RuleFactory initialRule, List<DependentRuleFactory> dependentRuleFactories) {
        this.initialRule = initialRule;
        this.dependentRuleFactories = dependentRuleFactories;
    }

    @Override
    public Rule create(Grammar grammar) {
        Rule result = initialRule.create(grammar);
        for (DependentRuleFactory dependentRuleFactory : dependentRuleFactories) {
            result = Rules.firstOf(
                    dependentRuleFactory.create(result, grammar),
                    result
            );
        }
        return result;
    }
}

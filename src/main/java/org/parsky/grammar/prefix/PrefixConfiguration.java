package org.parsky.grammar.prefix;

import org.parsky.grammar.RuleFactory;

import java.util.Collection;

public class PrefixConfiguration {
    private final RuleFactory elementRule;
    private final Collection<PrefixRuleConfiguration> prefixes;
    private final PrefixCombiner combiner;

    public PrefixConfiguration(RuleFactory elementRule, Collection<PrefixRuleConfiguration> prefixes, PrefixCombiner combiner) {
        this.elementRule = elementRule;
        this.prefixes = prefixes;
        this.combiner = combiner;
    }

    public RuleFactory getElementRule() {
        return elementRule;
    }

    public Collection<PrefixRuleConfiguration> getPrefixes() {
        return prefixes;
    }

    public PrefixCombiner getCombiner() {
        return combiner;
    }
}

package org.parsky.grammar;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.parsky.grammar.rules.Rule;
import org.parsky.grammar.rules.Rules;

import java.util.ArrayList;
import java.util.Arrays;

public class RuleFactories {
    public static RuleFactory reference (final String name) {
        return new RuleFactory() {
            @Override
            public Rule create(Grammar grammar) {
                return grammar.rule(name);
            }
        };
    }

    public static RuleFactory reference (final Class type) {
        return reference(type.getSimpleName());
    }

    public static RuleFactory simple (final Rule rule) {
        return new RuleFactory() {
            @Override
            public Rule create(Grammar grammar) {
                return rule;
            }
        };
    }

    public static RuleFactory anyOf (final Object... rules) {
        return new RuleFactory() {
            @Override
            public Rule create(final Grammar grammar) {
                return Rules.firstOf(new ArrayList<Rule>(
                        Collections2.transform(Arrays.asList(rules), new Function<Object, Rule>() {
                            @Override
                            public Rule apply(Object input) {
                                if (input instanceof String) return grammar.rule((String) input);
                                else return grammar.rule((Class) input);
                            }
                        })
                ));
            }
        };
    }

    public static RuleFactory anyOf (final RuleFactory... rules) {
        return new RuleFactory() {
            @Override
            public Rule create(final Grammar grammar) {
                return Rules.firstOf(new ArrayList<Rule>(
                        Collections2.transform(Arrays.asList(rules), new Function<RuleFactory, Rule>() {
                            @Override
                            public Rule apply(RuleFactory input) {
                                return input.create(grammar);
                            }
                        })
                ));
            }
        };
    }
}

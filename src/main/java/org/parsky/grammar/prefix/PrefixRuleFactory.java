package org.parsky.grammar.prefix;

import org.parsky.engine.ParserRequest;
import org.parsky.grammar.Grammar;
import org.parsky.grammar.RuleFactory;
import org.parsky.grammar.rules.Rule;
import org.parsky.grammar.rules.Rules;
import org.parsky.grammar.rules.transform.ListTransform;
import org.parsky.grammar.rules.transform.Transform;
import org.parsky.grammar.rules.transform.Transforms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrefixRuleFactory implements RuleFactory {
    public static PrefixRuleFactory prefix (PrefixConfiguration configuration) {
        return new PrefixRuleFactory(configuration);
    }

    private final PrefixConfiguration prefixConfiguration;

    protected PrefixRuleFactory(PrefixConfiguration prefixConfiguration) {
        this.prefixConfiguration = prefixConfiguration;
    }

    @Override
    public Rule create(Grammar grammar) {
        List<Rule> rules = new ArrayList<>();
        List<PrefixRuleConfiguration> configurations = new ArrayList<>(prefixConfiguration.getPrefixes());
        Collections.sort(configurations, new Comparator<PrefixRuleConfiguration>() {
            @Override
            public int compare(PrefixRuleConfiguration o1, PrefixRuleConfiguration o2) {
                return new Integer(o1.getPrecedence()).compareTo(o2.getPrecedence());
            }
        });

        for (PrefixRuleConfiguration configuration : configurations) {
            rules.add(configuration.getRule().create(grammar));
        }

        return Rules.transform(
                Rules.sequence(
                        Rules.firstOf(rules),
                        prefixConfiguration.getElementRule().create(grammar)
                ),
                Transforms.list(new ListTransform.TransformList() {
                    @Override
                    public Transform.Result transform(ParserRequest request, ListTransform.Request input) {
                        return prefixConfiguration.getCombiner().combine(
                                request,
                                input.get(0),
                                input.get(1)
                        );
                    }
                })
        );
    }
}

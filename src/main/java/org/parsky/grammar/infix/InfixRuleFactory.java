package org.parsky.grammar.infix;

import org.parsky.engine.ParserRequest;
import org.parsky.grammar.Grammar;
import org.parsky.grammar.RuleFactory;
import org.parsky.grammar.rules.ReferenceRule;
import org.parsky.grammar.rules.Rule;
import org.parsky.grammar.rules.Rules;
import org.parsky.grammar.rules.transform.ListTransform;
import org.parsky.grammar.rules.transform.Transform;
import org.parsky.grammar.rules.transform.Transforms;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class InfixRuleFactory implements RuleFactory {
    public static RuleFactory infix (InfixConfiguration configuration) {
        return new InfixRuleFactory(configuration);
    }

    private final InfixConfiguration configuration;

    public InfixRuleFactory(InfixConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Rule create(Grammar grammar) {
        List<InfixRuleConfiguration> infixRuleConfigurations = configuration.getExpressions();

        TreeMap<Integer, List<Rule>> precedenceMap = getPrecedenceMap(grammar, infixRuleConfigurations);
        Rule rule = configuration.getElementRule().create(grammar);
        for (Integer precedence : precedenceMap.keySet()) {
            rule = appendRule(
                    configuration.getName(),
                    precedence,
                    rule,
                    Rules.firstOf(precedenceMap.get(precedence)),
                    configuration.isMandatorySecondOperand()
            );
        }

        return rule;
    }

    private Rule appendRule(String name, Integer precedence, Rule element, final Rule infix, boolean mandatory) {
        return ReferenceRule.create(
                String.format("%s-%d", name, precedence),
                Rules.transform(
                        Rules.flatten(
                                Rules.sequence(
                                        element,
                                        Rules.flatten(
                                                Rules.zeroOrMore(
                                                        Rules.sequence(
                                                                infix,
                                                                mandatory ? Rules.mandatory(element, "Expecting to find an operand after the infix operator") : element
                                                        )
                                                )
                                        )
                                )
                        ),
                        Transforms.list(new ListTransform.TransformList() {
                            @Override
                            public Transform.Result transform(ParserRequest request, ListTransform.Request input) {
                                if (configuration.getFoldType() == InfixConfiguration.FoldType.FOLD_LEFT) {
                                    return getFoldLeft(request, input, input.size() - 1);
                                } else {
                                    return getFoldRight(request, input, 0);
                                }
                            }

                            private Transform.Result getFoldLeft(ParserRequest request, ListTransform.Request input, int offset) {
                                if (offset == 0) return Transform.Result.success(input.get(0));

                                Object expression = input.get(offset);
                                Object infixExpression = input.get(offset - 1);
                                Transform.Result foldLeft = getFoldLeft(request, input, offset - 2);
                                if (foldLeft.isSuccess()) return configuration.getCombiner().combine(request, foldLeft.getValue(), infixExpression, expression);
                                else return foldLeft;
                            }

                            private Transform.Result getFoldRight(ParserRequest request, ListTransform.Request input, int offset) {
                                if (offset + 1 == input.size()) return Transform.Result.success(input.get(offset));

                                Object expression = input.get(offset);
                                Object infixExpression = input.get(offset + 1);
                                Transform.Result foldRight = getFoldRight(request, input, offset + 2);
                                if (foldRight.isSuccess()) return configuration.getCombiner().combine(request, expression, infixExpression, foldRight);
                                else return foldRight;
                            }
                        })
                )
        );
    }

    private TreeMap<Integer, List<Rule>> getPrecedenceMap(Grammar grammar, List<InfixRuleConfiguration> infixRuleConfigurations) {
        TreeMap<Integer, List<Rule>> precedenceMap = new TreeMap<>();
        for (InfixRuleConfiguration infixRuleConfiguration : infixRuleConfigurations) {
            if (!precedenceMap.containsKey(infixRuleConfiguration.getPrecedence()))
                precedenceMap.put(infixRuleConfiguration.getPrecedence(), new ArrayList<Rule>());

            precedenceMap.get(infixRuleConfiguration.getPrecedence())
                    .add(infixRuleConfiguration.getRule().create(grammar));
        }
        return precedenceMap;
    }
}

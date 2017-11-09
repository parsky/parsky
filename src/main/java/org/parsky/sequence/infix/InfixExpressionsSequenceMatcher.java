package org.parsky.sequence.infix;

import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.infix.configuration.InfixExpressionConfiguration;
import org.parsky.sequence.infix.configuration.InfixExpressionsConfiguration;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class InfixExpressionsSequenceMatcher<C, Expression, InfixExpression> implements SequenceMatcher<C> {
    private final SequenceMatcher<C> matcher;

    public InfixExpressionsSequenceMatcher (InfixExpressionsConfiguration<C, Expression, InfixExpression> configuration) {
        this.matcher = create(configuration);
    }

    private SequenceMatcher<C> create(InfixExpressionsConfiguration<C, Expression, InfixExpression> configuration) {
        TreeMap<Integer, List<SequenceMatcher<C>>> precedenceMap = getPrecedenceMap(configuration);

        SequenceMatcher<C> expressionParser = configuration.getExpressionParser();
        for (Integer precedence : precedenceMap.keySet()) {
            expressionParser = new InfixExpressionSequenceMatcher<>(
                    configuration.getCombinedExpressionFactory(),
                    expressionParser,
                    SequenceMatchers.firstOf(precedenceMap.get(precedence))
            );
        }

        return expressionParser;
    }

    private TreeMap<Integer, List<SequenceMatcher<C>>> getPrecedenceMap(InfixExpressionsConfiguration<C, Expression, InfixExpression> configuration) {
        TreeMap<Integer, List<SequenceMatcher<C>>> precedenceMap = new TreeMap<>();
        for (InfixExpressionConfiguration<C> infixExpressionConfiguration : configuration.getInfixExpressionConfigurations()) {
            if (!precedenceMap.containsKey(infixExpressionConfiguration.getPrecedence()))
                precedenceMap.put(infixExpressionConfiguration.getPrecedence(), new ArrayList<SequenceMatcher<C>>());

            precedenceMap.get(infixExpressionConfiguration.getPrecedence())
                    .add(infixExpressionConfiguration.getSequenceMatcher());
        }
        return precedenceMap;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        return matcher.matches(sequenceMatcherRequest);
    }
}

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

public class InfixExpressionsSequenceMatcher<C, Expression, InfixExpression> implements SequenceMatcher {
    private final SequenceMatcher matcher;

    public InfixExpressionsSequenceMatcher (InfixExpressionsConfiguration<Expression, InfixExpression> configuration) {
        this.matcher = create(configuration);
    }

    private SequenceMatcher create(InfixExpressionsConfiguration<Expression, InfixExpression> configuration) {
        TreeMap<Integer, List<SequenceMatcher>> precedenceMap = getPrecedenceMap(configuration);

        SequenceMatcher expressionParser = configuration.getExpressionParser();
        for (Integer precedence : precedenceMap.keySet()) {
            expressionParser = new InfixExpressionSequenceMatcher<>(
                    configuration.getCombinedExpressionFactory(),
                    expressionParser,
                    SequenceMatchers.firstOf(precedenceMap.get(precedence))
            );
        }

        return expressionParser;
    }

    private TreeMap<Integer, List<SequenceMatcher>> getPrecedenceMap(InfixExpressionsConfiguration<Expression, InfixExpression> configuration) {
        TreeMap<Integer, List<SequenceMatcher>> precedenceMap = new TreeMap<>();
        for (InfixExpressionConfiguration infixExpressionConfiguration : configuration.getInfixExpressionConfigurations()) {
            if (!precedenceMap.containsKey(infixExpressionConfiguration.getPrecedence()))
                precedenceMap.put(infixExpressionConfiguration.getPrecedence(), new ArrayList<SequenceMatcher>());

            precedenceMap.get(infixExpressionConfiguration.getPrecedence())
                    .add(infixExpressionConfiguration.getSequenceMatcher());
        }
        return precedenceMap;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        return matcher.matches(sequenceMatcherRequest);
    }
}

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

public class InfixExpressionsSequenceMatcher<C, Expression, InfixExpression> implements SequenceMatcher<C, Expression> {
    private final SequenceMatcher<C, Expression> matcher;

    public InfixExpressionsSequenceMatcher (InfixExpressionsConfiguration<C, Expression, InfixExpression> configuration) {
        this.matcher = create(configuration);
    }

    private SequenceMatcher<C, Expression> create(InfixExpressionsConfiguration<C, Expression, InfixExpression> configuration) {
        TreeMap<Integer, List<SequenceMatcher<C, InfixExpression>>> precedenceMap = getPrecedenceMap(configuration);

        SequenceMatcher<C, Expression> expressionParser = configuration.getExpressionParser();
        for (Integer precedence : precedenceMap.keySet()) {
            expressionParser = new InfixExpressionSequenceMatcher<>(
                    configuration.getCombinedExpressionFactory(),
                    expressionParser,
                    SequenceMatchers.firstOf(precedenceMap.get(precedence))
            );
        }

        return expressionParser;
    }

    private TreeMap<Integer, List<SequenceMatcher<C, InfixExpression>>> getPrecedenceMap(InfixExpressionsConfiguration<C, Expression, InfixExpression> configuration) {
        TreeMap<Integer, List<SequenceMatcher<C, InfixExpression>>> precedenceMap = new TreeMap<>();
        for (InfixExpressionConfiguration<C, InfixExpression> infixExpressionConfiguration : configuration.getInfixExpressionConfigurations()) {
            if (!precedenceMap.containsKey(infixExpressionConfiguration.getPrecedence()))
                precedenceMap.put(infixExpressionConfiguration.getPrecedence(), new ArrayList<SequenceMatcher<C, InfixExpression>>());

            precedenceMap.get(infixExpressionConfiguration.getPrecedence())
                    .add(infixExpressionConfiguration.getSequenceMatcher());
        }
        return precedenceMap;
    }

    @Override
    public SequenceMatcherResult<Expression> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        return matcher.matches(sequenceMatcherRequest);
    }
}

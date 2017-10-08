package org.parsky.sequence.infix.configuration;

import org.parsky.sequence.SequenceMatcher;

import java.util.Collection;

public class InfixExpressionsConfiguration<Context, Expression, InfixExpression> {
    private final CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory;
    private final SequenceMatcher<Context, Expression> expressionParser;
    private final Collection<InfixExpressionConfiguration<Context, InfixExpression>> infixExpressionConfigurations;

    public InfixExpressionsConfiguration(CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory, SequenceMatcher<Context, Expression> expressionParser, Collection<InfixExpressionConfiguration<Context, InfixExpression>> infixExpressionConfigurations) {
        this.combinedExpressionFactory = combinedExpressionFactory;
        this.expressionParser = expressionParser;
        this.infixExpressionConfigurations = infixExpressionConfigurations;
    }

    public CombinedExpressionFactory<Expression, InfixExpression> getCombinedExpressionFactory() {
        return combinedExpressionFactory;
    }

    public SequenceMatcher<Context, Expression> getExpressionParser() {
        return expressionParser;
    }

    public Collection<InfixExpressionConfiguration<Context, InfixExpression>> getInfixExpressionConfigurations() {
        return infixExpressionConfigurations;
    }
}

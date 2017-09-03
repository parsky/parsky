package org.parsky.sequence.infix.configuration;

import org.parsky.sequence.TypedSequenceMatcher;

import java.util.Collection;

public class InfixExpressionsConfiguration<Expression, InfixExpression> {
    private final CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory;
    private final TypedSequenceMatcher<Expression> expressionParser;
    private final Collection<InfixExpressionConfiguration<InfixExpression>> infixExpressionConfigurations;

    public InfixExpressionsConfiguration(CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory, TypedSequenceMatcher<Expression> expressionParser, Collection<InfixExpressionConfiguration<InfixExpression>> infixExpressionConfigurations) {
        this.combinedExpressionFactory = combinedExpressionFactory;
        this.expressionParser = expressionParser;
        this.infixExpressionConfigurations = infixExpressionConfigurations;
    }

    public CombinedExpressionFactory<Expression, InfixExpression> getCombinedExpressionFactory() {
        return combinedExpressionFactory;
    }

    public TypedSequenceMatcher<Expression> getExpressionParser() {
        return expressionParser;
    }

    public Collection<InfixExpressionConfiguration<InfixExpression>> getInfixExpressionConfigurations() {
        return infixExpressionConfigurations;
    }
}

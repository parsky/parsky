package org.parsky.sequence.infix.configuration;

import org.parsky.sequence.SequenceMatcher;

import java.util.Collection;

public class InfixExpressionsConfiguration<Expression, InfixExpression> {
    private final CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory;
    private final SequenceMatcher expressionParser;
    private final Collection<InfixExpressionConfiguration> infixExpressionConfigurations;

    public InfixExpressionsConfiguration(CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory, SequenceMatcher expressionParser, Collection<InfixExpressionConfiguration> infixExpressionConfigurations) {
        this.combinedExpressionFactory = combinedExpressionFactory;
        this.expressionParser = expressionParser;
        this.infixExpressionConfigurations = infixExpressionConfigurations;
    }

    public CombinedExpressionFactory<Expression, InfixExpression> getCombinedExpressionFactory() {
        return combinedExpressionFactory;
    }

    public SequenceMatcher getExpressionParser() {
        return expressionParser;
    }

    public Collection<InfixExpressionConfiguration> getInfixExpressionConfigurations() {
        return infixExpressionConfigurations;
    }
}

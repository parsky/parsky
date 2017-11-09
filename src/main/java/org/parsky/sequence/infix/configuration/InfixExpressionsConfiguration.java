package org.parsky.sequence.infix.configuration;

import org.parsky.sequence.SequenceMatcher;

import java.util.Collection;

public class InfixExpressionsConfiguration<Context, Expression, InfixExpression> {
    private final CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory;
    private final SequenceMatcher<Context> expressionParser;
    private final Collection<InfixExpressionConfiguration<Context>> infixExpressionConfigurations;

    public InfixExpressionsConfiguration(CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory, SequenceMatcher<Context> expressionParser, Collection<InfixExpressionConfiguration<Context>> infixExpressionConfigurations) {
        this.combinedExpressionFactory = combinedExpressionFactory;
        this.expressionParser = expressionParser;
        this.infixExpressionConfigurations = infixExpressionConfigurations;
    }

    public CombinedExpressionFactory<Expression, InfixExpression> getCombinedExpressionFactory() {
        return combinedExpressionFactory;
    }

    public SequenceMatcher<Context> getExpressionParser() {
        return expressionParser;
    }

    public Collection<InfixExpressionConfiguration<Context>> getInfixExpressionConfigurations() {
        return infixExpressionConfigurations;
    }
}

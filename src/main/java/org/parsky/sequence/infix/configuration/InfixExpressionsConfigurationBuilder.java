package org.parsky.sequence.infix.configuration;

import com.google.common.base.Preconditions;
import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.transform.Transformations;

import java.util.ArrayList;
import java.util.Collection;

public class InfixExpressionsConfigurationBuilder<Expression, InfixExpression> {
    public static <Expression, InfixExpression> InfixExpressionsConfigurationBuilder<Expression, InfixExpression> infixExpressionsConfiguration() {
        return new InfixExpressionsConfigurationBuilder<>();
    }

    private CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory;
    private SequenceMatcher expressionParser;
    private final Collection<InfixExpressionConfiguration> infixExpressionConfigurations = new ArrayList<>();

    public InfixExpressionsConfigurationBuilder<Expression, InfixExpression> withCombinedExpressionFactory(CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory) {
        this.combinedExpressionFactory = combinedExpressionFactory;
        return this;
    }

    public InfixExpressionsConfigurationBuilder<Expression, InfixExpression> withExpressionParser(SequenceMatcher expressionParser) {
        this.expressionParser = expressionParser;
        return this;
    }

    public InfixExpressionsConfigurationBuilder<Expression, InfixExpression> withInfix (SequenceMatcher sequenceMatcher, int precedence) {
        this.infixExpressionConfigurations.add(new InfixExpressionConfiguration(
                sequenceMatcher,
                precedence
        ));
        return this;
    }

    public InfixExpressionsConfigurationBuilder<Expression, InfixExpression> withInfix (InfixExpressionConfiguration infix) {
        this.infixExpressionConfigurations.add(infix);
        return this;
    }

    public InfixExpressionsConfigurationBuilder<Expression, InfixExpression> withInfixExpressions (Collection<InfixExpressionConfiguration> infixes) {
        this.infixExpressionConfigurations.addAll(infixes);
        return this;
    }

    public InfixExpressionsConfigurationBuilder<Expression, InfixExpression> withInfix (String symbol, InfixExpression expression, int precedence) {
        this.infixExpressionConfigurations.add(new InfixExpressionConfiguration(
                SequenceMatchers.transform("infix " + symbol, SequenceMatchers.string(symbol), Transformations.constant(expression)),
                precedence
        ));
        return this;
    }

    public InfixExpressionsConfiguration<Expression, InfixExpression> build () {
        Preconditions.checkArgument(combinedExpressionFactory != null, "combinedExpressionFactory is required");
        Preconditions.checkArgument(expressionParser != null, "expressionParser is required");
        Preconditions.checkArgument(!infixExpressionConfigurations.isEmpty(), "infixExpressionConfigurations cannot be empty");

        return new InfixExpressionsConfiguration<>(
                combinedExpressionFactory,
                expressionParser,
                infixExpressionConfigurations
        );
    }
}

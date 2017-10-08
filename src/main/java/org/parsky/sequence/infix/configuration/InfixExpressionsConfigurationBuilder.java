package org.parsky.sequence.infix.configuration;

import com.google.common.base.Preconditions;
import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.transform.Transformations;

import java.util.ArrayList;
import java.util.Collection;

public class InfixExpressionsConfigurationBuilder<Context, Expression, InfixExpression> {
    public static <Context, Expression, InfixExpression> InfixExpressionsConfigurationBuilder<Context, Expression, InfixExpression> infixExpressionsConfiguration() {
        return new InfixExpressionsConfigurationBuilder<>();
    }

    private CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory;
    private SequenceMatcher<Context, Expression> expressionParser;
    private final Collection<InfixExpressionConfiguration<Context, InfixExpression>> infixExpressionConfigurations = new ArrayList<>();

    public InfixExpressionsConfigurationBuilder<Context, Expression, InfixExpression> withCombinedExpressionFactory(CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory) {
        this.combinedExpressionFactory = combinedExpressionFactory;
        return this;
    }

    public InfixExpressionsConfigurationBuilder<Context, Expression, InfixExpression> withExpressionParser(SequenceMatcher<Context, Expression> expressionParser) {
        this.expressionParser = expressionParser;
        return this;
    }

    public InfixExpressionsConfigurationBuilder<Context, Expression, InfixExpression> withInfix (SequenceMatcher<Context, InfixExpression> sequenceMatcher, int precedence) {
        this.infixExpressionConfigurations.add(new InfixExpressionConfiguration<Context, InfixExpression>(
                sequenceMatcher,
                precedence
        ));
        return this;
    }

    public InfixExpressionsConfigurationBuilder<Context, Expression, InfixExpression> withInfix (InfixExpressionConfiguration<Context, InfixExpression> infix) {
        this.infixExpressionConfigurations.add(infix);
        return this;
    }

    public InfixExpressionsConfigurationBuilder<Context, Expression, InfixExpression> withInfixExpressions (Collection<InfixExpressionConfiguration<Context, InfixExpression>> infixes) {
        this.infixExpressionConfigurations.addAll(infixes);
        return this;
    }

    public InfixExpressionsConfigurationBuilder<Context, Expression, InfixExpression> withInfix (String symbol, InfixExpression expression, int precedence) {
        this.infixExpressionConfigurations.add(new InfixExpressionConfiguration<Context, InfixExpression>(
                SequenceMatchers.<Context, String, InfixExpression>transform("infix " + symbol, SequenceMatchers.<Context>string(symbol), Transformations.<String, InfixExpression>constant(expression)),
                precedence
        ));
        return this;
    }

    public InfixExpressionsConfiguration<Context, Expression, InfixExpression> build () {
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

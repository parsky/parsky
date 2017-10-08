package org.parsky.sequence.infix.configuration;


import org.parsky.sequence.SequenceMatcher;

public class InfixExpressionConfiguration<Context, InfixExpression> {
    private final SequenceMatcher<Context, InfixExpression> sequenceMatcher;
    private final int precedence;

    public InfixExpressionConfiguration(SequenceMatcher<Context, InfixExpression> sequenceMatcher, int precedence) {
        this.sequenceMatcher = sequenceMatcher;
        this.precedence = precedence;
    }

    public SequenceMatcher<Context, InfixExpression> getSequenceMatcher() {
        return sequenceMatcher;
    }

    public int getPrecedence() {
        return precedence;
    }
}

package org.parsky.sequence.infix.configuration;

import org.parsky.sequence.TypedSequenceMatcher;

public class InfixExpressionConfiguration<InfixExpression> {
    private final TypedSequenceMatcher<InfixExpression> sequenceMatcher;
    private final int precedence;

    public InfixExpressionConfiguration(TypedSequenceMatcher<InfixExpression> sequenceMatcher, int precedence) {
        this.sequenceMatcher = sequenceMatcher;
        this.precedence = precedence;
    }

    public TypedSequenceMatcher<InfixExpression> getSequenceMatcher() {
        return sequenceMatcher;
    }

    public int getPrecedence() {
        return precedence;
    }
}

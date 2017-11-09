package org.parsky.sequence.infix.configuration;


import org.parsky.sequence.SequenceMatcher;

public class InfixExpressionConfiguration<Context> {
    private final SequenceMatcher<Context> sequenceMatcher;
    private final int precedence;

    public InfixExpressionConfiguration(SequenceMatcher<Context> sequenceMatcher, int precedence) {
        this.sequenceMatcher = sequenceMatcher;
        this.precedence = precedence;
    }

    public SequenceMatcher<Context> getSequenceMatcher() {
        return sequenceMatcher;
    }

    public int getPrecedence() {
        return precedence;
    }
}

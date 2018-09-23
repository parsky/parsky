package org.parsky.sequence.infix.configuration;


import org.parsky.sequence.SequenceMatcher;

public class InfixExpressionConfiguration {
    private final SequenceMatcher sequenceMatcher;
    private final int precedence;

    public InfixExpressionConfiguration(SequenceMatcher sequenceMatcher, int precedence) {
        this.sequenceMatcher = sequenceMatcher;
        this.precedence = precedence;
    }

    public SequenceMatcher getSequenceMatcher() {
        return sequenceMatcher;
    }

    public int getPrecedence() {
        return precedence;
    }
}

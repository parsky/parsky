package org.parsky.sequence.infix.configuration;

import org.parsky.sequence.model.SequenceMatcherRequest;

public interface CombinedExpressionFactory<Expression, InfixExpression> {
    Expression create(SequenceMatcherRequest request, Expression left, InfixExpression infix, Expression right);
}

package org.parsky.sequence.infix.configuration;

public interface CombinedExpressionFactory<Expression, InfixExpression> {
    Expression create (Expression left, InfixExpression infix, Expression right);
}

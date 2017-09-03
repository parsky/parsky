package org.parsky.sequence.infix.transform;

import com.google.common.base.Function;
import org.parsky.sequence.infix.configuration.CombinedExpressionFactory;
import org.parsky.sequence.transform.ListContentTransformation;

public class CombinedExpressionTransformation<Expression, InfixExpression> implements Function<ListContentTransformation.Request, Expression> {
    private final CombinedExpressionFactory<Expression, InfixExpression> factory;

    public CombinedExpressionTransformation(CombinedExpressionFactory<Expression, InfixExpression> factory) {
        this.factory = factory;
    }

    @Override
    public Expression apply(ListContentTransformation.Request input) {
        return getRest(0, input);
    }

    private Expression getRest(int offset, ListContentTransformation.Request input) {
        if (offset + 1 >= input.size()) return input.get(input.size() - 1);

        Expression expression = input.get(offset);
        InfixExpression infixExpression = input.get(offset + 1);
        return factory.create(expression, infixExpression, getRest(offset + 2, input));
    }
}

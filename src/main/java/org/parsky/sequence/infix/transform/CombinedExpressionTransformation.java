package org.parsky.sequence.infix.transform;

import com.google.common.base.Function;
import org.parsky.sequence.infix.configuration.CombinedExpressionFactory;
import org.parsky.sequence.transform.ListContentTransformation;
import org.parsky.sequence.transform.Transformation;

public class CombinedExpressionTransformation<Expression, InfixExpression> implements Function<ListContentTransformation.Request, Transformation.Result> {
    private final CombinedExpressionFactory<Expression, InfixExpression> factory;

    public CombinedExpressionTransformation(CombinedExpressionFactory<Expression, InfixExpression> factory) {
        this.factory = factory;
    }

    @Override
    public Transformation.Result apply(ListContentTransformation.Request input) {
        return Transformation.Result.success(getRest(input.size()-1, input));
    }

    private Expression getRest(int offset, ListContentTransformation.Request input) {
        if (offset == 0) return input.get(0);

        Expression expression = input.get(offset);
        InfixExpression infixExpression = input.get(offset - 1);
        return factory.create(input.getSequenceMatcherRequest(), getRest(offset - 2, input), infixExpression, expression);
    }
}

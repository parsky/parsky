package org.parsky.sequence.infix;

import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.infix.configuration.CombinedExpressionFactory;
import org.parsky.sequence.infix.transform.CombinedExpressionTransformation;
import org.parsky.sequence.transform.Transformation;
import org.parsky.sequence.transform.Transformations;

import java.util.List;

public class InfixExpressionSequenceMatcher<Context, Expression, InfixExpression> extends TransformSequenceMatcher<Context, List<Expression>, Expression> {
    public InfixExpressionSequenceMatcher(CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory, SequenceMatcher<Context, Expression> expressionSequenceMatcher, SequenceMatcher<Context, InfixExpression> infixExpressionSequenceMatcher) {
        super(SequenceMatchers.flatten(
                SequenceMatchers.sequence(
                        (SequenceMatcher) expressionSequenceMatcher,
                        (SequenceMatcher) SequenceMatchers.flatten(
                                SequenceMatchers.zeroOrMore(
                                        SequenceMatchers.sequence(
                                                (SequenceMatcher<Context, Object>) infixExpressionSequenceMatcher,
                                                (SequenceMatcher<Context, Object>) expressionSequenceMatcher
                                        )
                                )
                        )
                )
        ), (Transformation) Transformations.fromContentList(new CombinedExpressionTransformation<>(combinedExpressionFactory)));
    }
}
